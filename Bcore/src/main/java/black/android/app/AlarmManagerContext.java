package black.android.app;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/AlarmManagerContext.class */
@BClassNameNotProcess("android.app.AlarmManager")
public interface AlarmManagerContext {
    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    IInterface mService();

    @BFieldSetNotProcess
    void _set_mTargetSdkVersion(Object obj);

    @BFieldCheckNotProcess
    Field _check_mTargetSdkVersion();

    @BFieldNotProcess
    Integer mTargetSdkVersion();
}
