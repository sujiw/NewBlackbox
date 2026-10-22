package top.niunaijun.blackbox.utils.compat;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.entity.pm.InstalledModule;
import top.niunaijun.blackbox.utils.CloseUtils;
import top.niunaijun.blackbox.utils.ShellUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/XposedParserCompat.class */
public class XposedParserCompat {
    public static InstalledModule parseModule(ApplicationInfo applicationInfo) {
        try {
            PackageManager packageManager = BlackBoxCore.getPackageManager();
            InstalledModule module = new InstalledModule();
            module.packageName = applicationInfo.packageName;
            module.enable = false;
            module.desc = applicationInfo.metaData.getString("xposeddescription");
            module.name = applicationInfo.loadLabel(packageManager).toString();
            module.main = readMain(applicationInfo.sourceDir);
            return module;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static boolean isXPModule(String file) {
        try {
            String s = readMain(file);
            return s != null;
        } catch (Throwable th) {
            return false;
        }
    }

    private static String readMain(String apk) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(new File(apk));
            ZipEntry entry = zipFile.getEntry("assets/xposed_init");
            if (entry == null) {
                throw new RuntimeException();
            }
            return getInputStreamContent(zipFile.getInputStream(entry)).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.close(zipFile);
        }
    }

    private static String getInputStreamContent(InputStream stream) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            try {
                reader = new BufferedReader(new InputStreamReader(stream));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!line.startsWith("#")) {
                        builder.append(line).append(ShellUtils.COMMAND_LINE_END);
                    }
                }
                CloseUtils.close(reader);
            } catch (Exception e) {
                e.printStackTrace();
                CloseUtils.close(reader);
            }
            return builder.toString();
        } catch (Throwable th) {
            CloseUtils.close(reader);
            throw th;
        }
    }
}
