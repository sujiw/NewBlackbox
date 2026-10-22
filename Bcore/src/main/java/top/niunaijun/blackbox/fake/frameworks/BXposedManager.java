package top.niunaijun.blackbox.fake.frameworks;

import android.os.RemoteException;
import java.util.Collections;
import java.util.List;
import top.niunaijun.blackbox.core.system.ServiceManager;
import top.niunaijun.blackbox.core.system.pm.IBXposedManagerService;
import top.niunaijun.blackbox.entity.pm.InstalledModule;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/frameworks/BXposedManager.class */
public class BXposedManager extends BlackManager<IBXposedManagerService> {
    private static final BXposedManager sXposedManager = new BXposedManager();

    public static BXposedManager get() {
        return sXposedManager;
    }

    @Override // top.niunaijun.blackbox.fake.frameworks.BlackManager
    protected String getServiceName() {
        return ServiceManager.XPOSED_MANAGER;
    }

    public boolean isXPEnable() {
        try {
            return getService().isXPEnable();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setXPEnable(boolean enable) {
        try {
            getService().setXPEnable(enable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isModuleEnable(String packageName) {
        try {
            return getService().isModuleEnable(packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setModuleEnable(String packageName, boolean enable) {
        try {
            getService().setModuleEnable(packageName, enable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<InstalledModule> getInstalledModules() {
        try {
            return getService().getInstalledModules();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
