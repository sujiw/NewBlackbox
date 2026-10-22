package top.niunaijun.blackbox.utils;

import android.os.Build;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import top.niunaijun.blackbox.core.system.JarConfig;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/NativeUtils.class */
public class NativeUtils {
    public static final String TAG = "VirtualM";

    public static void copyNativeLib(File apk, File nativeLibDir) throws Exception {
        long startTime = System.currentTimeMillis();
        if (!nativeLibDir.exists()) {
            nativeLibDir.mkdirs();
        }
        try {
            ZipFile zipfile = new ZipFile(apk.getAbsolutePath());
            try {
                if (!findAndCopyNativeLib(zipfile, Build.CPU_ABI, nativeLibDir)) {
                    findAndCopyNativeLib(zipfile, "armeabi", nativeLibDir);
                    zipfile.close();
                    Log.d(TAG, "Done! +" + (System.currentTimeMillis() - startTime) + "ms");
                    return;
                } else {
                    zipfile.close();
                    Log.d(TAG, "Done! +" + (System.currentTimeMillis() - startTime) + "ms");
                    return;
                }
            } catch (Throwable th) {
                try {
                    zipfile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            Log.d(TAG, "Done! +" + (System.currentTimeMillis() - startTime) + "ms");
            throw th3;
        }
    }

    private static boolean findAndCopyNativeLib(ZipFile zipfile, String cpuArch, File nativeLibDir) throws Exception {
        Log.d(TAG, "Try to copy plugin's cup arch: " + cpuArch);
        boolean findLib = false;
        boolean findSo = false;
        byte[] buffer = null;
        String libPrefix = "lib/" + cpuArch + "/";
        Enumeration<? extends ZipEntry> enumerationEntries = zipfile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry entry = enumerationEntries.nextElement();
            String entryName = entry.getName();
            if (findLib || entryName.startsWith("lib/")) {
                findLib = true;
                if (entryName.endsWith(".so") && entryName.startsWith(libPrefix)) {
                    if (buffer == null) {
                        findSo = true;
                        Log.d(TAG, "Found plugin's cup arch dir: " + cpuArch);
                        buffer = new byte[JarConfig.DEFAULT_BUFFER_SIZE];
                    }
                    String libName = entryName.substring(entryName.lastIndexOf(47) + 1);
                    Log.d(TAG, "verify so " + libName);
                    File libFile = new File(nativeLibDir, libName);
                    if (libFile.exists() && libFile.length() == entry.getSize()) {
                        Log.d(TAG, libName + " skip copy");
                    } else {
                        FileOutputStream fos = new FileOutputStream(libFile);
                        Log.d(TAG, "copy so " + entry.getName() + " of " + cpuArch);
                        copySo(buffer, zipfile.getInputStream(entry), fos);
                    }
                }
            }
        }
        if (!findLib) {
            Log.d(TAG, "Fast skip all!");
            return true;
        }
        return findSo;
    }

    private static void copySo(byte[] buffer, InputStream input, OutputStream output) throws IOException {
        BufferedInputStream bufferedInput = new BufferedInputStream(input);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        while (true) {
            int count = bufferedInput.read(buffer);
            if (count > 0) {
                bufferedOutput.write(buffer, 0, count);
            } else {
                bufferedOutput.flush();
                bufferedOutput.close();
                output.close();
                bufferedInput.close();
                input.close();
                return;
            }
        }
    }
}
