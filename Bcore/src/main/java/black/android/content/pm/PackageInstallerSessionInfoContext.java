package black.android.content.pm;

import android.graphics.Bitmap;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageInstallerSessionInfoContext.class */
@BClassNameNotProcess("android.content.pm.PackageInstaller$SessionInfo")
public interface PackageInstallerSessionInfoContext {
    @BFieldSetNotProcess
    void _set_active(Object obj);

    @BFieldCheckNotProcess
    Field _check_active();

    @BFieldNotProcess
    Boolean active();

    @BFieldSetNotProcess
    void _set_appIcon(Object obj);

    @BFieldCheckNotProcess
    Field _check_appIcon();

    @BFieldNotProcess
    Bitmap appIcon();

    @BFieldSetNotProcess
    void _set_appLabel(Object obj);

    @BFieldCheckNotProcess
    Field _check_appLabel();

    @BFieldNotProcess
    CharSequence appLabel();

    @BFieldSetNotProcess
    void _set_appPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_appPackageName();

    @BFieldNotProcess
    String appPackageName();

    @BFieldSetNotProcess
    void _set_installerPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_installerPackageName();

    @BFieldNotProcess
    String installerPackageName();

    @BFieldSetNotProcess
    void _set_mode(Object obj);

    @BFieldCheckNotProcess
    Field _check_mode();

    @BFieldNotProcess
    Integer mode();

    @BFieldSetNotProcess
    void _set_progress(Object obj);

    @BFieldCheckNotProcess
    Field _check_progress();

    @BFieldNotProcess
    Float progress();

    @BFieldSetNotProcess
    void _set_resolvedBaseCodePath(Object obj);

    @BFieldCheckNotProcess
    Field _check_resolvedBaseCodePath();

    @BFieldNotProcess
    String resolvedBaseCodePath();

    @BFieldSetNotProcess
    void _set_sealed(Object obj);

    @BFieldCheckNotProcess
    Field _check_sealed();

    @BFieldNotProcess
    Boolean sealed();

    @BFieldSetNotProcess
    void _set_sessionId(Object obj);

    @BFieldCheckNotProcess
    Field _check_sessionId();

    @BFieldNotProcess
    Integer sessionId();

    @BFieldSetNotProcess
    void _set_sizeBytes(Object obj);

    @BFieldCheckNotProcess
    Field _check_sizeBytes();

    @BFieldNotProcess
    Long sizeBytes();
}
