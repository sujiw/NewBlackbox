package black.android.app;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApkServiceDispatcherInnerConnectionContext.class */
@BClassNameNotProcess("android.app.LoadedApk$ServiceDispatcher$InnerConnection")
public interface LoadedApkServiceDispatcherInnerConnectionContext {
    @BFieldSetNotProcess
    void _set_mDispatcher(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDispatcher();

    @BFieldNotProcess
    WeakReference<?> mDispatcher();
}
