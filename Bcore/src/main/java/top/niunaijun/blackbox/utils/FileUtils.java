package top.niunaijun.blackbox.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.system.Os;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/FileUtils.class */
public class FileUtils {

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/FileUtils$FileMode.class */
    public interface FileMode {
        public static final int MODE_ISUID = 2048;
        public static final int MODE_ISGID = 1024;
        public static final int MODE_ISVTX = 512;
        public static final int MODE_IRUSR = 256;
        public static final int MODE_IWUSR = 128;
        public static final int MODE_IXUSR = 64;
        public static final int MODE_IRGRP = 32;
        public static final int MODE_IWGRP = 16;
        public static final int MODE_IXGRP = 8;
        public static final int MODE_IROTH = 4;
        public static final int MODE_IWOTH = 2;
        public static final int MODE_IXOTH = 1;
        public static final int MODE_755 = 493;
    }

    public static int count(File file) {
        String[] fs;
        if (!file.exists()) {
            return -1;
        }
        if (file.isFile()) {
            return 1;
        }
        if (!file.isDirectory() || (fs = file.list()) == null) {
            return 0;
        }
        return fs.length;
    }

    public static String getFilenameExt(String filename) {
        int dotPos = filename.lastIndexOf(46);
        if (dotPos == -1) {
            return "";
        }
        return filename.substring(dotPos + 1);
    }

    public static File changeExt(File f, String targetExt) {
        String outPath;
        String outPath2 = f.getAbsolutePath();
        if (!getFilenameExt(outPath2).equals(targetExt)) {
            int dotPos = outPath2.lastIndexOf(".");
            if (dotPos > 0) {
                outPath = outPath2.substring(0, dotPos + 1) + targetExt;
            } else {
                outPath = outPath2 + "." + targetExt;
            }
            return new File(outPath);
        }
        return f;
    }

    public static boolean renameTo(File origFile, File newFile) {
        return origFile.renameTo(newFile);
    }

    public static String readToString(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (true) {
            int i = is.read();
            if (i != -1) {
                baos.write(i);
            } else {
                return baos.toString();
            }
        }
    }

    public static Parcel readToParcel(File file) throws IOException {
        Parcel in = Parcel.obtain();
        byte[] bytes = toByteArray(file);
        in.unmarshall(bytes, 0, bytes.length);
        in.setDataPosition(0);
        return in;
    }

    public static void chmod(String path, int mode) throws Exception {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.chmod(path, mode);
                return;
            } catch (Exception e) {
            }
        }
        File file = new File(path);
        String cmd = "chmod ";
        if (file.isDirectory()) {
            cmd = cmd + " -R ";
        }
        String cmode = String.format("%o", Integer.valueOf(mode));
        Runtime.getRuntime().exec(cmd + cmode + " " + path).waitFor();
    }

    public static void createSymlink(String oldPath, String newPath) throws Exception {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.link(oldPath, newPath);
                return;
            } catch (Throwable th) {
            }
        }
        Runtime.getRuntime().exec("ln -s " + oldPath + " " + newPath).waitFor();
    }

    public static boolean isSymlink(File file) throws IOException {
        File canon;
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (file.getParent() == null) {
            canon = file;
        } else {
            File canonDir = file.getParentFile().getCanonicalFile();
            canon = new File(canonDir, file.getName());
        }
        return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
    }

    public static void writeParcelToFile(Parcel p, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(p.marshall());
        fos.close();
    }

    public static void writeParcelToOutput(Parcel p, FileOutputStream fos) throws IOException {
        fos.write(p.marshall());
    }

    public static byte[] toByteArray(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return toByteArray(fileInputStream);
        } finally {
            closeQuietly(fileInputStream);
        }
    }

    public static byte[] toByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        while (true) {
            int rc = inStream.read(buff, 0, 100);
            if (rc > 0) {
                swapStream.write(buff, 0, rc);
            } else {
                return swapStream.toByteArray();
            }
        }
    }

    public static int deleteDir(File dir) {
        int count = 0;
        if (dir.isDirectory()) {
            boolean link = false;
            try {
                link = isSymlink(dir);
            } catch (Exception e) {
            }
            if (!link) {
                String[] children = dir.list();
                for (String file : children) {
                    count += deleteDir(new File(dir, file));
                }
            }
        }
        if (dir.delete()) {
            count++;
        }
        return count;
    }

    public static int deleteDir(String dir) {
        return deleteDir(new File(dir));
    }

    public static void writeToFile(InputStream dataIns, File target) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
        byte[] data = new byte[1024];
        while (true) {
            int count = dataIns.read(data, 0, 1024);
            if (count != -1) {
                bos.write(data, 0, count);
            } else {
                bos.close();
                return;
            }
        }
    }

    public static void writeToFile(byte[] data, File target) throws IOException {
        FileOutputStream fo = null;
        ReadableByteChannel src = null;
        FileChannel out = null;
        try {
            src = Channels.newChannel(new ByteArrayInputStream(data));
            fo = new FileOutputStream(target);
            FileChannel out2 = fo.getChannel();
            out2.transferFrom(src, 0L, data.length);
            if (fo != null) {
                fo.close();
            }
            if (src != null) {
                src.close();
            }
            if (out2 != null) {
            }
        } finally {
            if (fo != null) {
                fo.close();
            }
            if (src != null) {
                src.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static void copyFile(InputStream inputStream, File target) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(target);
            byte[] data = new byte[4096];
            while (true) {
                int len = inputStream.read(data);
                if (len != -1) {
                    outputStream.write(data, 0, len);
                } else {
                    outputStream.flush();
                    closeQuietly(inputStream);
                    closeQuietly(outputStream);
                    return;
                }
            }
        } catch (Throwable th) {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }
    }

    public static void copyFile(File source, File target) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            FileChannel iChannel = inputStream.getChannel();
            FileChannel oChannel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();
                int r = iChannel.read(buffer);
                if (r != -1) {
                    buffer.limit(buffer.position());
                    buffer.position(0);
                    oChannel.write(buffer);
                } else {
                    closeQuietly(inputStream);
                    closeQuietly(outputStream);
                    return;
                }
            }
        } catch (Throwable th) {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
            throw th;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static int peekInt(byte[] bytes, int value, ByteOrder endian) {
        int v0;
        if (endian == ByteOrder.BIG_ENDIAN) {
            int v1 = value + 1;
            int v2 = v1 + 1;
            v0 = ((bytes[v1] & 255) << 16) | ((bytes[value] & 255) << 24) | ((bytes[v2] & 255) << 8) | (bytes[v2 + 1] & 255);
        } else {
            int v3 = value + 1;
            int v4 = v3 + 1;
            v0 = ((bytes[v3] & 255) << 8) | (bytes[value] & 255) | ((bytes[v4] & 255) << 16) | ((bytes[v4 + 1] & 255) << 24);
        }
        return v0;
    }

    private static boolean isValidExtFilenameChar(char c) {
        switch (c) {
            case 0:
            case '/':
                return false;
            default:
                return true;
        }
    }

    public static boolean isValidExtFilename(String name) {
        return name != null && name.equals(buildValidExtFilename(name));
    }

    public static String buildValidExtFilename(String name) {
        if (TextUtils.isEmpty(name) || ".".equals(name) || "..".equals(name)) {
            return "(invalid)";
        }
        StringBuilder res = new StringBuilder(name.length());
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (isValidExtFilenameChar(c)) {
                res.append(c);
            } else {
                res.append('_');
            }
        }
        return res.toString();
    }

    public static void mkdirs(File path) {
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    public static void mkdirs(String path) {
        mkdirs(new File(path));
    }

    public static boolean isExist(String path) {
        return new File(path).exists();
    }

    public static boolean canRead(String path) {
        return new File(path).canRead();
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/FileUtils$FileLock.class */
    public static class FileLock {
        private static FileLock singleton;
        private Map<String, FileLockCount> mRefCountMap = new ConcurrentHashMap();

        public static FileLock getInstance() {
            if (singleton == null) {
                singleton = new FileLock();
            }
            return singleton;
        }

        private int RefCntInc(String filePath, java.nio.channels.FileLock fileLock, RandomAccessFile randomAccessFile, FileChannel fileChannel) {
            int refCount;
            if (this.mRefCountMap.containsKey(filePath)) {
                FileLockCount fileLockCount = this.mRefCountMap.get(filePath);
                int i = fileLockCount.mRefCount;
                fileLockCount.mRefCount = i + 1;
                refCount = i;
            } else {
                refCount = 1;
                this.mRefCountMap.put(filePath, new FileLockCount(fileLock, 1, randomAccessFile, fileChannel));
            }
            return refCount;
        }

        private int RefCntDec(String filePath) {
            int refCount = 0;
            if (this.mRefCountMap.containsKey(filePath)) {
                FileLockCount fileLockCount = this.mRefCountMap.get(filePath);
                int i = fileLockCount.mRefCount - 1;
                fileLockCount.mRefCount = i;
                refCount = i;
                if (refCount <= 0) {
                    this.mRefCountMap.remove(filePath);
                }
            }
            return refCount;
        }

        public boolean LockExclusive(File targetFile) {
            if (targetFile == null) {
                return false;
            }
            try {
                File lockFile = new File(targetFile.getParentFile().getAbsolutePath().concat("/lock"));
                if (!lockFile.exists()) {
                    lockFile.createNewFile();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(lockFile.getAbsolutePath(), "rw");
                FileChannel channel = randomAccessFile.getChannel();
                java.nio.channels.FileLock lock = channel.lock();
                if (!lock.isValid()) {
                    return false;
                }
                RefCntInc(lockFile.getAbsolutePath(), lock, randomAccessFile, channel);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public void unLock(File targetFile) {
            FileLockCount fileLockCount;
            File lockFile = new File(targetFile.getParentFile().getAbsolutePath().concat("/lock"));
            if (lockFile.exists() && this.mRefCountMap.containsKey(lockFile.getAbsolutePath()) && (fileLockCount = this.mRefCountMap.get(lockFile.getAbsolutePath())) != null) {
                java.nio.channels.FileLock fileLock = fileLockCount.mFileLock;
                RandomAccessFile randomAccessFile = fileLockCount.fOs;
                FileChannel fileChannel = fileLockCount.fChannel;
                try {
                    if (RefCntDec(lockFile.getAbsolutePath()) <= 0) {
                        if (fileLock != null && fileLock.isValid()) {
                            fileLock.release();
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/FileUtils$FileLock$FileLockCount.class */
        private class FileLockCount {
            FileChannel fChannel;
            RandomAccessFile fOs;
            java.nio.channels.FileLock mFileLock;
            int mRefCount;

            FileLockCount(java.nio.channels.FileLock fileLock, int mRefCount, RandomAccessFile fOs, FileChannel fChannel) {
                this.mFileLock = fileLock;
                this.mRefCount = mRefCount;
                this.fOs = fOs;
                this.fChannel = fChannel;
            }
        }
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String[] projection = {"_data"};
        try {
            Cursor cursor2 = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor2 == null || !cursor2.moveToFirst()) {
                return cursor2 != null ? null : null;
            }
            int column_index = cursor2.getColumnIndexOrThrow("_data");
            String string = cursor2.getString(column_index);
            if (cursor2 != null) {
            }
            return string;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
