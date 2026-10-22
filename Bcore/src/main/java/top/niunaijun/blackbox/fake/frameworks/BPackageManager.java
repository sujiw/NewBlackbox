package top.niunaijun.blackbox.fake.frameworks;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import java.io.File;
import java.util.Collections;
import java.util.List;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.system.JarConfig;
import top.niunaijun.blackbox.core.system.ServiceManager;
import top.niunaijun.blackbox.core.system.pm.IBPackageManagerService;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.entity.pm.InstallResult;
import top.niunaijun.blackbox.entity.pm.InstalledPackage;
import top.niunaijun.blackbox.utils.TransactionThrottler;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/frameworks/BPackageManager.class */
public class BPackageManager extends BlackManager<IBPackageManagerService> {
    private final TransactionThrottler transactionThrottler = new TransactionThrottler();
    private static final BPackageManager sPackageManager = new BPackageManager();
    private static volatile boolean sIsFindingApkPath = false;

    public static BPackageManager get() {
        return sPackageManager;
    }

    public void resetTransactionThrottler() {
        this.transactionThrottler.reset();
        Log.d(BlackManager.TAG, "Transaction throttler reset");
    }

    private boolean shouldUseFallbackMode() {
        return this.transactionThrottler.getFailureCount() >= 2 || !isServiceHealthy();
    }

    public void forceReinitialize() {
        Log.d(BlackManager.TAG, "Force reinitializing PackageManager service");
        clearServiceCache();
        resetTransactionThrottler();
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                Log.d(BlackManager.TAG, "Successfully reinitialized PackageManager service");
            } else {
                Log.w(BlackManager.TAG, "Failed to reinitialize PackageManager service");
            }
        } catch (Exception e) {
            Log.e(BlackManager.TAG, "Error during service reinitialization", e);
        }
    }

    public IBPackageManagerService getServiceWithFallback() {
        IBPackageManagerService service = getService();
        if (service == null) {
            Log.w(BlackManager.TAG, "PackageManager service is null, attempting reinitialization");
            forceReinitialize();
            service = getService();
        }
        return service;
    }

    @Override // top.niunaijun.blackbox.fake.frameworks.BlackManager
    protected String getServiceName() {
        return ServiceManager.PACKAGE_MANAGER;
    }

    public Intent getLaunchIntentForPackage(String packageName, int userId) {
        if (shouldUseFallbackMode()) {
            Log.w(BlackManager.TAG, "Using fallback launch intent for " + packageName + " due to service failures");
            return createFallbackLaunchIntent(packageName);
        }
        Intent intentToResolve = new Intent("android.intent.action.MAIN");
        intentToResolve.addCategory("android.intent.category.INFO");
        intentToResolve.setPackage(packageName);
        List<ResolveInfo> ris = queryIntentActivities(intentToResolve, 0, intentToResolve.resolveTypeIfNeeded(BlackBoxCore.getContext().getContentResolver()), userId);
        if (ris == null || ris.size() <= 0) {
            intentToResolve.removeCategory("android.intent.category.INFO");
            intentToResolve.addCategory("android.intent.category.LAUNCHER");
            intentToResolve.setPackage(packageName);
            ris = queryIntentActivities(intentToResolve, 0, intentToResolve.resolveTypeIfNeeded(BlackBoxCore.getContext().getContentResolver()), userId);
        }
        if (ris == null || ris.size() <= 0) {
            return null;
        }
        Intent intent = new Intent(intentToResolve);
        intent.setFlags(268435456);
        intent.setClassName(ris.get(0).activityInfo.packageName, ris.get(0).activityInfo.name);
        return intent;
    }

    private Intent createFallbackLaunchIntent(String packageName) {
        try {
            Intent intent = BlackBoxCore.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.setFlags(268435456);
                return intent;
            }
        } catch (Exception e) {
            Log.w(BlackManager.TAG, "Fallback launch intent failed for " + packageName, e);
        }
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent2.setPackage(packageName);
        intent2.setFlags(268435456);
        return intent2;
    }

    public ResolveInfo resolveService(Intent intent, int flags, String resolvedType, int userId) {
        if (this.transactionThrottler.shouldThrottle()) {
            Log.w(BlackManager.TAG, "Throttling resolveService due to recent failures");
            return null;
        }
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                ResolveInfo result = service.resolveService(intent, flags, resolvedType, userId);
                this.transactionThrottler.reset();
                return result;
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning null for resolveService");
            return null;
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during resolveService, clearing service and retrying", e);
            this.transactionThrottler.recordFailure();
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    ResolveInfo result2 = service2.resolveService(intent, flags, resolvedType, userId);
                    this.transactionThrottler.reset();
                    return result2;
                }
                return null;
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for resolveService", retryException);
                this.transactionThrottler.recordFailure();
                return null;
            }
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in resolveService", e2);
            this.transactionThrottler.recordFailure();
            return null;
        } catch (Exception e3) {
            Log.e(BlackManager.TAG, "Unexpected error in resolveService", e3);
            this.transactionThrottler.recordFailure();
            return null;
        }
    }

    public ResolveInfo resolveActivity(Intent intent, int flags, String resolvedType, int userId) {
        if (this.transactionThrottler.shouldThrottle()) {
            Log.w(BlackManager.TAG, "Throttling resolveActivity due to recent failures");
            return null;
        }
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                ResolveInfo result = service.resolveActivity(intent, flags, resolvedType, userId);
                this.transactionThrottler.reset();
                return result;
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning null for resolveActivity");
            return null;
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during resolveActivity, clearing service and retrying", e);
            this.transactionThrottler.recordFailure();
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    ResolveInfo result2 = service2.resolveActivity(intent, flags, resolvedType, userId);
                    this.transactionThrottler.reset();
                    return result2;
                }
                return null;
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for resolveActivity", retryException);
                this.transactionThrottler.recordFailure();
                return null;
            }
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in resolveActivity", e2);
            this.transactionThrottler.recordFailure();
            return null;
        } catch (Exception e3) {
            Log.e(BlackManager.TAG, "Unexpected error in resolveActivity", e3);
            this.transactionThrottler.recordFailure();
            return null;
        }
    }

    public ProviderInfo resolveContentProvider(String authority, int flags, int userId) {
        if (this.transactionThrottler.shouldThrottle()) {
            Log.w(BlackManager.TAG, "Throttling resolveContentProvider due to recent failures");
            return null;
        }
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                ProviderInfo result = service.resolveContentProvider(authority, flags, userId);
                this.transactionThrottler.reset();
                return result;
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning null for resolveContentProvider");
            return null;
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during resolveContentProvider, clearing service and retrying", e);
            this.transactionThrottler.recordFailure();
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    ProviderInfo result2 = service2.resolveContentProvider(authority, flags, userId);
                    this.transactionThrottler.reset();
                    return result2;
                }
                return null;
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for resolveContentProvider", retryException);
                this.transactionThrottler.recordFailure();
                return null;
            }
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in resolveContentProvider", e2);
            this.transactionThrottler.recordFailure();
            return null;
        } catch (Exception e3) {
            Log.e(BlackManager.TAG, "Unexpected error in resolveContentProvider", e3);
            this.transactionThrottler.recordFailure();
            return null;
        }
    }

    public ResolveInfo resolveIntent(Intent intent, String resolvedType, int flags, int userId) {
        try {
            return getService().resolveIntent(intent, resolvedType, flags, userId);
        } catch (RemoteException e) {
            crash(e);
            return null;
        }
    }

    public ApplicationInfo getApplicationInfo(String packageName, int flags, int userId) {
        try {
            IBPackageManagerService service = getServiceWithFallback();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getApplicationInfo, using fallback");
                return createFallbackApplicationInfo(packageName, flags, userId);
            }
            return service.getApplicationInfo(packageName, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getApplicationInfo for " + packageName, e);
            return createFallbackApplicationInfo(packageName, flags, userId);
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getApplicationInfo for " + packageName, e2);
            return createFallbackApplicationInfo(packageName, flags, userId);
        }
    }

    public PackageInfo getPackageInfo(String packageName, int flags, int userId) {
        try {
            IBPackageManagerService service = getServiceWithFallback();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getPackageInfo, using fallback");
                return createFallbackPackageInfo(packageName, flags, userId);
            }
            return service.getPackageInfo(packageName, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getPackageInfo for " + packageName, e);
            return createFallbackPackageInfo(packageName, flags, userId);
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getPackageInfo for " + packageName, e2);
            return createFallbackPackageInfo(packageName, flags, userId);
        }
    }

    public ServiceInfo getServiceInfo(ComponentName component, int flags, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getServiceInfo, returning null");
                return null;
            }
            return service.getServiceInfo(component, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getServiceInfo for " + component, e);
            return null;
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getServiceInfo for " + component, e2);
            return null;
        }
    }

    public ActivityInfo getReceiverInfo(ComponentName componentName, int flags, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getReceiverInfo, returning null");
                return null;
            }
            return service.getReceiverInfo(componentName, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getReceiverInfo for " + componentName, e);
            return null;
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getReceiverInfo for " + componentName, e2);
            return null;
        }
    }

    public ActivityInfo getActivityInfo(ComponentName component, int flags, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getActivityInfo, returning null");
                return null;
            }
            return service.getActivityInfo(component, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getActivityInfo for " + component, e);
            return null;
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getActivityInfo for " + component, e2);
            return null;
        }
    }

    public ProviderInfo getProviderInfo(ComponentName component, int flags, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service == null) {
                Log.w(BlackManager.TAG, "PackageManager service is null for getProviderInfo, returning null");
                return null;
            }
            return service.getProviderInfo(component, flags, userId);
        } catch (RemoteException e) {
            Log.e(BlackManager.TAG, "RemoteException in getProviderInfo for " + component, e);
            return null;
        } catch (Exception e2) {
            Log.e(BlackManager.TAG, "Exception in getProviderInfo for " + component, e2);
            return null;
        }
    }

    public List<ResolveInfo> queryIntentActivities(Intent intent, int flags, String resolvedType, int userId) {
        if (this.transactionThrottler.shouldThrottle()) {
            Log.w(BlackManager.TAG, "Throttling queryIntentActivities due to recent failures");
            return Collections.emptyList();
        }
        if (this.transactionThrottler.getFailureCount() >= 2) {
            Log.w(BlackManager.TAG, "Too many failures, returning empty list for queryIntentActivities");
            return Collections.emptyList();
        }
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                List<ResolveInfo> result = service.queryIntentActivities(intent, flags, resolvedType, userId);
                this.transactionThrottler.reset();
                return result;
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning empty list for queryIntentActivities");
            return Collections.emptyList();
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during queryIntentActivities, clearing cache and retrying", e);
            this.transactionThrottler.recordFailure();
            clearServiceCache();
            if (this.transactionThrottler.getFailureCount() < 3) {
                try {
                    IBPackageManagerService service2 = getService();
                    if (service2 != null) {
                        List<ResolveInfo> result2 = service2.queryIntentActivities(intent, flags, resolvedType, userId);
                        this.transactionThrottler.reset();
                        return result2;
                    }
                } catch (Exception retryException) {
                    Log.e(BlackManager.TAG, "Retry failed for queryIntentActivities", retryException);
                    this.transactionThrottler.recordFailure();
                }
            } else {
                Log.w(BlackManager.TAG, "Skipping retry due to too many failures");
            }
            return Collections.emptyList();
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in queryIntentActivities", e2);
            this.transactionThrottler.recordFailure();
            crash(e2);
            return Collections.emptyList();
        }
    }

    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags, String resolvedType, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                return service.queryBroadcastReceivers(intent, flags, resolvedType, userId);
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning empty list for queryBroadcastReceivers");
            return Collections.emptyList();
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during queryBroadcastReceivers, clearing cache and retrying", e);
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    return service2.queryBroadcastReceivers(intent, flags, resolvedType, userId);
                }
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for queryBroadcastReceivers", retryException);
            }
            return Collections.emptyList();
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in queryBroadcastReceivers", e2);
            crash(e2);
            return Collections.emptyList();
        }
    }

    public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags, int userId) {
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                return service.queryContentProviders(processName, uid, flags, userId);
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning empty list for queryContentProviders");
            return Collections.emptyList();
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during queryContentProviders, clearing cache and retrying", e);
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    return service2.queryContentProviders(processName, uid, flags, userId);
                }
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for queryContentProviders", retryException);
            }
            return Collections.emptyList();
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in queryContentProviders", e2);
            crash(e2);
            return Collections.emptyList();
        }
    }

    public InstallResult installPackageAsUser(String file, InstallOption option, int userId) {
        try {
            return getService().installPackageAsUser(file, option, userId);
        } catch (RemoteException e) {
            crash(e);
            return null;
        }
    }

    public List<ApplicationInfo> getInstalledApplications(int flags, int userId) {
        try {
            return getService().getInstalledApplications(flags, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<PackageInfo> getInstalledPackages(int flags, int userId) {
        try {
            return getService().getInstalledPackages(flags, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void clearPackage(String packageName, int userId) {
        try {
            getService().clearPackage(packageName, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void stopPackage(String packageName, int userId) {
        try {
            getService().stopPackage(packageName, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void uninstallPackageAsUser(String packageName, int userId) {
        try {
            getService().uninstallPackageAsUser(packageName, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void uninstallPackage(String packageName) {
        try {
            getService().uninstallPackage(packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isInstalled(String packageName, int userId) {
        if (shouldUseFallbackMode()) {
            Log.w(BlackManager.TAG, "Using fallback isInstalled check for " + packageName + " due to service failures");
            return isInstalledFallback(packageName);
        }
        try {
            IBPackageManagerService service = getService();
            if (service != null) {
                boolean result = service.isInstalled(packageName, userId);
                this.transactionThrottler.reset();
                return result;
            }
            Log.w(BlackManager.TAG, "PackageManager service is null, returning false for isInstalled check");
            return false;
        } catch (DeadObjectException e) {
            Log.w(BlackManager.TAG, "PackageManager service died during isInstalled check, clearing service and retrying", e);
            this.transactionThrottler.recordFailure();
            clearServiceCache();
            try {
                IBPackageManagerService service2 = getService();
                if (service2 != null) {
                    boolean result2 = service2.isInstalled(packageName, userId);
                    this.transactionThrottler.reset();
                    return result2;
                }
                return false;
            } catch (Exception retryException) {
                Log.e(BlackManager.TAG, "Retry failed for isInstalled check", retryException);
                this.transactionThrottler.recordFailure();
                return false;
            }
        } catch (RemoteException e2) {
            Log.e(BlackManager.TAG, "RemoteException in isInstalled check", e2);
            this.transactionThrottler.recordFailure();
            return false;
        } catch (Exception e3) {
            Log.e(BlackManager.TAG, "Unexpected error in isInstalled check", e3);
            this.transactionThrottler.recordFailure();
            return false;
        }
    }

    private boolean isInstalledFallback(String packageName) {
        try {
            BlackBoxCore.getContext().getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (Exception e) {
            Log.d(BlackManager.TAG, "Fallback isInstalled check failed for " + packageName + ", assuming not installed");
            if (packageName != null) {
                if (packageName.equals("com.media.bestrecorder.audiorecorder") || packageName.startsWith("top.niunaijun.blackbox")) {
                    Log.w(BlackManager.TAG, "Returning true for known app " + packageName + " despite fallback failure");
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public List<InstalledPackage> getInstalledPackagesAsUser(int userId) {
        try {
            return getService().getInstalledPackagesAsUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public String[] getPackagesForUid(int uid) {
        try {
            return getService().getPackagesForUid(uid, BActivityThread.getUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private void crash(Throwable e) {
        e.printStackTrace();
    }

    private ApplicationInfo createFallbackApplicationInfo(String packageName, int flags, int userId) {
        Log.w(BlackManager.TAG, "Creating fallback ApplicationInfo for " + packageName);
        ApplicationInfo info = new ApplicationInfo();
        info.packageName = packageName;
        info.flags = flags;
        info.uid = 0;
        String apkPath = findActualApkPath(packageName);
        if (apkPath != null) {
            info.sourceDir = apkPath;
            info.publicSourceDir = apkPath;
        } else {
            Log.w(BlackManager.TAG, "No APK found for " + packageName + ", using null paths to prevent I/O errors");
            info.sourceDir = null;
            info.publicSourceDir = null;
        }
        info.dataDir = "/data/data/" + packageName;
        info.nativeLibraryDir = "/data/app-lib/" + packageName;
        info.metaData = new Bundle();
        info.splitNames = new String[0];
        info.flags |= JarConfig.MAX_BUFFER_SIZE;
        info.flags |= 4194304;
        return info;
    }

    private String findActualApkPath(String packageName) {
        boolean z;
        if (sIsFindingApkPath) {
            Log.w(BlackManager.TAG, "findActualApkPath called recursively, returning null to prevent infinite loop.");
            return null;
        }
        sIsFindingApkPath = true;
        try {
            Log.d(BlackManager.TAG, "Skipping PackageManager call to prevent recursion for " + packageName);
            String[] commonPaths = {"/data/app/~~*/" + packageName + "-*/base.apk", "/data/app/~~*/" + packageName + "*/base.apk", "/data/app/" + packageName + "-1/base.apk", "/data/app/" + packageName + "-2/base.apk", "/data/app/" + packageName + "/base.apk", "/system/app/" + packageName + ".apk", "/system/priv-app/" + packageName + ".apk", "/system_ext/app/" + packageName + ".apk", "/product/app/" + packageName + ".apk", "/vendor/app/" + packageName + ".apk"};
            for (String path : commonPaths) {
                if (isValidApkPath(path)) {
                    Log.d(BlackManager.TAG, "Found existing APK at: " + path);
                    return path;
                }
            }
            String hashBasedPath = findHashBasedApkPath(packageName);
            if (hashBasedPath != null) {
                Log.d(BlackManager.TAG, "Found hash-based APK at: " + hashBasedPath);
                return hashBasedPath;
            }
            Log.w(BlackManager.TAG, "No existing APK found for " + packageName + ", using null path");
            return null;
        } finally {
            sIsFindingApkPath = false;
        }
    }

    private String findHashBasedApkPath(String packageName) {
        File[] hashDirs;
        File[] packageDirs;
        try {
            File dataAppDir = new File("/data/app");
            if (!dataAppDir.exists() || !dataAppDir.isDirectory() || (hashDirs = dataAppDir.listFiles((dir, name) -> {
                return name.startsWith("~~") && name.endsWith("==");
            })) == null) {
                return null;
            }
            for (File hashDir : hashDirs) {
                if (hashDir.isDirectory() && (packageDirs = hashDir.listFiles((dir2, name2) -> {
                    return name2.startsWith(packageName);
                })) != null) {
                    for (File packageDir : packageDirs) {
                        if (packageDir.isDirectory()) {
                            File baseApk = new File(packageDir, "base.apk");
                            if (isValidApkPath(baseApk.getAbsolutePath())) {
                                return baseApk.getAbsolutePath();
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            Log.d(BlackManager.TAG, "Error searching for hash-based APK path for " + packageName + ": " + e.getMessage());
            return null;
        }
    }

    private boolean isValidApkPath(String path) {
        try {
            if (path.contains("*")) {
                return false;
            }
            File apkFile = new File(path);
            if (!apkFile.exists()) {
                return false;
            }
            if (!apkFile.canRead()) {
                Log.d(BlackManager.TAG, "APK file not readable: " + path);
                return false;
            }
            long fileSize = apkFile.length();
            if (fileSize < 1024) {
                Log.d(BlackManager.TAG, "APK file too small: " + path + " (size: " + fileSize + ")");
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.d(BlackManager.TAG, "Error checking APK path " + path + ": " + e.getMessage());
            return false;
        }
    }

    private PackageInfo createFallbackPackageInfo(String packageName, int flags, int userId) {
        Log.w(BlackManager.TAG, "Creating fallback PackageInfo for " + packageName);
        PackageInfo info = new PackageInfo();
        info.packageName = packageName;
        info.versionCode = 1;
        info.versionName = "1.0";
        info.applicationInfo = createFallbackApplicationInfo(packageName, flags, userId);
        info.firstInstallTime = System.currentTimeMillis();
        info.lastUpdateTime = System.currentTimeMillis();
        info.installLocation = 0;
        info.gids = new int[0];
        info.splitNames = new String[0];
        info.signatures = new Signature[0];
        return info;
    }
}
