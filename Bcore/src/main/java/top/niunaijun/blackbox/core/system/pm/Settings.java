package top.niunaijun.blackbox.core.system.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;
import android.os.Parcel;
import android.util.ArrayMap;
import android.util.AtomicFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.user.BUserHandle;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.PackageParserCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/Settings.class */
class Settings {
    public static final String TAG = "Settings";
    final ArrayMap<String, BPackageSettings> mPackages = new ArrayMap<>();
    private final Map<String, Integer> mAppIds = new HashMap();
    private final Map<String, SharedUserSetting> mSharedUsers = SharedUserSetting.sSharedUsers;
    private int mCurrUid = 0;

    public Settings() {
        synchronized (this.mPackages) {
            loadUidLP();
            SharedUserSetting.loadSharedUsers();
        }
    }

    BPackageSettings getPackageLPw(String name, PackageParser.Package aPackage, InstallOption installOption) {
        BPackageSettings origSettings = new BPackageSettings();
        origSettings.pkg = new BPackage(aPackage);
        origSettings.pkg.installOption = installOption;
        origSettings.installOption = installOption;
        origSettings.pkg.mExtras = origSettings;
        origSettings.pkg.applicationInfo = PackageManagerCompat.generateApplicationInfo(origSettings.pkg, 0, BPackageUserState.create(), 0);
        synchronized (this.mPackages) {
            BPackageSettings pkgSettings = this.mPackages.get(name);
            if (pkgSettings != null) {
                origSettings.appId = pkgSettings.appId;
                origSettings.userState = pkgSettings.userState;
            } else {
                boolean b = registerAppIdLPw(origSettings);
                if (!b) {
                    throw new RuntimeException("registerAppIdLPw err.");
                }
            }
        }
        return origSettings;
    }

    boolean registerAppIdLPw(BPackageSettings p) {
        boolean createdNew;
        String sharedUserId = p.pkg.mSharedUserId;
        SharedUserSetting sharedUserSetting = null;
        if (sharedUserId != null) {
            sharedUserSetting = this.mSharedUsers.get(sharedUserId);
            if (sharedUserSetting == null) {
                sharedUserSetting = new SharedUserSetting(sharedUserId);
                sharedUserSetting.userId = acquireAndRegisterNewAppIdLPw(p);
                this.mSharedUsers.put(sharedUserId, sharedUserSetting);
            }
        }
        if (sharedUserSetting != null) {
            p.appId = sharedUserSetting.userId;
            Slog.d(TAG, p.pkg.packageName + " sharedUserId = " + sharedUserId + ", setAppId = " + p.appId);
        }
        if (p.appId == 0) {
            p.appId = acquireAndRegisterNewAppIdLPw(p);
        }
        if (p.appId < 0) {
            createdNew = false;
        } else {
            createdNew = true;
        }
        saveUidLP();
        SharedUserSetting.saveSharedUsers();
        return createdNew;
    }

    private int acquireAndRegisterNewAppIdLPw(BPackageSettings obj) {
        Integer integer = this.mAppIds.get(obj.pkg.packageName);
        if (integer != null) {
            return integer.intValue();
        }
        if (this.mCurrUid >= 19999) {
            return -1;
        }
        this.mCurrUid++;
        this.mAppIds.put(obj.pkg.packageName, Integer.valueOf(this.mCurrUid));
        return BUserHandle.AID_APP_START + this.mCurrUid;
    }

    private void saveUidLP() {
        Parcel parcel = Parcel.obtain();
        FileOutputStream fileOutputStream = null;
        AtomicFile atomicFile = new AtomicFile(BEnvironment.getUidConf());
        try {
            Set<String> pkgName = this.mPackages.keySet();
            for (Object s : new HashSet<>(this.mAppIds.keySet())) {
                if (!pkgName.contains(s)) {
                    this.mAppIds.remove(s);
                }
            }
            parcel.writeInt(this.mCurrUid);
            parcel.writeMap(this.mAppIds);
            fileOutputStream = atomicFile.startWrite();
            FileUtils.writeParcelToOutput(parcel, fileOutputStream);
            atomicFile.finishWrite(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            atomicFile.failWrite(fileOutputStream);
        } finally {
            parcel.recycle();
        }
    }

    private void loadUidLP() {
        Parcel parcel = Parcel.obtain();
        try {
            try {
                byte[] uidBytes = FileUtils.toByteArray(BEnvironment.getUidConf());
                if (uidBytes == null || uidBytes.length == 0) {
                    parcel.recycle();
                    return;
                }
                parcel.unmarshall(uidBytes, 0, uidBytes.length);
                parcel.setDataPosition(0);
                this.mCurrUid = parcel.readInt();
                HashMap hashMap = parcel.readHashMap(HashMap.class.getClassLoader());
                synchronized (this.mAppIds) {
                    this.mAppIds.clear();
                    this.mAppIds.putAll(hashMap);
                }
                parcel.recycle();
            } catch (Exception e) {
                try {
                    BEnvironment.getUidConf().delete();
                } catch (Exception e2) {
                }
                this.mCurrUid = 0;
                synchronized (this.mAppIds) {
                    this.mAppIds.clear();
                    parcel.recycle();
                }
            }
        } catch (Throwable th) {
            parcel.recycle();
            throw th;
        }
    }

    public void scanPackage() {
        synchronized (this.mPackages) {
            File appRootDir = BEnvironment.getAppRootDir();
            FileUtils.mkdirs(appRootDir);
            File[] apps = appRootDir.listFiles();
            for (File app : apps) {
                if (app.isDirectory()) {
                    scanPackage(app.getName());
                }
            }
        }
    }

    public void scanPackage(String packageName) {
        synchronized (this.mPackages) {
            updatePackageLP(BEnvironment.getAppDir(packageName));
        }
    }

    private void updatePackageLP(File app) {
        String packageName = app.getName();
        Parcel packageSettingsIn = Parcel.obtain();
        File packageConf = BEnvironment.getPackageConf(packageName);
        try {
            byte[] bPackageSettingsBytes = FileUtils.toByteArray(packageConf);
            packageSettingsIn.unmarshall(bPackageSettingsBytes, 0, bPackageSettingsBytes.length);
            packageSettingsIn.setDataPosition(0);
            BPackageSettings bPackageSettings = new BPackageSettings(packageSettingsIn);
            bPackageSettings.pkg.mExtras = bPackageSettings;
            if (bPackageSettings.installOption.isFlag(1)) {
                PackageInfo packageInfo = BlackBoxCore.getPackageManager().getPackageInfo(packageName, 128);
                String currPackageSourcePath = packageInfo.applicationInfo.sourceDir;
                if (!currPackageSourcePath.equals(bPackageSettings.pkg.baseCodePath)) {
                    BProcessManagerService.get().killAllByPackageName(bPackageSettings.pkg.packageName);
                    BPackageSettings newPkg = reInstallBySystem(packageInfo, bPackageSettings.installOption);
                    bPackageSettings.pkg = newPkg.pkg;
                }
            } else {
                bPackageSettings.pkg.applicationInfo = PackageManagerCompat.generateApplicationInfo(bPackageSettings.pkg, 0, BPackageUserState.create(), 0);
            }
            bPackageSettings.save();
            this.mPackages.put(bPackageSettings.pkg.packageName, bPackageSettings);
            Slog.d(TAG, "loaded Package: " + packageName);
        } catch (Throwable e) {
            e.printStackTrace();
            FileUtils.deleteDir(app);
            removePackage(packageName);
            BProcessManagerService.get().killAllByPackageName(packageName);
            BPackageManagerService.get().onPackageUninstalled(packageName, true, -1);
            Slog.d(TAG, "bad Package: " + packageName);
        } finally {
            packageSettingsIn.recycle();
        }
    }

    private BPackageSettings reInstallBySystem(PackageInfo systemPackageInfo, InstallOption option) throws Exception {
        Slog.d(TAG, "reInstallBySystem: " + systemPackageInfo.packageName);
        PackageParser.Package aPackage = parserApk(systemPackageInfo.applicationInfo.sourceDir);
        if (aPackage == null) {
            throw new RuntimeException("parser apk error.");
        }
        aPackage.applicationInfo = BlackBoxCore.getPackageManager().getPackageInfo(aPackage.packageName, 0).applicationInfo;
        return getPackageLPw(aPackage.packageName, aPackage, option);
    }

    public void removePackage(String packageName) {
        this.mPackages.remove(packageName);
    }

    private PackageParser.Package parserApk(String file) {
        try {
            PackageParser parser = PackageParserCompat.createParser(new File(file));
            PackageParser.Package aPackage = PackageParserCompat.parsePackage(parser, new File(file), 0);
            PackageParserCompat.collectCertificates(parser, aPackage, 0);
            if (aPackage != null) {
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
}
