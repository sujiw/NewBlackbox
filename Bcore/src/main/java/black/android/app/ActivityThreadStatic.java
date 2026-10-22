package black.android.app;

import android.app.Application;
import android.os.IInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadStatic.class */
@BClassNameNotProcess("android.app.ActivityThread")
public interface ActivityThreadStatic {
    @BMethodCheckNotProcess
    Method _check_currentActivityThread();

    Object currentActivityThread();

    @BMethodCheckNotProcess
    Method _check_currentApplication();

    Application currentApplication();

    @BMethodCheckNotProcess
    Method _check_currentPackageName();

    String currentPackageName();

    @BFieldSetNotProcess
    void _set_sPackageManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_sPackageManager();

    @BFieldNotProcess
    IInterface sPackageManager();

    @BFieldSetNotProcess
    void _set_sPermissionManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_sPermissionManager();

    @BFieldNotProcess
    IInterface sPermissionManager();
}
