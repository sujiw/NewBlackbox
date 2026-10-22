package top.niunaijun.blackbox.core.system.pm.installer;

import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.pm.BPackageSettings;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.FileUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/installer/RemoveAppExecutor.class */
public class RemoveAppExecutor implements Executor {
    @Override // top.niunaijun.blackbox.core.system.pm.installer.Executor
    public int exec(BPackageSettings ps, InstallOption option, int userId) {
        FileUtils.deleteDir(BEnvironment.getAppDir(ps.pkg.packageName));
        return 0;
    }
}
