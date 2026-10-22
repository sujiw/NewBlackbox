package top.niunaijun.blackbox.core.system.pm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import black.android.content.pm.BRApplicationInfoL;
import black.android.content.pm.BRApplicationInfoN;
import black.android.content.pm.BRPackageParserSigningDetails;
import black.android.content.pm.BRSigningInfo;
import black.android.content.res.BRAssetManager;
import java.util.HashSet;
import java.util.Set;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.AppSystemEnv;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.utils.ArrayUtils;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/PackageManagerCompat.class */
@SuppressLint({"SdCardPath", "NewApi"})
public class PackageManagerCompat {
    public static PackageInfo generatePackageInfo(BPackageSettings ps, int flags, BPackageUserState state, int userId) {
        BPackage p;
        if (ps != null && (p = ps.pkg) != null) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = generatePackageInfo(p, flags, 0L, 0L, state, userId);
            } catch (Throwable th) {
            }
            return packageInfo;
        }
        return null;
    }

    public static PackageInfo generatePackageInfo(BPackage p, int flags, long firstInstallTime, long lastUpdateTime, BPackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(flags, state, p.applicationInfo)) {
            return null;
        }
        PackageInfo pi = new PackageInfo();
        pi.packageName = p.packageName;
        pi.versionCode = p.mVersionCode;
        pi.versionName = p.mVersionName;
        pi.sharedUserId = p.mSharedUserId;
        pi.sharedUserLabel = p.mSharedUserLabel;
        pi.applicationInfo = generateApplicationInfo(p, flags, state, userId);
        pi.firstInstallTime = firstInstallTime;
        pi.lastUpdateTime = lastUpdateTime;
        if (!p.requestedPermissions.isEmpty()) {
            String[] requestedPermissions = new String[p.requestedPermissions.size()];
            p.requestedPermissions.toArray(requestedPermissions);
            pi.requestedPermissions = requestedPermissions;
        }
        if ((flags & 256) != 0) {
            pi.gids = new int[0];
        }
        if ((flags & 16384) != 0) {
            int N = p.configPreferences != null ? p.configPreferences.size() : 0;
            if (N > 0) {
                pi.configPreferences = new ConfigurationInfo[N];
                p.configPreferences.toArray(pi.configPreferences);
            }
            int N2 = p.reqFeatures != null ? p.reqFeatures.size() : 0;
            if (N2 > 0) {
                pi.reqFeatures = new FeatureInfo[N2];
                p.reqFeatures.toArray(pi.reqFeatures);
            }
        }
        if ((flags & 1) != 0) {
            pi.activities = null;
            int N3 = p.activities.size();
            if (N3 > 0) {
                int num = 0;
                ActivityInfo[] res = new ActivityInfo[N3];
                for (int i = 0; i < N3; i++) {
                    BPackage.Activity a = p.activities.get(i);
                    int i2 = num;
                    num++;
                    res[i2] = generateActivityInfo(a, flags, state, userId);
                }
                pi.activities = (ActivityInfo[]) ArrayUtils.trimToSize(res, num);
            }
        }
        if ((flags & 2) != 0) {
            pi.receivers = null;
            int N4 = p.receivers.size();
            if (N4 > 0) {
                int num2 = 0;
                ActivityInfo[] res2 = new ActivityInfo[N4];
                for (int i3 = 0; i3 < N4; i3++) {
                    BPackage.Activity a2 = p.receivers.get(i3);
                    int i4 = num2;
                    num2++;
                    res2[i4] = generateActivityInfo(a2, flags, state, userId);
                }
                pi.receivers = (ActivityInfo[]) ArrayUtils.trimToSize(res2, num2);
            }
        }
        if ((flags & 4) != 0) {
            pi.services = null;
            int N5 = p.services.size();
            if (N5 > 0) {
                int num3 = 0;
                ServiceInfo[] res3 = new ServiceInfo[N5];
                for (int i5 = 0; i5 < N5; i5++) {
                    BPackage.Service s = p.services.get(i5);
                    ServiceInfo serviceInfo = generateServiceInfo(s, flags, state, userId);
                    if (serviceInfo != null) {
                        if ((flags & 128) != 0) {
                            if (serviceInfo.metaData != null) {
                                int i6 = num3;
                                num3++;
                                res3[i6] = serviceInfo;
                            }
                        } else {
                            int i7 = num3;
                            num3++;
                            res3[i7] = serviceInfo;
                        }
                    }
                }
                pi.services = (ServiceInfo[]) ArrayUtils.trimToSize(res3, num3);
            }
        }
        if ((flags & 8) != 0) {
            pi.providers = null;
            int N6 = p.providers.size();
            if (N6 > 0) {
                int num4 = 0;
                ProviderInfo[] res4 = new ProviderInfo[N6];
                for (int i8 = 0; i8 < N6; i8++) {
                    BPackage.Provider pr = p.providers.get(i8);
                    ProviderInfo providerInfo = generateProviderInfo(pr, flags, state, userId);
                    if (providerInfo != null) {
                        int i9 = num4;
                        num4++;
                        res4[i9] = providerInfo;
                    }
                }
                pi.providers = (ProviderInfo[]) ArrayUtils.trimToSize(res4, num4);
            }
        }
        if ((flags & 16) != 0) {
            pi.instrumentation = null;
            int N7 = p.instrumentation.size();
            if (N7 > 0) {
                pi.instrumentation = new InstrumentationInfo[N7];
                for (int i10 = 0; i10 < N7; i10++) {
                    pi.instrumentation[i10] = generateInstrumentationInfo(p.instrumentation.get(i10), flags);
                }
            }
        }
        if ((flags & 4096) != 0) {
            pi.permissions = null;
            int N8 = p.permissions.size();
            if (N8 > 0) {
                pi.permissions = new PermissionInfo[N8];
                for (int i11 = 0; i11 < N8; i11++) {
                    pi.permissions[i11] = generatePermissionInfo(p.permissions.get(i11), flags);
                }
            }
            pi.requestedPermissions = null;
            int N9 = p.requestedPermissions.size();
            if (N9 > 0) {
                pi.requestedPermissions = new String[N9];
                pi.requestedPermissionsFlags = new int[N9];
                for (int i12 = 0; i12 < N9; i12++) {
                    String perm = p.requestedPermissions.get(i12);
                    pi.requestedPermissions[i12] = perm;
                }
            }
        }
        PackageInfo base = null;
        try {
            base = BlackBoxCore.getContext().getPackageManager().getPackageInfo(p.packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if ((flags & 64) != 0) {
            if (base == null) {
                pi.signatures = p.mSignatures;
            } else {
                pi.signatures = base.signatures;
            }
        }
        if (BuildCompat.isPie() && (flags & 134217728) != 0) {
            if (base == null) {
                PackageParser.SigningDetails signingDetails = PackageParser.SigningDetails.UNKNOWN;
                BRPackageParserSigningDetails.get(signingDetails)._set_signatures(p.mSigningDetails.signatures);
                pi.signingInfo = BRSigningInfo.get()._new(signingDetails);
            } else {
                pi.signingInfo = base.signingInfo;
            }
        }
        return pi;
    }

    public static ActivityInfo generateActivityInfo(BPackage.Activity a, int flags, BPackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(flags, state, a.info.applicationInfo)) {
            return null;
        }
        ActivityInfo ai = new ActivityInfo(a.info);
        ai.metaData = a.metaData;
        ai.processName = BPackageManagerService.fixProcessName(ai.packageName, ai.processName);
        ai.applicationInfo = generateApplicationInfo(a.owner, flags, state, userId);
        return ai;
    }

    public static ServiceInfo generateServiceInfo(BPackage.Service s, int flags, BPackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(flags, state, s.info.applicationInfo)) {
            return null;
        }
        ServiceInfo si = new ServiceInfo(s.info);
        si.metaData = s.metaData;
        si.processName = BPackageManagerService.fixProcessName(si.packageName, si.processName);
        si.applicationInfo = generateApplicationInfo(s.owner, flags, state, userId);
        return si;
    }

    public static ProviderInfo generateProviderInfo(BPackage.Provider p, int flags, BPackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(flags, state, p.info.applicationInfo)) {
            return null;
        }
        ProviderInfo pi = new ProviderInfo(p.info);
        if (pi.authority == null) {
            return null;
        }
        pi.metaData = p.metaData;
        pi.processName = BPackageManagerService.fixProcessName(pi.packageName, pi.processName);
        if ((flags & FileUtils.FileMode.MODE_ISUID) == 0) {
            pi.uriPermissionPatterns = null;
        }
        pi.applicationInfo = generateApplicationInfo(p.owner, flags, state, userId);
        return pi;
    }

    public static PermissionInfo generatePermissionInfo(BPackage.Permission p, int flags) {
        if (p == null) {
            return null;
        }
        if ((flags & 128) == 0) {
            return p.info;
        }
        PermissionInfo pi = new PermissionInfo(p.info);
        pi.metaData = p.metaData;
        return pi;
    }

    public static InstrumentationInfo generateInstrumentationInfo(BPackage.Instrumentation i, int flags) {
        if (i == null) {
            return null;
        }
        if ((flags & 128) == 0) {
            return i.info;
        }
        InstrumentationInfo ii = new InstrumentationInfo(i.info);
        ii.metaData = i.metaData;
        return ii;
    }

    public static ApplicationInfo generateApplicationInfo(BPackage p, int flags, BPackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(flags, state, p.applicationInfo)) {
            return null;
        }
        try {
            ApplicationInfo baseApplication = BlackBoxCore.getPackageManager().getApplicationInfo(BlackBoxCore.getHostPkg(), flags);
            String sourceDir = p.baseCodePath;
            if (sourceDir == null && p.applicationInfo != null) {
                sourceDir = p.applicationInfo.sourceDir;
            }
            if (sourceDir == null) {
                sourceDir = BEnvironment.getBaseApkDir(p.packageName).getAbsolutePath();
            }
            if (p.applicationInfo == null) {
                p.applicationInfo = BlackBoxCore.getPackageManager().getPackageArchiveInfo(sourceDir, 0).applicationInfo;
            }
            ApplicationInfo ai = new ApplicationInfo(p.applicationInfo);
            if ((flags & 128) != 0) {
                ai.metaData = p.mAppMetaData;
            }
            ai.dataDir = BEnvironment.getDataDir(ai.packageName, userId).getAbsolutePath();
            if (!p.installOption.isFlag(1)) {
                ai.nativeLibraryDir = BEnvironment.getAppLibDir(ai.packageName).getAbsolutePath();
            }
            ai.processName = BPackageManagerService.fixProcessName(p.packageName, ai.packageName);
            ai.publicSourceDir = sourceDir;
            ai.sourceDir = sourceDir;
            ai.uid = p.mExtras.appId;
            if (BuildCompat.isL()) {
                BRApplicationInfoL.get(ai)._set_primaryCpuAbi(Build.CPU_ABI);
                BRApplicationInfoL.get(ai)._set_scanPublicSourceDir(BRApplicationInfoL.get(baseApplication).scanPublicSourceDir());
                BRApplicationInfoL.get(ai)._set_scanSourceDir(BRApplicationInfoL.get(baseApplication).scanSourceDir());
            }
            if (BuildCompat.isN()) {
                ai.deviceProtectedDataDir = BEnvironment.getDeDataDir(p.packageName, userId).getAbsolutePath();
                if (BRApplicationInfoN.get(ai)._check_deviceEncryptedDataDir() != null) {
                    BRApplicationInfoN.get(ai)._set_deviceEncryptedDataDir(ai.deviceProtectedDataDir);
                }
                if (BRApplicationInfoN.get(ai)._check_credentialEncryptedDataDir() != null) {
                    BRApplicationInfoN.get(ai)._set_credentialEncryptedDataDir(ai.dataDir);
                }
                if (BRApplicationInfoN.get(ai)._check_deviceProtectedDataDir() != null) {
                    BRApplicationInfoN.get(ai)._set_deviceProtectedDataDir(ai.deviceProtectedDataDir);
                }
                if (BRApplicationInfoN.get(ai)._check_credentialProtectedDataDir() != null) {
                    BRApplicationInfoN.get(ai)._set_credentialProtectedDataDir(ai.dataDir);
                }
            }
            fixJar(ai);
            return ai;
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean checkUseInstalledOrHidden(int flags, BPackageUserState state, ApplicationInfo appInfo) {
        if (AppSystemEnv.isBlackPackage(appInfo.packageName) || !state.installed || state.hidden) {
            return false;
        }
        return true;
    }

    private static void fixJar(ApplicationInfo info) {
        Set<String> sharedLibraryFileList = new HashSet<>();
        if (!BuildCompat.isQ() || !FileUtils.isExist("/system/framework/org.apache.http.legacy.jar")) {
            sharedLibraryFileList.add("/system/framework/org.apache.http.legacy.boot.jar");
        } else {
            sharedLibraryFileList.add("/system/framework/org.apache.http.legacy.jar");
        }
        info.sharedLibraryFiles = (String[]) sharedLibraryFileList.toArray(new String[0]);
    }

    public static Resources getResources(Context context, ApplicationInfo appInfo) {
        BPackageSettings ps = BPackageManagerService.get().getBPackageSetting(appInfo.packageName);
        if (ps != null) {
            AssetManager assets = BRAssetManager.get()._new();
            BRAssetManager.get(assets).addAssetPath(ps.pkg.baseCodePath);
            Resources hostRes = context.getResources();
            return new Resources(assets, hostRes.getDisplayMetrics(), hostRes.getConfiguration());
        }
        return null;
    }
}
