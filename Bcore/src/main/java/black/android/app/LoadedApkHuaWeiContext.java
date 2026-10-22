package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApkHuaWeiContext.class */
@BClassNameNotProcess("android.app.LoadedApk")
public interface LoadedApkHuaWeiContext {
    @BFieldSetNotProcess
    void _set_mReceiverResource(Object obj);

    @BFieldCheckNotProcess
    Field _check_mReceiverResource();

    @BFieldNotProcess
    Object mReceiverResource();
}
