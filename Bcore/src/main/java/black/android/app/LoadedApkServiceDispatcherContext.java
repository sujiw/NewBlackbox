package black.android.app;

import android.content.Context;
import android.content.ServiceConnection;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApkServiceDispatcherContext.class */
@BClassNameNotProcess("android.app.LoadedApk$ServiceDispatcher")
public interface LoadedApkServiceDispatcherContext {
    @BFieldSetNotProcess
    void _set_mConnection(Object obj);

    @BFieldCheckNotProcess
    Field _check_mConnection();

    @BFieldNotProcess
    ServiceConnection mConnection();

    @BFieldSetNotProcess
    void _set_mContext(Object obj);

    @BFieldCheckNotProcess
    Field _check_mContext();

    @BFieldNotProcess
    Context mContext();
}
