package android.content.pm;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import java.io.File;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser.class */
public class PackageParser {
    public static final int PARSE_IS_SYSTEM = 1;
    public static final int PARSE_CHATTY = 2;
    public static final int PARSE_MUST_BE_APK = 4;
    public static final int PARSE_IGNORE_PROCESSES = 8;
    public static final int PARSE_FORWARD_LOCK = 16;
    public static final int PARSE_EXTERNAL_STORAGE = 32;
    public static final int PARSE_IS_SYSTEM_DIR = 64;
    public static final int PARSE_IS_PRIVILEGED = 128;
    public static final int PARSE_COLLECT_CERTIFICATES = 256;
    public static final int PARSE_TRUSTED_OVERLAY = 512;
    public static final NewPermissionInfo[] NEW_PERMISSIONS = {new NewPermissionInfo("android.permission.WRITE_EXTERNAL_STORAGE", 4, 0), new NewPermissionInfo("android.permission.READ_PHONE_STATE", 4, 0)};

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$IntentInfo.class */
    @SuppressLint({"ParcelCreator"})
    public static class IntentInfo extends IntentFilter {
        public boolean hasDefault;
        public int labelRes;
        public CharSequence nonLocalizedLabel;
        public int icon;
        public int logo;
        public int banner;
        public int preferred;
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$SigningDetails.class */
    public static class SigningDetails {
        public static final SigningDetails UNKNOWN = null;
        public Signature[] signatures;
        public Signature[] pastSigningCertificates;
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$NewPermissionInfo.class */
    public static class NewPermissionInfo {
        public final String name;
        public final int sdkVersion;
        public final int fileVersion;

        public NewPermissionInfo(String name, int sdkVersion, int fileVersion) {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$SplitPermissionInfo.class */
    public static class SplitPermissionInfo {
        public final String rootPerm;
        public final String[] newPerms;
        public final int targetSdk;

        public SplitPermissionInfo(String rootPerm, String[] newPerms, int targetSdk) {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ParsePackageItemArgs.class */
    static class ParsePackageItemArgs {
        final Package owner;
        final String[] outError;
        final int nameRes;
        final int labelRes;
        final int iconRes;
        final int logoRes;
        final int bannerRes;
        String tag;
        TypedArray sa;

        ParsePackageItemArgs(Package owner, String[] outError, int nameRes, int labelRes, int iconRes, int logoRes, int bannerRes) {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ParseComponentArgs.class */
    static class ParseComponentArgs extends ParsePackageItemArgs {
        final String[] sepProcesses;
        final int processRes;
        final int descriptionRes;
        final int enabledRes;
        int flags;

        ParseComponentArgs(Package owner, String[] outError, int nameRes, int labelRes, int iconRes, int logoRes, int bannerRes, String[] sepProcesses, int processRes, int descriptionRes, int enabledRes) {
            super(owner, outError, nameRes, labelRes, iconRes, logoRes, bannerRes);
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$PackageLite.class */
    public static class PackageLite {
        public final String packageName;
        public final int versionCode;
        public final int installLocation;
        public final VerifierInfo[] verifiers;
        public final String[] splitNames;
        public final String codePath;
        public final String baseCodePath;
        public final String[] splitCodePaths;
        public final int baseRevisionCode;
        public final int[] splitRevisionCodes;
        public final boolean coreApp;
        public final boolean multiArch;
        public final boolean extractNativeLibs;

        public PackageLite(String codePath, ApkLite baseApk, String[] splitNames, String[] splitCodePaths, int[] splitRevisionCodes) {
            throw new RuntimeException("Stub!");
        }

        public List<String> getAllCodePaths() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ApkLite.class */
    public static class ApkLite {
        public final String codePath;
        public final String packageName;
        public final String splitName;
        public final int versionCode;
        public final int revisionCode;
        public final int installLocation;
        public final VerifierInfo[] verifiers;
        public final Signature[] signatures;
        public final boolean coreApp;
        public final boolean multiArch;
        public final boolean extractNativeLibs;

        public ApkLite(String codePath, String packageName, String splitName, int versionCode, int revisionCode, int installLocation, List<VerifierInfo> verifiers, Signature[] signatures, boolean coreApp, boolean multiArch, boolean extractNativeLibs) {
            throw new RuntimeException("Stub!");
        }
    }

    public PackageParser() {
        throw new RuntimeException("Stub!");
    }

    public PackageParser(String archiveSourcePath) {
        throw new RuntimeException("Stub!");
    }

    public void setSeparateProcesses(String[] procs) {
        throw new RuntimeException("Stub!");
    }

    public void setOnlyCoreApps(boolean onlyCoreApps) {
        throw new RuntimeException("Stub!");
    }

    public void setDisplayMetrics(DisplayMetrics metrics) {
        throw new RuntimeException("Stub!");
    }

    public static final boolean isApkFile(File file) {
        throw new RuntimeException("Stub!");
    }

    public static PackageInfo generatePackageInfo(Package p, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isAvailable(PackageUserState state) {
        throw new RuntimeException("Stub!");
    }

    public static PackageInfo generatePackageInfo(Package p, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId) {
        throw new RuntimeException("Stub!");
    }

    public static PackageLite parsePackageLite(File packageFile, int flags) throws PackageParserException {
        throw new RuntimeException("Stub!");
    }

    public Package parsePackage(File packageFile, int flags) throws PackageParserException {
        throw new RuntimeException("Stub!");
    }

    public Package parsePackage(File sourceFile, String destCodePath, DisplayMetrics metrics, int flags) {
        throw new RuntimeException("Stub!");
    }

    public void collectManifestDigest(Package pkg) throws PackageParserException {
        throw new RuntimeException("Stub!");
    }

    public void collectCertificates(Package pkg, int flags) throws PackageParserException {
        throw new RuntimeException("Stub!");
    }

    public static ApkLite parseApkLite(File apkFile, int flags) throws PackageParserException {
        throw new RuntimeException("Stub!");
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Package.class */
    public static final class Package {
        public String packageName;
        public String[] splitNames;
        public String volumeUuid;
        public String codePath;
        public String baseCodePath;
        public String[] splitCodePaths;
        public int baseRevisionCode;
        public int[] splitRevisionCodes;
        public int[] splitFlags;
        public int[] splitPrivateFlags;
        public boolean baseHardwareAccelerated;
        public ArrayList<String> protectedBroadcasts;
        public int mVersionCode;
        public String mVersionName;
        public String mSharedUserId;
        public int mSharedUserLabel;
        public Signature[] mSignatures;
        public SigningDetails mSigningDetails;
        public Certificate[][] mCertificates;
        public long mLastPackageUsageTimeInMills;
        public Object mExtras;
        public int installLocation;
        public boolean coreApp;
        public boolean mRequiredForAllUsers;
        public String mRestrictedAccountType;
        public String mRequiredAccountType;
        public ManifestDigest manifestDigest;
        public String mOverlayTarget;
        public int mOverlayPriority;
        public boolean mTrustedOverlay;
        public ArraySet<PublicKey> mSigningKeys;
        public ArraySet<String> mUpgradeKeySets;
        public ArrayMap<String, ArraySet<PublicKey>> mKeySetMapping;
        public String cpuAbiOverride;
        public ApplicationInfo applicationInfo = new ApplicationInfo();
        public final ArrayList<Permission> permissions = new ArrayList<>(0);
        public final ArrayList<PermissionGroup> permissionGroups = new ArrayList<>(0);
        public final ArrayList<Activity> activities = new ArrayList<>(0);
        public final ArrayList<Activity> receivers = new ArrayList<>(0);
        public final ArrayList<Provider> providers = new ArrayList<>(0);
        public final ArrayList<Service> services = new ArrayList<>(0);
        public final ArrayList<Instrumentation> instrumentation = new ArrayList<>(0);
        public final ArrayList<String> requestedPermissions = new ArrayList<>();
        public ArrayList<String> libraryNames = null;
        public ArrayList<String> usesLibraries = null;
        public ArrayList<String> usesOptionalLibraries = null;
        public String[] usesLibraryFiles = null;
        public ArrayList<ActivityIntentInfo> preferredActivityFilters = null;
        public ArrayList<String> mOriginalPackages = null;
        public String mRealPackage = null;
        public ArrayList<String> mAdoptPermissions = null;
        public Bundle mAppMetaData = null;
        public int mPreferredOrder = 0;
        public ArrayList<ConfigurationInfo> configPreferences = null;
        public ArrayList<FeatureInfo> reqFeatures = null;
        public ArrayList<FeatureGroupInfo> featureGroups = null;

        public Package(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public List<String> getAllCodePaths() {
            throw new RuntimeException("Stub!");
        }

        public List<String> getAllCodePathsExcludingResourceOnly() {
            throw new RuntimeException("Stub!");
        }

        public void setPackageName(String newName) {
            throw new RuntimeException("Stub!");
        }

        public boolean hasComponentClassName(String name) {
            throw new RuntimeException("Stub!");
        }

        public boolean isForwardLocked() {
            throw new RuntimeException("Stub!");
        }

        public boolean isSystemApp() {
            throw new RuntimeException("Stub!");
        }

        public boolean isPrivilegedApp() {
            throw new RuntimeException("Stub!");
        }

        public boolean isUpdatedSystemApp() {
            throw new RuntimeException("Stub!");
        }

        public boolean canHaveOatDir() {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Component.class */
    public static class Component<II extends IntentInfo> {
        public final Package owner;
        public final ArrayList<II> intents;
        public final String className;
        public Bundle metaData;
        ComponentName componentName;
        String componentShortName;

        public Component(Package owner) {
            throw new RuntimeException("Stub!");
        }

        public Component(ParsePackageItemArgs args, PackageItemInfo outInfo) {
            throw new RuntimeException("Stub!");
        }

        public Component(ParseComponentArgs args, ComponentInfo outInfo) {
            throw new RuntimeException("Stub!");
        }

        public Component(Component<II> clone) {
            throw new RuntimeException("Stub!");
        }

        public ComponentName getComponentName() {
            throw new RuntimeException("Stub!");
        }

        public void appendComponentShortName(StringBuilder sb) {
            throw new RuntimeException("Stub!");
        }

        public void printComponentShortName(PrintWriter pw) {
            throw new RuntimeException("Stub!");
        }

        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Permission.class */
    public static final class Permission extends Component<IntentInfo> {
        public final PermissionInfo info;
        public boolean tree;
        public PermissionGroup group;

        public Permission(Package owner) {
            super(owner);
            throw new RuntimeException("Stub!");
        }

        public Permission(Package owner, PermissionInfo info) {
            super(owner);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$PermissionGroup.class */
    public static final class PermissionGroup extends Component<IntentInfo> {
        public final PermissionGroupInfo info;

        public PermissionGroup(Package owner) {
            super(owner);
            throw new RuntimeException("Stub!");
        }

        public PermissionGroup(Package owner, PermissionGroupInfo info) {
            super(owner);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Activity.class */
    public static final class Activity extends Component<ActivityIntentInfo> {
        public final ActivityInfo info;

        public Activity(ParseComponentArgs args, ActivityInfo info) {
            super(args, (ComponentInfo) info);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Service.class */
    public static final class Service extends Component<ServiceIntentInfo> {
        public final ServiceInfo info;

        public Service(ParseComponentArgs args, ServiceInfo info) {
            super(args, (ComponentInfo) info);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Provider.class */
    public static final class Provider extends Component<ProviderIntentInfo> {
        public final ProviderInfo info;
        public boolean syncable;

        public Provider(ParseComponentArgs args, ProviderInfo info) {
            super(args, (ComponentInfo) info);
            throw new RuntimeException("Stub!");
        }

        public Provider(Provider existingProvider) {
            super(existingProvider);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$Instrumentation.class */
    public static final class Instrumentation extends Component<IntentInfo> {
        public final InstrumentationInfo info;

        public Instrumentation(ParsePackageItemArgs args, InstrumentationInfo info) {
            super(args, info);
            throw new RuntimeException("Stub!");
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ActivityIntentInfo.class */
    @SuppressLint({"ParcelCreator"})
    public static final class ActivityIntentInfo extends IntentInfo {
        public final Activity activity;

        public ActivityIntentInfo(Activity activity) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ServiceIntentInfo.class */
    @SuppressLint({"ParcelCreator"})
    public static final class ServiceIntentInfo extends IntentInfo {
        public final Service service;

        public ServiceIntentInfo(Service service) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$ProviderIntentInfo.class */
    @SuppressLint({"ParcelCreator"})
    public static final class ProviderIntentInfo extends IntentInfo {
        public final Provider provider;

        public ProviderIntentInfo(Provider provider) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/PackageParser$PackageParserException.class */
    public static class PackageParserException extends Exception {
        public PackageParserException(int error, String detailMessage) {
            super(detailMessage);
            throw new RuntimeException("Stub!");
        }

        public PackageParserException(int error, String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
            throw new RuntimeException("Stub!");
        }
    }
}
