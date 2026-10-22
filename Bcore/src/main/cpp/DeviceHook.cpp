//
// 设备指纹 native 层 hook：/proc 文件重定向 + CLOCK_BOOTTIME 校准。
//
// 用 ShadowHook（AAR prefab，支持 16KB page size）做 inline hook。Java 侧在
// DeviceInfoManager 里生成好 cpuinfo / meminfo 临时文件、算出开机时间后调用这里
// 的两个 JNI 方法，这样 C/C++ 代码直接读 /proc 或 SystemClock 也能拿到档案里的值。
//

#include <jni.h>
#include <android/log.h>
#include <fcntl.h>
#include <stdarg.h>
#include <stdio.h>
#include <string.h>
#include <sys/syscall.h>
#include <sys/system_properties.h>
#include <time.h>
#include <unistd.h>

#include <shadowhook.h>

#include <android/api-level.h>

#include "JniHook/JniHook.h"

#define DEVHOOK_TAG "DeviceHook"
#define DEVHOOK_LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, DEVHOOK_TAG, __VA_ARGS__)
#define DEVHOOK_LOGE(...) __android_log_print(ANDROID_LOG_ERROR, DEVHOOK_TAG, __VA_ARGS__)

namespace {

constexpr int kPathMax = 512;

char gCpuInfoPath[kPathMax] = {0};
char gMemInfoPath[kPathMax] = {0};
long long gBootOffsetNanos = 0;
bool gHooksInstalled = false;
bool gShadowHookReady = false;
bool gProcHitLogged = false;
bool gGpuHookInstalled = false;

typedef int (*OpenatFn)(int, const char *, int, ...);
typedef int (*OpenFn)(const char *, int, ...);
typedef int (*ClockGettimeFn)(clockid_t, struct timespec *);

char gGpuRenderer[256] = {0};
char gGpuVendor[256] = {0};

typedef jstring (*GlGetStringJavaFn)(JNIEnv *, jclass, jint);

GlGetStringJavaFn gOrigGlGetStringJava = nullptr;

OpenatFn gOrigOpenat = nullptr;
OpenFn gOrigOpen = nullptr;
ClockGettimeFn gOrigClockGettime = nullptr;

void SelfCheck();

void *HookSymbol(const char *libName, const char *symbol, void *replace, void **origin);

const char *RemapProcPath(const char *path) {
    if (path == nullptr) {
        return nullptr;
    }
    const char *target = nullptr;
    if (gCpuInfoPath[0] != 0 && strcmp(path, "/proc/cpuinfo") == 0) {
        target = gCpuInfoPath;
    } else if (gMemInfoPath[0] != 0 && strcmp(path, "/proc/meminfo") == 0) {
        target = gMemInfoPath;
    }
    if (target != nullptr && !gProcHitLogged) {
        gProcHitLogged = true;
        DEVHOOK_LOGD("proc redirect hit: %s", path);
    }
    return target;
}

int HookOpenat(int dirfd, const char *path, int flags, ...) {
    if (gOrigOpenat == nullptr) {
        return -1;
    }
    mode_t mode = 0;
    if ((flags & O_CREAT) != 0 || (flags & O_TMPFILE) == O_TMPFILE) {
        va_list args;
        va_start(args, flags);
        mode = static_cast<mode_t>(va_arg(args, int));
        va_end(args);
    }
    const char *remapped = dirfd == AT_FDCWD ? RemapProcPath(path) : nullptr;
    return gOrigOpenat(dirfd, remapped != nullptr ? remapped : path, flags, mode);
}

int HookOpen(const char *path, int flags, ...) {
    if (gOrigOpen == nullptr) {
        return -1;
    }
    mode_t mode = 0;
    if ((flags & O_CREAT) != 0 || (flags & O_TMPFILE) == O_TMPFILE) {
        va_list args;
        va_start(args, flags);
        mode = static_cast<mode_t>(va_arg(args, int));
        va_end(args);
    }
    const char *remapped = RemapProcPath(path);
    return gOrigOpen(remapped != nullptr ? remapped : path, flags, mode);
}

int HookClockGettime(clockid_t clockId, struct timespec *ts) {
    if (gOrigClockGettime == nullptr) {
        // Origin 没拿到时必须直接走 syscall，否则日志/计时链路会跳到空指针
        return static_cast<int>(syscall(__NR_clock_gettime, clockId, ts));
    }
    int result = gOrigClockGettime(clockId, ts);
    if (result == 0 && gBootOffsetNanos != 0 &&
        (clockId == CLOCK_BOOTTIME || clockId == CLOCK_BOOTTIME_ALARM)) {
        long long nanos = static_cast<long long>(ts->tv_sec) * 1000000000LL + ts->tv_nsec;
        nanos += gBootOffsetNanos;
        if (nanos < 0) {
            nanos = 0;
        }
        ts->tv_sec = static_cast<time_t>(nanos / 1000000000LL);
        ts->tv_nsec = static_cast<long>(nanos % 1000000000LL);
    }
    return result;
}

/**
 * 走 framework 的 Java 入口 android.opengl.GLES20.glGetString(int)，用 JniHook 换掉它的实现。
 *
 * <p>不碰 EGL / libGLESv2 的导出符号，所以不会影响应用自己的渲染初始化；
 * 通过 Java API 读 GL 字符串的 SDK 都会被覆盖，未覆盖的常量原样转发给原实现。</p>
 */
jstring HookGlGetStringJava(JNIEnv *env, jclass clazz, jint name) {
    switch (name) {
        case 0x1F00:  // GL_VENDOR
            if (gGpuVendor[0] != 0) {
                return env->NewStringUTF(gGpuVendor);
            }
            break;
        case 0x1F01:  // GL_RENDERER
            if (gGpuRenderer[0] != 0) {
                static bool loggedRenderer = false;
                if (!loggedRenderer) {
                    loggedRenderer = true;
                    DEVHOOK_LOGD("glGetString(GL_RENDERER) -> %s", gGpuRenderer);
                }
                return env->NewStringUTF(gGpuRenderer);
            }
            break;
        // GL_VERSION(0x1F02) 不伪造：HWUI / GL 库用它判断能力集，
        // 返回和真实驱动对不上的版本串会直接导致渲染失败（黑屏）。
        case 0x1F02:
            break;
        default:
            break;
    }
    return gOrigGlGetStringJava != nullptr
           ? gOrigGlGetStringJava(env, clazz, name) : nullptr;
}

/**
 * 默认开启；`setprop debug.bb.native_gpu_hook 0` 可关闭。
 */
void InstallGpuHook(JNIEnv *env) {
    if (gGpuHookInstalled || env == nullptr) {
        return;
    }
    if (gGpuRenderer[0] == 0 && gGpuVendor[0] == 0) {
        return;
    }
    // 默认开启；`setprop debug.bb.native_gpu_hook 0` 可关闭
    char enabled[PROP_VALUE_MAX] = {0};
    int len = __system_property_get("debug.bb.native_gpu_hook", enabled);
    if (len > 0 && enabled[0] == '0') {
        DEVHOOK_LOGD("gpu hook disabled (debug.bb.native_gpu_hook=0)");
        return;
    }
    // 同一进程里 libblackbox.so 可能被加载出多个实例（宿主 + guest 各一份），
    // HookEnv 是每个实例各自的静态变量；这里按需初始化当前实例，否则 native_offset 恒为 0。
    if (JniHook::NativeOffset() == 0) {
        JniHook::InitJniHook(env, android_get_device_api_level());
        DEVHOOK_LOGD("JniHook re-initialized: native_offset=%d", JniHook::NativeOffset());
    }
    JniHook::HookJniFun(env, "android/opengl/GLES20", "glGetString",
                        "(I)Ljava/lang/String;",
                        reinterpret_cast<void *>(HookGlGetStringJava),
                        reinterpret_cast<void **>(&gOrigGlGetStringJava), true,
                        /* skip_flags_check = */ true);
    if (gOrigGlGetStringJava == nullptr) {
        DEVHOOK_LOGE("GLES20.glGetString hook failed (origin null, native_offset=%d)",
                     JniHook::NativeOffset());
        return;
    }
    gGpuHookInstalled = true;
    DEVHOOK_LOGD("GLES20.glGetString hooked (renderer=%s)", gGpuRenderer);
}

/** syscall 直连，避免在 hook 内部再走一次被 hook 的 clock_gettime。 */
long long RealClockNanos(clockid_t clockId) {
    struct timespec ts = {0, 0};
    syscall(__NR_clock_gettime, clockId, &ts);
    return static_cast<long long>(ts.tv_sec) * 1000000000LL + ts.tv_nsec;
}

/** 默认开启；`setprop debug.bb.native_hook 0` 可关闭（排查兼容性问题时用）。 */
bool NativeHookEnabled() {
    char value[PROP_VALUE_MAX] = {0};
    int len = __system_property_get("debug.bb.native_hook", value);
    if (len > 0 && value[0] == '0') {
        return false;
    }
    return true;
}

void *HookSymbol(const char *libName, const char *symbol, void *replace, void **origin) {
    void *stub = shadowhook_hook_sym_name(libName, symbol, replace, origin);
    if (stub == nullptr) {
        DEVHOOK_LOGE("%s!%s hook failed: %s", libName, symbol,
                     shadowhook_to_errmsg(shadowhook_get_errno()));
        return nullptr;
    }
    if (origin != nullptr && *origin == nullptr) {
        // 拿不到 original：说明 ShadowHook 没能完成重定位（vDSO / 短跳板 / 16KB 页）。
        // 立刻回滚，否则任何调用都会跳到空指针。
        DEVHOOK_LOGE("%s!%s hooked but origin is null, rolling back", libName, symbol);
        shadowhook_unhook(stub);
        return nullptr;
    }
    DEVHOOK_LOGD("%s!%s hooked (origin=%p)", libName, symbol,
                 origin != nullptr ? *origin : nullptr);
    return stub;
}

/**
 * 自检：native 层自己 fopen("/proc/cpuinfo")，确认 open hook 已生效
 * （拿到的是档案生成的文件，尺寸/首行与真机不同）。
 */
void SelfCheck() {
    FILE *file = fopen("/proc/cpuinfo", "r");
    if (file == nullptr) {
        DEVHOOK_LOGE("selfcheck: fopen /proc/cpuinfo failed");
        return;
    }
    char line[128] = {0};
    long total = 0;
    if (fgets(line, sizeof(line) - 1, file) != nullptr) {
        char buffer[4096];
        size_t readBytes;
        while ((readBytes = fread(buffer, 1, sizeof(buffer), file)) > 0) {
            total += static_cast<long>(readBytes);
        }
    }
    fclose(file);
    DEVHOOK_LOGD("selfcheck cpuinfo: %ld bytes, first line: %.60s", total, line);
}

void InstallHooks() {
    if (gHooksInstalled) {
        return;
    }
    gHooksInstalled = true;
    if (!NativeHookEnabled()) {
        DEVHOOK_LOGD("native hook disabled (debug.bb.native_hook=0)");
        return;
    }
    if (shadowhook_init(SHADOWHOOK_MODE_UNIQUE, false) != 0) {
        DEVHOOK_LOGE("shadowhook init failed: %s",
                     shadowhook_to_errmsg(shadowhook_get_errno()));
        return;
    }
    gShadowHookReady = true;

    HookSymbol("libc.so", "openat", reinterpret_cast<void *>(HookOpenat),
               reinterpret_cast<void **>(&gOrigOpenat));
    HookSymbol("libc.so", "open", reinterpret_cast<void *>(HookOpen),
               reinterpret_cast<void **>(&gOrigOpen));
    char clockEnabled[PROP_VALUE_MAX] = {0};
    if (__system_property_get("debug.bb.native_clock", clockEnabled) > 0
            && clockEnabled[0] == '1') {
        HookSymbol("libc.so", "clock_gettime", reinterpret_cast<void *>(HookClockGettime),
                   reinterpret_cast<void **>(&gOrigClockGettime));
    } else {
        DEVHOOK_LOGD("clock hook disabled (debug.bb.native_clock != 1)");
    }
    SelfCheck();
}

void CopyPath(JNIEnv *env, jstring value, char *target) {
    target[0] = 0;
    if (value == nullptr) {
        return;
    }
    const char *chars = env->GetStringUTFChars(value, nullptr);
    if (chars == nullptr) {
        return;
    }
    strncpy(target, chars, kPathMax - 1);
    target[kPathMax - 1] = 0;
    env->ReleaseStringUTFChars(value, chars);
}

}  // namespace

extern "C" {

JNIEXPORT void JNICALL
Java_top_niunaijun_blackbox_core_NativeCore_setProcPaths(JNIEnv *env, jclass clazz,
                                                         jstring cpuInfoPath,
                                                         jstring memInfoPath) {
    CopyPath(env, cpuInfoPath, gCpuInfoPath);
    CopyPath(env, memInfoPath, gMemInfoPath);
    InstallHooks();
    DEVHOOK_LOGD("proc paths updated: %s | %s", gCpuInfoPath, gMemInfoPath);
}

JNIEXPORT void JNICALL
Java_top_niunaijun_blackbox_core_NativeCore_setBootTime(JNIEnv *env, jclass clazz,
                                                        jlong bootTimeMillis) {
    if (bootTimeMillis <= 0) {
        gBootOffsetNanos = 0;
        return;
    }
    InstallHooks();
    long long realUptimeNanos = RealClockNanos(CLOCK_BOOTTIME);
    long long nowNanos = RealClockNanos(CLOCK_REALTIME);
    long long targetUptimeNanos = nowNanos - bootTimeMillis * 1000000LL;
    gBootOffsetNanos = targetUptimeNanos - realUptimeNanos;
    DEVHOOK_LOGD("boot offset set: %lld ns (shadowhook=%d)", gBootOffsetNanos,
                 gShadowHookReady ? 1 : 0);
}

JNIEXPORT void JNICALL
Java_top_niunaijun_blackbox_core_NativeCore_setGpuInfo(JNIEnv *env, jclass clazz,
                                                       jstring renderer, jstring vendor) {
    CopyPath(env, renderer, gGpuRenderer);
    CopyPath(env, vendor, gGpuVendor);
    if (gGpuRenderer[0] == 0 && gGpuVendor[0] == 0) {
        return;
    }
    InstallGpuHook(env);
    DEVHOOK_LOGD("gpu configured: %s | %s", gGpuRenderer, gGpuVendor);
}

/**
 * 资源加载：确认 framework 的 ApkAssets::nativeLoad 符号可解析（兼容性自检）。
 *
 * 宿主侧资源走 Java 的 AssetManager.addAssetPath（PackageManagerCompat），
 * 这里不做路径改写，避免破坏 guest 的资源解析。
 */
JNIEXPORT jboolean JNICALL
Java_top_niunaijun_blackbox_core_NativeCore_disableResourceLoading(JNIEnv *env, jclass clazz) {
    if (!gShadowHookReady && shadowhook_init(SHADOWHOOK_MODE_UNIQUE, false) != 0) {
        return JNI_FALSE;
    }
    gShadowHookReady = true;
    void *handle = shadowhook_dlopen("libandroid_runtime.so");
    if (handle == nullptr) {
        DEVHOOK_LOGE("ResourceLoading: could not open libandroid_runtime.so");
        return JNI_FALSE;
    }
    void *symbol = shadowhook_dlsym(handle, "_ZN7android8ApkAssets9nativeLoadEPKc");
    shadowhook_dlclose(handle);
    if (symbol == nullptr) {
        DEVHOOK_LOGE("ResourceLoading: ApkAssets::nativeLoad not found");
        return JNI_FALSE;
    }
    DEVHOOK_LOGD("ResourceLoading: ApkAssets::nativeLoad at %p (passthrough)", symbol);
    return JNI_TRUE;
}

}  // extern "C"
