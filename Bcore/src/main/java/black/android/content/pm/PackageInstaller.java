package black.android.content.pm;

import android.graphics.Bitmap;
import android.net.Uri;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstaller.class */
@BClassName("mirror.android.content.pm.PackageInstaller")
public interface PackageInstaller {

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstaller$SessionInfo.class */
    @BClassName("android.content.pm.PackageInstaller$SessionInfo")
    public interface SessionInfo {
        @BConstructor
        SessionInfo _new();

        @BField
        boolean active();

        @BField
        Bitmap appIcon();

        @BField
        CharSequence appLabel();

        @BField
        String appPackageName();

        @BField
        String installerPackageName();

        @BField
        int mode();

        @BField
        float progress();

        @BField
        String resolvedBaseCodePath();

        @BField
        boolean sealed();

        @BField
        int sessionId();

        @BField
        long sizeBytes();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstaller$SessionParamsLOLLIPOP.class */
    @BClassName("android.content.pm.PackageInstaller$SessionParams")
    public interface SessionParamsLOLLIPOP {
        @BField
        String abiOverride();

        @BField
        Bitmap appIcon();

        @BField
        long appIconLastModified();

        @BField
        String appLabel();

        @BField
        String appPackageName();

        @BField
        int installFlags();

        @BField
        int installLocation();

        @BField
        int mode();

        @BField
        Uri originatingUri();

        @BField
        Uri referrerUri();

        @BField
        long sizeBytes();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstaller$SessionParamsMarshmallow.class */
    @BClassName("android.content.pm.PackageInstaller$SessionParams")
    public interface SessionParamsMarshmallow {
        @BField
        String abiOverride();

        @BField
        Bitmap appIcon();

        @BField
        long appIconLastModified();

        @BField
        String appLabel();

        @BField
        String appPackageName();

        @BField
        String[] grantedRuntimePermissions();

        @BField
        int installFlags();

        @BField
        int installLocation();

        @BField
        int mode();

        @BField
        Uri originatingUri();

        @BField
        Uri referrerUri();

        @BField
        long sizeBytes();

        @BField
        String volumeUuid();
    }
}
