package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApkKitkatContext.class */
@BClassNameNotProcess("android.app.LoadedApk")
public interface LoadedApkKitkatContext {
    @BFieldSetNotProcess
    void _set_mDisplayAdjustments(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDisplayAdjustments();

    @BFieldNotProcess
    Object mDisplayAdjustments();
}
