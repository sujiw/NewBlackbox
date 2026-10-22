package top.niunaijun.blackbox.core.system;

import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.accounts.BAccountManagerService;
import top.niunaijun.blackbox.core.system.am.BActivityManagerService;
import top.niunaijun.blackbox.core.system.am.BJobManagerService;
import top.niunaijun.blackbox.core.system.location.BLocationManagerService;
import top.niunaijun.blackbox.core.system.notification.BNotificationManagerService;
import top.niunaijun.blackbox.core.system.os.BStorageManagerService;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.core.system.pm.BXposedManagerService;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/ServiceManager.class */
public class ServiceManager {
    private static ServiceManager sServiceManager = null;
    public static final String ACTIVITY_MANAGER = "activity_manager";
    public static final String JOB_MANAGER = "job_manager";
    public static final String PACKAGE_MANAGER = "package_manager";
    public static final String STORAGE_MANAGER = "storage_manager";
    public static final String XPOSED_MANAGER = "xposed_manager";
    public static final String ACCOUNT_MANAGER = "account_manager";
    public static final String LOCATION_MANAGER = "location_manager";
    public static final String NOTIFICATION_MANAGER = "notification_manager";
    private final Map<String, IBinder> mCaches = new HashMap();

    public static ServiceManager get() {
        if (sServiceManager == null) {
            synchronized (ServiceManager.class) {
                if (sServiceManager == null) {
                    sServiceManager = new ServiceManager();
                }
            }
        }
        return sServiceManager;
    }

    public static IBinder getService(String name) {
        return get().getServiceInternal(name);
    }

    private ServiceManager() {
        this.mCaches.put(ACTIVITY_MANAGER, BActivityManagerService.get());
        this.mCaches.put(JOB_MANAGER, BJobManagerService.get());
        this.mCaches.put(PACKAGE_MANAGER, BPackageManagerService.get());
        this.mCaches.put(STORAGE_MANAGER, BStorageManagerService.get());
        this.mCaches.put(XPOSED_MANAGER, BXposedManagerService.get());
        this.mCaches.put(ACCOUNT_MANAGER, BAccountManagerService.get());
        this.mCaches.put(LOCATION_MANAGER, BLocationManagerService.get());
        this.mCaches.put(NOTIFICATION_MANAGER, BNotificationManagerService.get());
    }

    public IBinder getServiceInternal(String name) {
        return this.mCaches.get(name);
    }

    public static void initBlackManager() {
        BlackBoxCore.get().getService(ACTIVITY_MANAGER);
        BlackBoxCore.get().getService(JOB_MANAGER);
        BlackBoxCore.get().getService(PACKAGE_MANAGER);
        BlackBoxCore.get().getService(STORAGE_MANAGER);
        BlackBoxCore.get().getService(XPOSED_MANAGER);
        BlackBoxCore.get().getService(ACCOUNT_MANAGER);
        BlackBoxCore.get().getService(LOCATION_MANAGER);
        BlackBoxCore.get().getService(NOTIFICATION_MANAGER);
    }
}
