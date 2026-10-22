package top.niunaijun.blackbox.core.system.pm;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/PackageMonitor.class */
public interface PackageMonitor {
    void onPackageUninstalled(String str, boolean z, int i);

    void onPackageInstalled(String str, int i);
}
