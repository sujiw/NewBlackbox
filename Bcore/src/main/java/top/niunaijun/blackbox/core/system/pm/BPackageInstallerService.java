package top.niunaijun.blackbox.core.system.pm;

import java.util.ArrayList;
import java.util.List;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.core.system.pm.installer.CopyExecutor;
import top.niunaijun.blackbox.core.system.pm.installer.CreatePackageExecutor;
import top.niunaijun.blackbox.core.system.pm.installer.CreateUserExecutor;
import top.niunaijun.blackbox.core.system.pm.installer.Executor;
import top.niunaijun.blackbox.core.system.pm.installer.RemoveAppExecutor;
import top.niunaijun.blackbox.core.system.pm.installer.RemoveUserExecutor;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackageInstallerService.class */
public class BPackageInstallerService extends IBPackageInstallerService.Stub implements ISystemService {
    private static final BPackageInstallerService sService = new BPackageInstallerService();
    public static final String TAG = "BPackageInstallerService";

    public static BPackageInstallerService get() {
        return sService;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
    public int installPackageAsUser(BPackageSettings ps, int userId) {
        List<Executor> executors = new ArrayList<>();
        executors.add(new CreateUserExecutor());
        executors.add(new CreatePackageExecutor());
        executors.add(new CopyExecutor());
        InstallOption option = ps.installOption;
        for (Executor executor : executors) {
            int exec = executor.exec(ps, option, userId);
            Slog.d(TAG, "installPackageAsUser: " + executor.getClass().getSimpleName() + " exec: " + exec);
            if (exec != 0) {
                return exec;
            }
        }
        return 0;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
    public int uninstallPackageAsUser(BPackageSettings ps, boolean removeApp, int userId) {
        List<Executor> executors = new ArrayList<>();
        if (removeApp) {
            executors.add(new RemoveAppExecutor());
        }
        executors.add(new RemoveUserExecutor());
        InstallOption option = ps.installOption;
        for (Executor executor : executors) {
            int exec = executor.exec(ps, option, userId);
            Slog.d(TAG, "uninstallPackageAsUser: " + executor.getClass().getSimpleName() + " exec: " + exec);
            if (exec != 0) {
                return exec;
            }
        }
        return 0;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
    public int clearPackage(BPackageSettings ps, int userId) {
        List<Executor> executors = new ArrayList<>();
        executors.add(new RemoveUserExecutor());
        executors.add(new CreateUserExecutor());
        InstallOption option = ps.installOption;
        for (Executor executor : executors) {
            int exec = executor.exec(ps, option, userId);
            Slog.d(TAG, "uninstallPackageAsUser: " + executor.getClass().getSimpleName() + " exec: " + exec);
            if (exec != 0) {
                return exec;
            }
        }
        return 0;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
    public int updatePackage(BPackageSettings ps) {
        List<Executor> executors = new ArrayList<>();
        executors.add(new CreatePackageExecutor());
        executors.add(new CopyExecutor());
        InstallOption option = ps.installOption;
        for (Executor executor : executors) {
            int exec = executor.exec(ps, option, -1);
            if (exec != 0) {
                return exec;
            }
        }
        return 0;
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
    }
}
