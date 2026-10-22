package black.android.content.pm;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser.class */
@BClassName("android.content.pm.PackageParser")
public interface PackageParser {

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Activity.class */
    @BClassName("android.content.pm.PackageParser$Activity")
    public interface Activity {
        @BField
        ActivityInfo info();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Component.class */
    @BClassName("android.content.pm.PackageParser$Component")
    public interface Component {
        @BField
        String className();

        @BField
        ComponentName componentName();

        @BField
        List<IntentFilter> intents();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Package.class */
    @BClassName("android.content.pm.PackageParser$Package")
    public interface Package {
        @BField
        List activities();

        @BField
        Bundle mAppMetaData();

        @BField
        String mSharedUserId();

        @BField
        Signature[] mSignatures();

        @BField
        Object mSigningDetails();

        @BField
        Integer mVersionCode();

        @BField
        String packageName();

        @BField
        List permissionGroups();

        @BField
        List permissions();

        @BField
        List<String> protectedBroadcasts();

        @BField
        List providers();

        @BField
        List receivers();

        @BField
        List<String> requestedPermissions();

        @BField
        List services();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Permission.class */
    @BClassName("android.content.pm.PackageParser$Permission")
    public interface Permission {
        @BField
        PermissionInfo info();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$PermissionGroup.class */
    @BClassName("android.content.pm.PackageParser$PermissionGroup")
    public interface PermissionGroup {
        @BField
        PermissionGroupInfo info();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Provider.class */
    @BClassName("android.content.pm.PackageParser$Provider")
    public interface Provider {
        @BField
        ProviderInfo info();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$Service.class */
    @BClassName("android.content.pm.PackageParser$Service")
    public interface Service {
        @BField
        ServiceInfo info();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParser$SigningDetails.class */
    @BClassName("android.content.pm.PackageParser$SigningDetails")
    public interface SigningDetails {
        @BField
        Signature[] pastSigningCertificates();

        @BField
        Signature[] signatures();

        @BMethod
        Boolean hasPastSigningCertificates();

        @BMethod
        Boolean hasSignatures();
    }

    @BConstructor
    android.content.pm.PackageParser _new(String str);

    @BMethod
    void collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    @BMethod
    android.content.pm.PackageParser.Package parsePackage(File file, String str, DisplayMetrics displayMetrics, int i);
}
