//
// Created by Milk on 4/9/21.
//

#include "BoxCore.h"
#include "Log.h"
#include "IO.h"
#include <jni.h>
#include <android/api-level.h>
#include <JniHook/JniHook.h>
#include <Hook/VMClassLoaderHook.h>
#include <Hook/UnixFileSystemHook.h>
#include <Hook/BinderHook.h>
#include <Hook/RuntimeHook.h>
#include "Utils/HexDump.h"
#include "Utils/fake_dlfcn.h"
#include <shadowhook.h>
#include <string>

struct {
    JavaVM *vm;
    jclass NativeCoreClass;
    jmethodID getCallingUidId;
    jmethodID redirectPathString;
    jmethodID redirectPathFile;
    jmethodID loadEmptyDex;
    jmethodID loadEmptyDexL;
    int api_level;
} VMEnv;


JNIEnv *getEnv() {
    JNIEnv *env;
    VMEnv.vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6);
    return env;
}

JNIEnv *ensureEnvCreated() {
    JNIEnv *env = getEnv();
    if (env == NULL) {
        VMEnv.vm->AttachCurrentThread(&env, NULL);
    }
    return env;
}

int BoxCore::getCallingUid(JNIEnv *env, int orig) {
    env = ensureEnvCreated();
    return env->CallStaticIntMethod(VMEnv.NativeCoreClass, VMEnv.getCallingUidId, orig);
}

jstring BoxCore::redirectPathString(JNIEnv *env, jstring path) {
    env = ensureEnvCreated();
    return (jstring) env->CallStaticObjectMethod(VMEnv.NativeCoreClass, VMEnv.redirectPathString, path);
}

jobject BoxCore::redirectPathFile(JNIEnv *env, jobject path) {
    env = ensureEnvCreated();
    return env->CallStaticObjectMethod(VMEnv.NativeCoreClass, VMEnv.redirectPathFile, path);
}

jlongArray BoxCore::loadEmptyDex(JNIEnv *env) {
    env = ensureEnvCreated();
    return (jlongArray) env->CallStaticObjectMethod(VMEnv.NativeCoreClass, VMEnv.loadEmptyDex);
}

int BoxCore::getApiLevel() {
    return VMEnv.api_level;
}

JavaVM *BoxCore::getJavaVM() {
    return VMEnv.vm;
}

void nativeHook(JNIEnv *env) {
    BaseHook::init(env);
    UnixFileSystemHook::init(env);
    VMClassLoaderHook::init(env);
//    RuntimeHook::init(env);
    BinderHook::init(env);
}

void hideXposed(JNIEnv *env, jclass clazz) {
    ALOGD("set hideXposed");
    VMClassLoaderHook::hideXposed();
}

void init(JNIEnv *env, jobject clazz, jint api_level) {
    ALOGD("NativeCore init.");
    VMEnv.api_level = api_level;
    VMEnv.NativeCoreClass = (jclass) env->NewGlobalRef(env->FindClass(VMCORE_CLASS));
    VMEnv.getCallingUidId = env->GetStaticMethodID(VMEnv.NativeCoreClass, "getCallingUid", "(I)I");
    VMEnv.redirectPathString = env->GetStaticMethodID(VMEnv.NativeCoreClass, "redirectPath",
                                                      "(Ljava/lang/String;)Ljava/lang/String;");
    VMEnv.redirectPathFile = env->GetStaticMethodID(VMEnv.NativeCoreClass, "redirectPath",
                                                    "(Ljava/io/File;)Ljava/io/File;");
    VMEnv.loadEmptyDex = env->GetStaticMethodID(VMEnv.NativeCoreClass, "loadEmptyDex",
                                                "()[J");

    JniHook::InitJniHook(env, api_level);
}

void addIORule(JNIEnv *env, jclass clazz, jstring target_path,
               jstring relocate_path) {
    IO::addRule(env->GetStringUTFChars(target_path, JNI_FALSE),
                env->GetStringUTFChars(relocate_path, JNI_FALSE));
}

void enableIO(JNIEnv *env, jclass clazz) {
    IO::init(env);
    nativeHook(env);
}


/**
 * 关闭隐藏 API 限制（和预编译版同样走 art::VMRuntime::setHiddenApiExemptions，
 * 只是这里用 fake_dlfcn 直接解析 libart 的 .dynsym，避免 dlopen 命名空间限制）。
 */
static jboolean disableHiddenApi(JNIEnv *env, jclass clazz) {
    if (android_get_device_api_level() < 29) {
        ALOGD("HiddenAPI: Android version < 29, no need to disable");
        return JNI_TRUE;
    }
    const char *names[] = {
            "_ZN3artL32VMRuntime_setHiddenApiExemptionsEP7_JNIEnvP7_jclassP13_jobjectArray",
            "_ZN3art9VMRuntime25setHiddenApiExemptionsEP7_JNIEnvP7_jclassP13_jobjectArray",
    };
    void *symbol = nullptr;
    // 先走 fake_dlfcn（不依赖 linker namespace），失败再用 ShadowHook 的 dlopen 兜底
    void *handle = fake_dlopen("libart.so", 0);
    if (handle != nullptr) {
        for (const char *name : names) {
            symbol = fake_dlsym(handle, name);
            if (symbol != nullptr) {
                ALOGD("HiddenAPI: Found symbol %s at %p", name, symbol);
                break;
            }
        }
        fake_dlclose(handle);
    }
    if (symbol == nullptr) {
        void *shadowHandle = shadowhook_dlopen("libart.so");
        if (shadowHandle != nullptr) {
            for (const char *name : names) {
                symbol = shadowhook_dlsym(shadowHandle, name);
                if (symbol != nullptr) {
                    ALOGD("HiddenAPI: Found symbol via shadowhook: %s", name);
                    break;
                }
            }
            shadowhook_dlclose(shadowHandle);
        }
    }
    if (symbol == nullptr) {
        ALOGE("HiddenAPI: Didn't find setHiddenApiExemptions in any form");
        return JNI_FALSE;
    }
    jclass stringClass = env->FindClass("java/lang/String");
    jobjectArray args = env->NewObjectArray(1, stringClass, nullptr);
    jstring wildcard = env->NewStringUTF("L");
    env->SetObjectArrayElement(args, 0, wildcard);
    typedef void (*SetExemptions)(JNIEnv *, jclass, jobjectArray);
    reinterpret_cast<SetExemptions>(symbol)(env, clazz, args);
    env->DeleteLocalRef(wildcard);
    env->DeleteLocalRef(args);
    ALOGD("HiddenAPI: exemptions applied");
    return JNI_TRUE;
}

static JNINativeMethod gMethods[] = {
        {"hideXposed", "()V",                                     (void *) hideXposed},
        {"addIORule",  "(Ljava/lang/String;Ljava/lang/String;)V", (void *) addIORule},
        {"enableIO",   "()V",                                     (void *) enableIO},
        {"init",       "(I)V",                                    (void *) init},
        {"disableHiddenApi",      "()Z", (void *) disableHiddenApi},
};

int registerNativeMethods(JNIEnv *env, const char *className,
                          JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == nullptr) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env, VMCORE_CLASS, gMethods,
                               sizeof(gMethods) / sizeof(gMethods[0])))
        return JNI_FALSE;
    return JNI_TRUE;
}

void registerMethod(JNIEnv *jenv) {
    registerNatives(jenv);
}

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    VMEnv.vm = vm;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_EVERSION;
    }
    registerMethod(env);
    return JNI_VERSION_1_6;
}