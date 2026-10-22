package top.niunaijun.blackbox.core.system;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.AppSystemEnv;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.accounts.BAccountManagerService;
import top.niunaijun.blackbox.core.system.am.BActivityManagerService;
import top.niunaijun.blackbox.core.system.am.BJobManagerService;
import top.niunaijun.blackbox.core.system.location.BLocationManagerService;
import top.niunaijun.blackbox.core.system.notification.BNotificationManagerService;
import top.niunaijun.blackbox.core.system.os.BStorageManagerService;
import top.niunaijun.blackbox.core.system.pm.BPackageInstallerService;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.core.system.pm.BXposedManagerService;
import top.niunaijun.blackbox.entity.pm.InstallOption;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/BlackBoxSystem.class */
public class BlackBoxSystem {
    private static BlackBoxSystem sBlackBoxSystem;
    private final List<ISystemService> mServices = new ArrayList();
    private static final AtomicBoolean isStartup = new AtomicBoolean(false);

    public static BlackBoxSystem getSystem() {
        if (sBlackBoxSystem == null) {
            synchronized (BlackBoxSystem.class) {
                if (sBlackBoxSystem == null) {
                    sBlackBoxSystem = new BlackBoxSystem();
                }
            }
        }
        return sBlackBoxSystem;
    }

    public void startup() {
        if (isStartup.getAndSet(true)) {
            return;
        }
        BEnvironment.load();
        this.mServices.add(BPackageManagerService.get());
        this.mServices.add(BActivityManagerService.get());
        this.mServices.add(BJobManagerService.get());
        this.mServices.add(BStorageManagerService.get());
        this.mServices.add(BPackageInstallerService.get());
        this.mServices.add(BXposedManagerService.get());
        this.mServices.add(BProcessManagerService.get());
        this.mServices.add(BAccountManagerService.get());
        this.mServices.add(BLocationManagerService.get());
        this.mServices.add(BNotificationManagerService.get());
        for (ISystemService service : this.mServices) {
            service.systemReady();
        }
        List<String> preInstallPackages = AppSystemEnv.getPreInstallPackages();
        for (String preInstallPackage : preInstallPackages) {
            try {
                if (!BPackageManagerService.get().isInstalled(preInstallPackage, -1)) {
                    PackageInfo packageInfo = BlackBoxCore.getPackageManager().getPackageInfo(preInstallPackage, 0);
                    BPackageManagerService.get().installPackageAsUser(packageInfo.applicationInfo.sourceDir, InstallOption.installBySystem(), -1);
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        JarManager.getInstance().initializeAsync();
    }
}
