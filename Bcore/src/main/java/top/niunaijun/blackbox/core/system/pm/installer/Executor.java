package top.niunaijun.blackbox.core.system.pm.installer;

import top.niunaijun.blackbox.core.system.pm.BPackageSettings;
import top.niunaijun.blackbox.entity.pm.InstallOption;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/installer/Executor.class */
public interface Executor {
    public static final String TAG = "InstallExecutor";

    int exec(BPackageSettings bPackageSettings, InstallOption installOption, int i);
}
