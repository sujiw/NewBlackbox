package black.android.content.pm;

import android.graphics.Bitmap;
import android.net.Uri;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstallerSessionParamsMarshmallowContext.class */
@BClassNameNotProcess("android.content.pm.PackageInstaller$SessionParams")
public interface PackageInstallerSessionParamsMarshmallowContext {
    @BFieldSetNotProcess
    void _set_abiOverride(Object obj);

    @BFieldCheckNotProcess
    Field _check_abiOverride();

    @BFieldNotProcess
    String abiOverride();

    @BFieldSetNotProcess
    void _set_appIcon(Object obj);

    @BFieldCheckNotProcess
    Field _check_appIcon();

    @BFieldNotProcess
    Bitmap appIcon();

    @BFieldSetNotProcess
    void _set_appIconLastModified(Object obj);

    @BFieldCheckNotProcess
    Field _check_appIconLastModified();

    @BFieldNotProcess
    Long appIconLastModified();

    @BFieldSetNotProcess
    void _set_appLabel(Object obj);

    @BFieldCheckNotProcess
    Field _check_appLabel();

    @BFieldNotProcess
    String appLabel();

    @BFieldSetNotProcess
    void _set_appPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_appPackageName();

    @BFieldNotProcess
    String appPackageName();

    @BFieldSetNotProcess
    void _set_grantedRuntimePermissions(Object obj);

    @BFieldCheckNotProcess
    Field _check_grantedRuntimePermissions();

    @BFieldNotProcess
    String[] grantedRuntimePermissions();

    @BFieldSetNotProcess
    void _set_installFlags(Object obj);

    @BFieldCheckNotProcess
    Field _check_installFlags();

    @BFieldNotProcess
    Integer installFlags();

    @BFieldSetNotProcess
    void _set_installLocation(Object obj);

    @BFieldCheckNotProcess
    Field _check_installLocation();

    @BFieldNotProcess
    Integer installLocation();

    @BFieldSetNotProcess
    void _set_mode(Object obj);

    @BFieldCheckNotProcess
    Field _check_mode();

    @BFieldNotProcess
    Integer mode();

    @BFieldSetNotProcess
    void _set_originatingUri(Object obj);

    @BFieldCheckNotProcess
    Field _check_originatingUri();

    @BFieldNotProcess
    Uri originatingUri();

    @BFieldSetNotProcess
    void _set_referrerUri(Object obj);

    @BFieldCheckNotProcess
    Field _check_referrerUri();

    @BFieldNotProcess
    Uri referrerUri();

    @BFieldSetNotProcess
    void _set_sizeBytes(Object obj);

    @BFieldCheckNotProcess
    Field _check_sizeBytes();

    @BFieldNotProcess
    Long sizeBytes();

    @BFieldSetNotProcess
    void _set_volumeUuid(Object obj);

    @BFieldCheckNotProcess
    Field _check_volumeUuid();

    @BFieldNotProcess
    String volumeUuid();
}
