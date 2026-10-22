package top.niunaijun.blackbox.core.system.pm.installer;

import java.io.File;
import java.io.IOException;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.pm.BPackageSettings;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.NativeUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/installer/CopyExecutor.class */
public class CopyExecutor implements Executor {
    @Override // top.niunaijun.blackbox.core.system.pm.installer.Executor
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        try {
            if (!option.isFlag(1)) {
                NativeUtils.copyNativeLib(new File(ps.pkg.baseCodePath), BEnvironment.getAppLibDir(ps.pkg.packageName));
            }
            if (option.isFlag(2)) {
                File origFile = new File(ps.pkg.baseCodePath);
                File newFile = BEnvironment.getBaseApkDir(ps.pkg.packageName);
                try {
                    if (option.isFlag(8)) {
                        boolean b = FileUtils.renameTo(origFile, newFile);
                        if (!b) {
                            FileUtils.copyFile(origFile, newFile);
                        }
                    } else {
                        FileUtils.copyFile(origFile, newFile);
                    }
                    newFile.setReadOnly();
                    ps.pkg.baseCodePath = newFile.getAbsolutePath();
                    return 0;
                } catch (IOException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            if (option.isFlag(1)) {
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
