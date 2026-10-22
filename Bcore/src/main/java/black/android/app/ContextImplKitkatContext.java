package black.android.app;

import java.io.File;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ContextImplKitkatContext.class */
@BClassNameNotProcess("android.app.ContextImpl")
public interface ContextImplKitkatContext {
    @BFieldSetNotProcess
    void _set_mDisplayAdjustments(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDisplayAdjustments();

    @BFieldNotProcess
    Object mDisplayAdjustments();

    @BFieldSetNotProcess
    void _set_mExternalCacheDirs(Object obj);

    @BFieldCheckNotProcess
    Field _check_mExternalCacheDirs();

    @BFieldNotProcess
    File[] mExternalCacheDirs();

    @BFieldSetNotProcess
    void _set_mExternalFilesDirs(Object obj);

    @BFieldCheckNotProcess
    Field _check_mExternalFilesDirs();

    @BFieldNotProcess
    File[] mExternalFilesDirs();

    @BFieldSetNotProcess
    void _set_mOpPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mOpPackageName();

    @BFieldNotProcess
    String mOpPackageName();
}
