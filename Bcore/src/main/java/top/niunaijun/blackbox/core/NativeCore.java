package top.niunaijun.blackbox.core;

import android.os.Process;
import android.util.Log;
import androidx.annotation.Keep;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.JarManager;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.DexFileCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/NativeCore.class */
public class NativeCore {
    public static final String TAG = "NativeCore";

    public static native void init(int i);

    public static native void enableIO();

    public static native void addIORule(String str, String str2);

    public static native void hideXposed();

    public static native boolean disableHiddenApi();

    public static native boolean disableResourceLoading();

    /** native 层 /proc/cpuinfo、/proc/meminfo 的重定向目标（源码版新增）。 */
    public static native void setProcPaths(String cpuInfoPath, String memInfoPath);

    /** native 层 CLOCK_BOOTTIME 校准，让 SystemClock.elapsedRealtime 对上档案的开机时间。 */
    public static native void setBootTime(long bootTimeMillis);

    /**
     * native 层 GLES20.glGetString 覆盖：只替换 GL_RENDERER / GL_VENDOR。
     *
     * <p>GL_VERSION 故意不伪造 —— HWUI 与 GL 库用它在运行时判断能力集，
     * 返回和真实驱动不一致的版本串会导致渲染失败（窗口全黑）。档案里的
     * gpuVersion 仍然保留，只用于上报 payload。</p>
     */
    public static native void setGpuInfo(String renderer, String vendor);

    static {
        System.loadLibrary("blackbox");
    }

    @Keep
    public static int getCallingUid(int origCallingUid) {
        if (origCallingUid > 0 && origCallingUid < 10000) {
            return origCallingUid;
        }
        if (origCallingUid > 19999) {
            return origCallingUid;
        }
        try {
            if (origCallingUid == BlackBoxCore.getHostUid()) {
                String appPackageName = BlackBoxCore.getAppPackageName();
                if (appPackageName == null || appPackageName.equals(GmsCore.GMS_PKG)) {
                }
                if (appPackageName != null && appPackageName.equals("com.google.android.webview")) {
                    return Process.myUid();
                }
                try {
                    int callingBUid = BlackBoxCore.getCallingBUid();
                    if (callingBUid > 0 && callingBUid < 19999) {
                        return callingBUid;
                    }
                    return BlackBoxCore.getHostUid();
                } catch (Exception e) {
                    Log.w(TAG, "Error getting calling BUid: " + e.getMessage());
                }
            } else {
                return origCallingUid;
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error in getCallingUid: " + e2.getMessage());
            return Process.myUid();
        }
        return origCallingUid;
    }

    @Keep
    public static String redirectPath(String path) {
        return IOCore.get().redirectPath(path);
    }

    @Keep
    public static File redirectPath(File path) {
        return IOCore.get().redirectPath(path);
    }

    @Keep
    public static long[] loadEmptyDex() {
        try {
            File emptyJar = JarManager.getInstance().getEmptyJar();
            if (emptyJar == null) {
                Log.w(TAG, "Empty JAR not available, attempting sync initialization");
                JarManager.getInstance().initializeSync();
                emptyJar = JarManager.getInstance().getEmptyJar();
            }
            if (emptyJar == null || !emptyJar.exists()) {
                Log.e(TAG, "Empty JAR file not found or invalid");
                return new long[0];
            }
            DexFile dexFile = new DexFile(emptyJar);
            List<Long> cookies = DexFileCompat.getCookies(dexFile);
            long[] longs = new long[cookies.size()];
            for (int i = 0; i < cookies.size(); i++) {
                longs[i] = cookies.get(i).longValue();
            }
            Log.d(TAG, "Successfully loaded empty DEX with " + cookies.size() + " cookies");
            return longs;
        } catch (Exception e) {
            Log.e(TAG, "Failed to load empty DEX", e);
            return new long[0];
        }
    }

    private static long[] createFallbackEmptyDex() {
        try {
            Slog.d(TAG, "Creating fallback empty DEX");
            byte[] emptyDexBytes = createMinimalDexBytes();
            File tempDexFile = File.createTempFile("fallback_empty", ".dex");
            tempDexFile.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempDexFile);
            fos.write(emptyDexBytes);
            fos.close();
            DexFile dexFile = new DexFile(tempDexFile);
            List<Long> cookies = DexFileCompat.getCookies(dexFile);
            if (cookies != null && !cookies.isEmpty()) {
                long[] longs = new long[cookies.size()];
                for (int i = 0; i < cookies.size(); i++) {
                    longs[i] = cookies.get(i).longValue();
                }
                Slog.d(TAG, "Successfully created fallback empty DEX with " + cookies.size() + " cookies");
                return longs;
            }
        } catch (Exception e) {
            Slog.e(TAG, "Error creating fallback empty DEX: " + e.getMessage());
        }
        Slog.w(TAG, "Returning empty DEX array as last resort");
        return new long[0];
    }

    private static byte[] createMinimalDexBytes() {
        byte[] dexHeader = {100, 101, 120, 10, 48, 51, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        return dexHeader;
    }
}
