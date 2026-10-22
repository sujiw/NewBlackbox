package black.android.app;

import java.io.File;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ContextImplICSContext.class */
@BClassNameNotProcess("android.app.ContextImpl")
public interface ContextImplICSContext {
    @BFieldSetNotProcess
    void _set_mExternalCacheDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_mExternalCacheDir();

    @BFieldNotProcess
    File mExternalCacheDir();

    @BFieldSetNotProcess
    void _set_mExternalFilesDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_mExternalFilesDir();

    @BFieldNotProcess
    File mExternalFilesDir();
}
