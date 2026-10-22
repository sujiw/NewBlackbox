package top.niunaijun.blackbox.core.system.pm;

import android.content.pm.ApplicationInfo;
import android.os.Parcel;
import androidx.core.util.AtomicFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.entity.pm.InstalledModule;
import top.niunaijun.blackbox.entity.pm.XposedConfig;
import top.niunaijun.blackbox.utils.CloseUtils;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.compat.XposedParserCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BXposedManagerService.class */
public class BXposedManagerService extends IBXposedManagerService.Stub implements ISystemService, PackageMonitor {
    private static final BXposedManagerService sService = new BXposedManagerService();
    private XposedConfig mXposedConfig;
    private BPackageManagerService mPms;
    private final Object mLock = new Object();
    private final Map<String, InstalledModule> mCacheModule = new HashMap();

    public static BXposedManagerService get() {
        return sService;
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        loadModuleStateLr();
        this.mPms = BPackageManagerService.get();
        this.mPms.addPackageMonitor(this);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
    public boolean isXPEnable() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mXposedConfig.enable;
        }
        return z;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
    public void setXPEnable(boolean enable) {
        synchronized (this.mLock) {
            this.mXposedConfig.enable = enable;
            saveModuleStateLw();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
    public boolean isModuleEnable(String packageName) {
        boolean z;
        synchronized (this.mLock) {
            Boolean enable = this.mXposedConfig.moduleState.get(packageName);
            z = enable != null && enable.booleanValue();
        }
        return z;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
    public void setModuleEnable(String packageName, boolean enable) {
        synchronized (this.mLock) {
            if (this.mPms.isInstalled(packageName, -4)) {
                this.mXposedConfig.moduleState.put(packageName, Boolean.valueOf(enable));
                saveModuleStateLw();
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
    public List<InstalledModule> getInstalledModules() {
        ArrayList<InstalledModule> installedModules;
        List<ApplicationInfo> installedApplications = this.mPms.getInstalledApplications(128, -4);
        synchronized (this.mCacheModule) {
            for (ApplicationInfo installedApplication : installedApplications) {
                if (!this.mCacheModule.containsKey(installedApplication.packageName)) {
                    InstalledModule installedModule = XposedParserCompat.parseModule(installedApplication);
                    if (installedModule != null) {
                        this.mCacheModule.put(installedApplication.packageName, installedModule);
                    }
                }
            }
            installedModules = new ArrayList<>(this.mCacheModule.values());
            for (InstalledModule installedModule2 : installedModules) {
                installedModule2.enable = isModuleEnable(installedModule2.packageName);
            }
        }
        return installedModules;
    }

    private void loadModuleStateLr() {
        File xpModuleConf = BEnvironment.getXPModuleConf();
        if (!xpModuleConf.exists()) {
            this.mXposedConfig = new XposedConfig();
            saveModuleStateLw();
            return;
        }
        Parcel parcel = null;
        try {
            try {
                parcel = FileUtils.readToParcel(xpModuleConf);
                this.mXposedConfig = new XposedConfig(parcel);
                if (parcel != null) {
                    parcel.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (parcel != null) {
                    parcel.recycle();
                }
            }
        } catch (Throwable th) {
            if (parcel != null) {
                parcel.recycle();
            }
            throw th;
        }
    }

    private void saveModuleStateLw() {
        Parcel parcel = Parcel.obtain();
        AtomicFile atomicFile = new AtomicFile(BEnvironment.getXPModuleConf());
        FileOutputStream fileOutputStream = null;
        try {
            try {
                this.mXposedConfig.writeToParcel(parcel, 0);
                parcel.setDataPosition(0);
                fileOutputStream = atomicFile.startWrite();
                FileUtils.writeParcelToOutput(parcel, fileOutputStream);
                atomicFile.finishWrite(fileOutputStream);
                parcel.recycle();
                CloseUtils.close(fileOutputStream);
            } catch (Exception e) {
                atomicFile.failWrite(fileOutputStream);
                parcel.recycle();
                CloseUtils.close(fileOutputStream);
            }
        } catch (Throwable th) {
            parcel.recycle();
            CloseUtils.close(fileOutputStream);
            throw th;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageUninstalled(String packageName, boolean removeApp, int userId) {
        if (userId != -4 && userId != -1) {
            return;
        }
        synchronized (this.mCacheModule) {
            this.mCacheModule.remove(packageName);
        }
        synchronized (this.mLock) {
            this.mXposedConfig.moduleState.remove(packageName);
            saveModuleStateLw();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageInstalled(String packageName, int userId) {
        if (userId != -4 && userId != -1) {
            return;
        }
        synchronized (this.mCacheModule) {
            this.mCacheModule.remove(packageName);
        }
        synchronized (this.mLock) {
            this.mXposedConfig.moduleState.put(packageName, false);
            saveModuleStateLw();
        }
    }
}
