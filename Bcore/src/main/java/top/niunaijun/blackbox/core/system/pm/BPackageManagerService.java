package top.niunaijun.blackbox.core.system.pm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.GmsCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.core.system.ProcessRecord;

import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.entity.pm.InstallResult;
import top.niunaijun.blackbox.entity.pm.InstalledPackage;
import top.niunaijun.blackbox.utils.AbiUtils;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.ShellUtils;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.PackageParserCompat;
import top.niunaijun.blackbox.utils.compat.XposedParserCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackageManagerService.class */
public class BPackageManagerService extends IBPackageManagerService.Stub implements ISystemService {
    public static final String TAG = "BPackageManagerService";
    public static BPackageManagerService sService = new BPackageManagerService();
    private final Settings mSettings = new Settings();
    private final List<PackageMonitor> mPackageMonitors = new ArrayList();
    final Map<String, BPackageSettings> mPackages = this.mSettings.mPackages;
    final Object mInstallLock = new Object();
    private final BroadcastReceiver mPackageChangedHandler = new BroadcastReceiver() { // from class: top.niunaijun.blackbox.core.system.pm.BPackageManagerService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    BPackageManagerService.this.mSettings.scanPackage();
                }
            }
        }
    };
    private final ComponentResolver mComponentResolver = new ComponentResolver();

    public static BPackageManagerService get() {
        return sService;
    }

    public BPackageManagerService() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        BlackBoxCore.getContext().registerReceiver(this.mPackageChangedHandler, filter);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ApplicationInfo getApplicationInfo(String packageName, int flags, int userId) {
        if (Objects.equals(packageName, BlackBoxCore.getHostPkg())) {
            try {
                return BlackBoxCore.getPackageManager().getApplicationInfo(packageName, flags);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        int flags2 = updateFlags(flags, userId);
        synchronized (this.mPackages) {
            BPackageSettings ps = this.mPackages.get(packageName);
            if (ps != null) {
                BPackage p = ps.pkg;
                return PackageManagerCompat.generateApplicationInfo(p, flags2, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ResolveInfo resolveService(Intent intent, int flags, String resolvedType, int userId) {
        List<ResolveInfo> query = queryIntentServicesInternal(intent, resolvedType, flags, userId);
        if (query != null && query.size() >= 1) {
            return query.get(0);
        }
        return null;
    }

    private List<ResolveInfo> queryIntentServicesInternal(Intent intent, String resolvedType, int flags, int userId) {
        ComponentName comp = intent.getComponent();
        if (comp == null && intent.getSelector() != null) {
            intent = intent.getSelector();
            comp = intent.getComponent();
        }
        if (comp != null) {
            List<ResolveInfo> list = new ArrayList<>(1);
            ServiceInfo si = getServiceInfo(comp, flags, userId);
            if (si != null) {
                ResolveInfo ri = new ResolveInfo();
                ri.serviceInfo = si;
                list.add(ri);
            }
            return list;
        }
        synchronized (this.mPackages) {
            String pkgName = intent.getPackage();
            if (pkgName != null) {
                BPackageSettings bPackageSettings = this.mPackages.get(pkgName);
                if (bPackageSettings != null) {
                    BPackage pkg = bPackageSettings.pkg;
                    return this.mComponentResolver.queryServices(intent, resolvedType, flags, pkg.services, userId);
                }
                return Collections.emptyList();
            }
            return this.mComponentResolver.queryServices(intent, resolvedType, flags, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ResolveInfo resolveActivity(Intent intent, int flags, String resolvedType, int userId) {
        List<ResolveInfo> resolves = queryIntentActivities(intent, resolvedType, flags, userId);
        return chooseBestActivity(intent, resolvedType, flags, resolves);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ProviderInfo resolveContentProvider(String authority, int flags, int userId) {
        return this.mComponentResolver.queryProvider(authority, flags, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ResolveInfo resolveIntent(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> resolves = queryIntentActivities(intent, resolvedType, flags, userId);
        return chooseBestActivity(intent, resolvedType, flags, resolves);
    }

    private ResolveInfo chooseBestActivity(Intent intent, String resolvedType, int flags, List<ResolveInfo> query) {
        if (query != null) {
            int N = query.size();
            if (N == 1) {
                return query.get(0);
            }
            if (N > 1) {
                ResolveInfo r0 = query.get(0);
                ResolveInfo r1 = query.get(1);
                if (r0.priority != r1.priority || r0.preferredOrder != r1.preferredOrder || r0.isDefault != r1.isDefault) {
                    return query.get(0);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private List<ResolveInfo> queryIntentActivities(Intent intent, String resolvedType, int flags, int userId) {
        List<ResolveInfo> listQueryActivities;
        ComponentName comp = intent.getComponent();
        if (comp == null && intent.getSelector() != null) {
            intent = intent.getSelector();
            comp = intent.getComponent();
        }
        if (comp != null) {
            List<ResolveInfo> list = new ArrayList<>(1);
            ActivityInfo ai = getActivity(comp, flags, userId);
            if (ai != null) {
                ResolveInfo ri = new ResolveInfo();
                ri.activityInfo = ai;
                list.add(ri);
                return list;
            }
        }
        synchronized (this.mPackages) {
            listQueryActivities = this.mComponentResolver.queryActivities(intent, resolvedType, flags, userId);
        }
        return listQueryActivities;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<ResolveInfo> queryIntentServices(Intent intent, int flags, int userId) {
        String resolvedType = intent.resolveTypeIfNeeded(BlackBoxCore.getContext().getContentResolver());
        return queryIntentServicesInternal(intent, resolvedType, flags, userId);
    }

    private ActivityInfo getActivity(ComponentName component, int flags, int userId) {
        int flags2 = updateFlags(flags, userId);
        synchronized (this.mPackages) {
            BPackage.Activity a = this.mComponentResolver.getActivity(component);
            if (a != null) {
                BPackageSettings ps = this.mSettings.mPackages.get(component.getPackageName());
                if (ps == null) {
                    return null;
                }
                return PackageManagerCompat.generateActivityInfo(a, flags2, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public PackageInfo getPackageInfo(String packageName, int flags, int userId) {
        BPackageSettings ps;
        if (Objects.equals(packageName, BlackBoxCore.getHostPkg())) {
            try {
                return BlackBoxCore.getPackageManager().getPackageInfo(packageName, flags);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        int flags2 = updateFlags(flags, userId);
        synchronized (this.mPackages) {
            ps = this.mPackages.get(packageName);
        }
        if (ps != null) {
            return PackageManagerCompat.generatePackageInfo(ps, flags2, ps.readUserState(userId), userId);
        }
        return null;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ServiceInfo getServiceInfo(ComponentName component, int flags, int userId) {
        synchronized (this.mPackages) {
            BPackage.Service s = this.mComponentResolver.getService(component);
            if (s != null) {
                BPackageSettings ps = this.mPackages.get(component.getPackageName());
                if (ps == null) {
                    return null;
                }
                return PackageManagerCompat.generateServiceInfo(s, flags, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ActivityInfo getReceiverInfo(ComponentName component, int flags, int userId) {
        synchronized (this.mPackages) {
            BPackage.Activity a = this.mComponentResolver.getReceiver(component);
            if (a != null) {
                BPackageSettings ps = this.mPackages.get(component.getPackageName());
                if (ps == null) {
                    return null;
                }
                return PackageManagerCompat.generateActivityInfo(a, flags, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ActivityInfo getActivityInfo(ComponentName component, int flags, int userId) {
        synchronized (this.mPackages) {
            BPackage.Activity a = this.mComponentResolver.getActivity(component);
            if (a != null) {
                BPackageSettings ps = this.mPackages.get(component.getPackageName());
                if (ps == null) {
                    return null;
                }
                return PackageManagerCompat.generateActivityInfo(a, flags, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public ProviderInfo getProviderInfo(ComponentName component, int flags, int userId) {
        synchronized (this.mPackages) {
            BPackage.Provider p = this.mComponentResolver.getProvider(component);
            if (p != null) {
                BPackageSettings ps = this.mPackages.get(component.getPackageName());
                if (ps == null) {
                    return null;
                }
                return PackageManagerCompat.generateProviderInfo(p, flags, ps.readUserState(userId), userId);
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<ApplicationInfo> getInstalledApplications(int flags, int userId) {
        return getInstalledApplicationsListInternal(flags, userId, Binder.getCallingUid());
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<PackageInfo> getInstalledPackages(int flags, int userId) {
        ArrayList arrayList;
        Binder.getCallingUid();
        synchronized (this.mPackages) {
            ArrayList<PackageInfo> list = new ArrayList<>(this.mPackages.size());
            for (BPackageSettings ps : this.mPackages.values()) {
                PackageInfo pi = getPackageInfo(ps.pkg.packageName, flags, userId);
                if (pi != null) {
                    list.add(pi);
                }
            }
            arrayList = new ArrayList(list);
        }
        return arrayList;
    }

    private List<ApplicationInfo> getInstalledApplicationsListInternal(int flags, int userId, int callingUid) {
        ArrayList<ApplicationInfo> list;
        synchronized (this.mPackages) {
            list = new ArrayList<>(this.mPackages.size());
            Collection<BPackageSettings> packageSettings = this.mPackages.values();
            for (BPackageSettings ps : packageSettings) {
                ApplicationInfo ai = PackageManagerCompat.generateApplicationInfo(ps.pkg, flags, ps.readUserState(userId), userId);
                if (ai != null) {
                    list.add(ai);
                }
            }
        }
        return list;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<ResolveInfo> queryIntentActivities(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
        String pkgName = intent.getPackage();
        ComponentName comp = intent.getComponent();
        if (comp == null && intent.getSelector() != null) {
            intent = intent.getSelector();
            comp = intent.getComponent();
        }
        if (comp != null) {
            List<ResolveInfo> list = new ArrayList<>(1);
            ActivityInfo ai = getActivityInfo(comp, flags, userId);
            if (ai != null) {
                ResolveInfo ri = new ResolveInfo();
                ri.activityInfo = ai;
                list.add(ri);
            }
            return list;
        }
        synchronized (this.mPackages) {
            try {
                if (pkgName != null) {
                    BPackageSettings bPackageSettings = this.mPackages.get(pkgName);
                    List<ResolveInfo> result = null;
                    if (bPackageSettings != null) {
                        BPackage pkg = bPackageSettings.pkg;
                        result = this.mComponentResolver.queryActivities(intent, resolvedType, flags, pkg.activities, userId);
                    }
                    if ((result == null || result.size() == 0) && result == null) {
                        result = new ArrayList();
                    }
                    return result;
                }
                return Collections.emptyList();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
        ComponentName comp = intent.getComponent();
        if (comp == null && intent.getSelector() != null) {
            intent = intent.getSelector();
            comp = intent.getComponent();
        }
        if (comp != null) {
            List<ResolveInfo> list = new ArrayList<>(1);
            ActivityInfo ai = getReceiverInfo(comp, flags, userId);
            if (ai != null) {
                ResolveInfo ri = new ResolveInfo();
                ri.activityInfo = ai;
                list.add(ri);
            }
            return list;
        }
        synchronized (this.mPackages) {
            String pkgName = intent.getPackage();
            BPackageSettings bPackageSettings = this.mPackages.get(pkgName);
            if (bPackageSettings != null) {
                BPackage pkg = bPackageSettings.pkg;
                return this.mComponentResolver.queryReceivers(intent, resolvedType, flags, pkg.receivers, userId);
            }
            return this.mComponentResolver.queryReceivers(intent, resolvedType, flags, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags, int userId) throws RemoteException {
        List<ProviderInfo> providers = new ArrayList<>();
        if (TextUtils.isEmpty(processName)) {
            return providers;
        }
        providers.addAll(this.mComponentResolver.queryProviders(processName, (String) null, flags, userId));
        return providers;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public InstallResult installPackageAsUser(String file, InstallOption option, int userId) {
        InstallResult installResultInstallPackageAsUserLocked;
        synchronized (this.mInstallLock) {
            installResultInstallPackageAsUserLocked = installPackageAsUserLocked(file, option, userId);
        }
        return installResultInstallPackageAsUserLocked;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public void uninstallPackageAsUser(String packageName, int userId) throws RemoteException {
        synchronized (this.mInstallLock) {
            synchronized (this.mPackages) {
                BPackageSettings ps = this.mPackages.get(packageName);
                if (ps == null) {
                    return;
                }
                if (!ps.installOption.isFlag(4) || userId == -4) {
                    if (isInstalled(packageName, userId)) {
                        boolean removeApp = ps.getUserState().size() <= 1;
                        BProcessManagerService.get().killPackageAsUser(packageName, userId);
                        int i = BPackageInstallerService.get().uninstallPackageAsUser(ps, removeApp, userId);
                        if (i < 0) {
                        }
                        if (removeApp) {
                            this.mSettings.removePackage(packageName);
                            this.mComponentResolver.removeAllComponents(ps.pkg);
                        } else {
                            ps.removeUser(userId);
                            ps.save();
                        }
                        onPackageUninstalled(packageName, removeApp, userId);
                    }
                }
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public void uninstallPackage(String packageName) {
        synchronized (this.mInstallLock) {
            try {
                synchronized (this.mPackages) {
                    BPackageSettings ps = this.mPackages.get(packageName);
                    if (ps == null) {
                        return;
                    }
                    BProcessManagerService.get().killAllByPackageName(packageName);
                    for (Integer userId : ps.getUserIds()) {
                        int i = BPackageInstallerService.get().uninstallPackageAsUser(ps, true, userId.intValue());
                        if (i >= 0) {
                            onPackageUninstalled(packageName, true, userId.intValue());
                        }
                    }
                    this.mSettings.removePackage(packageName);
                    this.mComponentResolver.removeAllComponents(ps.pkg);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public void clearPackage(String packageName, int userId) {
        if (!isInstalled(packageName, userId)) {
            return;
        }
        BProcessManagerService.get().killPackageAsUser(packageName, userId);
        BPackageSettings ps = this.mPackages.get(packageName);
        if (ps == null) {
            return;
        }
        BPackageInstallerService.get().clearPackage(ps, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public void stopPackage(String packageName, int userId) {
        BProcessManagerService.get().killPackageAsUser(packageName, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public void deleteUser(int userId) throws RemoteException {
        synchronized (this.mPackages) {
            for (BPackageSettings ps : this.mPackages.values()) {
                uninstallPackageAsUser(ps.pkg.packageName, userId);
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public boolean isInstalled(String packageName, int userId) {
        synchronized (this.mPackages) {
            BPackageSettings ps = this.mPackages.get(packageName);
            if (ps == null) {
                return false;
            }
            return ps.getInstalled(userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public List<InstalledPackage> getInstalledPackagesAsUser(int userId) {
        List<InstalledPackage> installedPackages;
        synchronized (this.mPackages) {
            installedPackages = new ArrayList<>();
            for (BPackageSettings ps : this.mPackages.values()) {
                if (ps.getInstalled(userId) && !GmsCore.isGoogleAppOrService(ps.pkg.packageName)) {
                    InstalledPackage installedPackage = new InstalledPackage();
                    installedPackage.userId = userId;
                    installedPackage.packageName = ps.pkg.packageName;
                    installedPackages.add(installedPackage);
                }
            }
        }
        return installedPackages;
    }

    @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
    public String[] getPackagesForUid(int uid, int userId) throws RemoteException {
        String[] strArr;
        ProcessRecord processByPid;
        synchronized (this.mPackages) {
            List<String> packages = new ArrayList<>();
            for (BPackageSettings ps : this.mPackages.values()) {
                String packageName = ps.pkg.packageName;
                if (ps.getInstalled(userId) && getAppId(packageName) == uid) {
                    packages.add(packageName);
                }
            }
            if (packages.isEmpty() && (processByPid = BProcessManagerService.get().findProcessByPid(getCallingPid())) != null) {
                packages.add(processByPid.getPackageName());
            }
            strArr = (String[]) packages.toArray(new String[0]);
        }
        return strArr;
    }

    private InstallResult installPackageAsUserLocked(String file, InstallOption option, int userId) {
        File apkFile;
        long l = System.currentTimeMillis();
        InstallResult result = new InstallResult();
        try {
            try {
                if (option.isFlag(8)) {
                    apkFile = new File(BEnvironment.getCacheDir(), UUID.randomUUID().toString() + ".apk");
                    InputStream inputStream = BlackBoxCore.getContext().getContentResolver().openInputStream(Uri.parse(file));
                    FileUtils.copyFile(inputStream, apkFile);
                } else {
                    apkFile = new File(file);
                }
                if (option.isFlag(4) && userId != -4) {
                    InstallResult installResultInstallError = new InstallResult().installError("Please install the XP module in XP module management");
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError;
                }
                if (option.isFlag(4) && !XposedParserCompat.isXPModule(apkFile.getAbsolutePath())) {
                    InstallResult installResultInstallError2 = new InstallResult().installError("not a XP module");
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError2;
                }
                PackageInfo packageArchiveInfo = BlackBoxCore.getPackageManager().getPackageArchiveInfo(apkFile.getAbsolutePath(), 0);
                if (packageArchiveInfo == null) {
                    InstallResult installResultInstallError3 = result.installError("getPackageArchiveInfo error.Please check whether APK is normal.");
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError3;
                }
                boolean support = AbiUtils.isSupport(apkFile);
                if (!support) {
                    String msg = ((Object) packageArchiveInfo.applicationInfo.loadLabel(BlackBoxCore.getPackageManager())) + "[" + packageArchiveInfo.packageName + "]";
                    InstallResult installResultInstallError4 = result.installError(packageArchiveInfo.packageName, msg + ShellUtils.COMMAND_LINE_END + (BlackBoxCore.is64Bit() ? "The box does not support 32-bit Application" : "The box does not support 64-bit Application"));
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError4;
                }
                PackageParser.Package aPackage = parserApk(apkFile.getAbsolutePath());
                if (aPackage == null) {
                    InstallResult installResultInstallError5 = result.installError("parser apk error.");
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError5;
                }
                result.packageName = aPackage.packageName;
                if (option.isFlag(1)) {
                    aPackage.applicationInfo = BlackBoxCore.getPackageManager().getPackageInfo(aPackage.packageName, 0).applicationInfo;
                }
                BPackageSettings bPackageSettings = this.mSettings.getPackageLPw(aPackage.packageName, aPackage, option);
                BProcessManagerService.get().killPackageAsUser(aPackage.packageName, userId);
                int i = BPackageInstallerService.get().installPackageAsUser(bPackageSettings, userId);
                if (i < 0) {
                    InstallResult installResultInstallError6 = result.installError("install apk error.");
                    if (apkFile != null && option.isFlag(8)) {
                        FileUtils.deleteDir(apkFile);
                    }
                    Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                    return installResultInstallError6;
                }
                synchronized (this.mPackages) {
                    bPackageSettings.setInstalled(true, userId);
                    bPackageSettings.save();
                }
                this.mComponentResolver.removeAllComponents(bPackageSettings.pkg);
                this.mComponentResolver.addAllComponents(bPackageSettings.pkg);
                this.mSettings.scanPackage(aPackage.packageName);
                onPackageInstalled(bPackageSettings.pkg.packageName, userId);
                if (apkFile != null && option.isFlag(8)) {
                    FileUtils.deleteDir(apkFile);
                }
                Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                return result;
            } catch (Throwable t) {
                t.printStackTrace();
                if (0 != 0 && option.isFlag(8)) {
                    FileUtils.deleteDir((File) null);
                }
                Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
                return result;
            }
        } catch (Throwable th) {
            if (0 != 0 && option.isFlag(8)) {
                FileUtils.deleteDir((File) null);
            }
            Slog.d(TAG, "install finish: " + (System.currentTimeMillis() - l) + "ms");
            throw th;
        }
    }

    private PackageParser.Package parserApk(String file) {
        try {
            PackageParser parser = PackageParserCompat.createParser(new File(file));
            PackageParser.Package aPackage = PackageParserCompat.parsePackage(parser, new File(file), 0);
            PackageParserCompat.collectCertificates(parser, aPackage, 0);
            if (aPackage != null) {
                // Android 14+ no longer fills the legacy path fields, without
                // them the installer cannot copy the APK and the class loader
                // ends up empty.
                aPackage.baseCodePath = file;
                if (aPackage.applicationInfo != null) {
                    aPackage.applicationInfo.sourceDir = file;
                    aPackage.applicationInfo.publicSourceDir = file;
                }
            }
            return aPackage;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    static String fixProcessName(String defProcessName, String processName) {
        if (processName == null) {
            return defProcessName;
        }
        return processName;
    }

    private int updateFlags(int flags, int userId) {
        if ((flags & 786432) == 0) {
            flags |= 786432;
        }
        return flags;
    }

    public int getAppId(String packageName) {
        BPackageSettings bPackageSettings = this.mPackages.get(packageName);
        if (bPackageSettings != null) {
            return bPackageSettings.appId;
        }
        return -1;
    }

    Settings getSettings() {
        return this.mSettings;
    }

    public void addPackageMonitor(PackageMonitor monitor) {
        this.mPackageMonitors.add(monitor);
    }

    public void removePackageMonitor(PackageMonitor monitor) {
        this.mPackageMonitors.remove(monitor);
    }

    void onPackageUninstalled(String packageName, boolean isRemove, int userId) {
        for (PackageMonitor packageMonitor : this.mPackageMonitors) {
            packageMonitor.onPackageUninstalled(packageName, isRemove, userId);
        }
        Slog.d(TAG, "onPackageUninstalled: " + packageName + ", userId: " + userId);
    }

    void onPackageInstalled(String packageName, int userId) {
        for (PackageMonitor packageMonitor : this.mPackageMonitors) {
            packageMonitor.onPackageInstalled(packageName, userId);
        }
        Slog.d(TAG, "onPackageInstalled: " + packageName + ", userId: " + userId);
    }

    public BPackageSettings getBPackageSetting(String packageName) {
        return this.mPackages.get(packageName);
    }

    public List<BPackageSettings> getBPackageSettings() {
        return new ArrayList(this.mPackages.values());
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        this.mSettings.scanPackage();
        for (BPackageSettings value : this.mPackages.values()) {
            this.mComponentResolver.removeAllComponents(value.pkg);
            this.mComponentResolver.addAllComponents(value.pkg);
        }
    }
}
