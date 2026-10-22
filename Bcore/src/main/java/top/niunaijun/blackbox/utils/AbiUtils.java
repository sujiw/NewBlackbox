package top.niunaijun.blackbox.utils;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/AbiUtils.class */
public class AbiUtils {
    private final Set<String> mLibs = new HashSet();
    private static final Map<File, AbiUtils> sAbiUtilsMap = new HashMap();

    public static boolean isSupport(File apkFile) {
        AbiUtils abiUtils = sAbiUtilsMap.get(apkFile);
        if (abiUtils == null) {
            abiUtils = new AbiUtils(apkFile);
            sAbiUtilsMap.put(apkFile, abiUtils);
        }
        if (abiUtils.isEmptyAib()) {
            return true;
        }
        if (BlackBoxCore.is64Bit()) {
            return abiUtils.is64Bit();
        }
        return abiUtils.is32Bit();
    }

    public AbiUtils(File apkFile) {
        ZipFile zipFile = null;
        try {
            try {
                zipFile = new ZipFile(apkFile);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = entries.nextElement();
                    String name = zipEntry.getName();
                    if (name.startsWith("lib/arm64-v8a")) {
                        this.mLibs.add("arm64-v8a");
                    } else if (name.startsWith("lib/armeabi")) {
                        this.mLibs.add("armeabi");
                    } else if (name.startsWith("lib/armeabi-v7a")) {
                        this.mLibs.add("armeabi-v7a");
                    }
                }
                CloseUtils.close(zipFile);
            } catch (Exception e) {
                e.printStackTrace();
                CloseUtils.close(zipFile);
            }
        } catch (Throwable th) {
            CloseUtils.close(zipFile);
            throw th;
        }
    }

    public boolean is64Bit() {
        return this.mLibs.contains("arm64-v8a");
    }

    public boolean is32Bit() {
        return this.mLibs.contains("armeabi") || this.mLibs.contains("armeabi-v7a");
    }

    public boolean isEmptyAib() {
        return this.mLibs.isEmpty();
    }
}
