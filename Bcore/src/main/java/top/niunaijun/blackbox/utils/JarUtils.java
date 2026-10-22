package top.niunaijun.blackbox.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipInputStream;
import top.niunaijun.blackbox.core.system.JarConfig;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/JarUtils.class */
public class JarUtils {
    private static final String TAG = "JarUtils";

    public static void copyFileWithProgress(InputStream input, File target, String fileName) throws IOException {
        if (input == null) {
            throw new IOException("Input stream is null");
        }
        if (target == null) {
            throw new IOException("Target file is null");
        }
        File targetDir = target.getParentFile();
        if (targetDir != null && !targetDir.exists() && !targetDir.mkdirs()) {
            throw new IOException("Failed to create target directory: " + targetDir);
        }
        FileOutputStream output = null;
        File tempFile = null;
        try {
            try {
                File tempFile2 = File.createTempFile(target.getName(), ".tmp", targetDir);
                FileOutputStream output2 = new FileOutputStream(tempFile2);
                byte[] buffer = new byte[JarConfig.DEFAULT_BUFFER_SIZE];
                long totalBytes = 0;
                long lastLogTime = System.currentTimeMillis();
                while (true) {
                    int bytesRead = input.read(buffer);
                    if (bytesRead == -1) {
                        break;
                    }
                    output2.write(buffer, 0, bytesRead);
                    totalBytes += (long) bytesRead;
                    if (totalBytes % 102400 == 0 && System.currentTimeMillis() - lastLogTime > 1000) {
                        Log.d(TAG, "Copying " + fileName + ": " + (totalBytes / 1024) + "KB");
                        lastLogTime = System.currentTimeMillis();
                    }
                }
                output2.flush();
                output2.close();
                FileOutputStream output3 = null;
                if (!tempFile2.renameTo(target)) {
                    throw new IOException("Failed to move temporary file to target: " + target);
                }
                Log.d(TAG, "Successfully copied " + fileName + " (" + totalBytes + " bytes)");
                if (0 != 0) {
                    try {
                        output3.close();
                    } catch (IOException e) {
                        Log.w(TAG, "Failed to close output stream", e);
                    }
                }
            } catch (IOException e2) {
                if (0 != 0 && tempFile.exists() && !tempFile.delete()) {
                    Log.w(TAG, "Failed to delete temporary file: " + ((Object) null));
                }
                throw e2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    output.close();
                } catch (IOException e3) {
                    Log.w(TAG, "Failed to close output stream", e3);
                }
            }
            throw th;
        }
    }

    public static String calculateFileHash(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[JarConfig.DEFAULT_BUFFER_SIZE];
            while (true) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                digest.update(buffer, 0, bytesRead);
            }
            fis.close();
            byte[] hash = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(255 & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            Log.w(TAG, "Failed to calculate file hash", e);
            return null;
        }
    }

    public static boolean verifyJarFile(File jarFile) {
        if (jarFile == null || !jarFile.exists() || jarFile.length() == 0) {
            return false;
        }
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(jarFile));
            int entryCount = 0;
            while (zis.getNextEntry() != null) {
                try {
                    entryCount++;
                    if (entryCount > 10) {
                        break;
                    }
                    zis.closeEntry();
                } catch (Throwable th) {
                    try {
                        zis.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            boolean z = entryCount > 0;
            zis.close();
            return z;
        } catch (IOException e) {
            Log.w(TAG, "JAR file verification failed: " + jarFile.getName(), e);
            return false;
        }
    }

    public static String getJarInfo(File jarFile) {
        if (jarFile == null || !jarFile.exists()) {
            return "File not found";
        }
        StringBuilder info = new StringBuilder();
        info.append("Size: ").append(jarFile.length()).append(" bytes, ");
        if (verifyJarFile(jarFile)) {
            info.append("Valid JAR");
        } else {
            info.append("Invalid JAR");
        }
        String hash = calculateFileHash(jarFile);
        if (hash != null) {
            info.append(", Hash: ").append(hash.substring(0, Math.min(16, hash.length())));
        }
        return info.toString();
    }

    public static boolean safeDelete(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        int retryCount = 0;
        while (retryCount < 3) {
            if (file.delete()) {
                return true;
            }
            retryCount++;
            if (retryCount < 3) {
                try {
                    Thread.sleep(100 * retryCount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        Log.w(TAG, "Failed to delete file after " + retryCount + " attempts: " + file);
        return false;
    }

    public static boolean createDirectory(File dir) {
        if (dir == null) {
            return false;
        }
        if (dir.exists()) {
            return dir.isDirectory();
        }
        if (dir.mkdirs()) {
            dir.setReadable(true, true);
            dir.setWritable(true, true);
            return true;
        }
        return false;
    }

    public static int getOptimalBufferSize() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        if (maxMemory > 536870912) {
            return JarConfig.MAX_BUFFER_SIZE;
        }
        if (maxMemory > 268435456) {
            return 16384;
        }
        return JarConfig.DEFAULT_BUFFER_SIZE;
    }
}
