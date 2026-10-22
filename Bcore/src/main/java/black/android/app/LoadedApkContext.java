package black.android.app;

import android.app.Application;
import android.app.IServiceConnection;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApkContext.class */
@BClassNameNotProcess("android.app.LoadedApk")
public interface LoadedApkContext {
    @BMethodCheckNotProcess
    Method _check_getResources();

    Object getResources();

    @BMethodCheckNotProcess
    Method _check_forgetServiceDispatcher(Context context, ServiceConnection serviceConnection);

    IServiceConnection forgetServiceDispatcher(Context context, ServiceConnection serviceConnection);

    @BMethodCheckNotProcess
    Method _check_getClassLoader();

    ClassLoader getClassLoader();

    @BMethodCheckNotProcess
    Method _check_getServiceDispatcher(ServiceConnection serviceConnection, Context context, Handler handler, int i);

    IServiceConnection getServiceDispatcher(ServiceConnection serviceConnection, Context context, Handler handler, int i);

    @BMethodCheckNotProcess
    Method _check_makeApplication(boolean z, android.app.Instrumentation instrumentation);

    Application makeApplication(boolean z, android.app.Instrumentation instrumentation);

    @BFieldSetNotProcess
    void _set_mApplication(Object obj);

    @BFieldCheckNotProcess
    Field _check_mApplication();

    @BFieldNotProcess
    Application mApplication();

    @BFieldSetNotProcess
    void _set_mApplicationInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_mApplicationInfo();

    @BFieldNotProcess
    ApplicationInfo mApplicationInfo();

    @BFieldSetNotProcess
    void _set_mCredentialProtectedDataDirFile(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCredentialProtectedDataDirFile();

    @BFieldNotProcess
    File mCredentialProtectedDataDirFile();

    @BFieldSetNotProcess
    void _set_mDataDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDataDir();

    @BFieldNotProcess
    String mDataDir();

    @BFieldSetNotProcess
    void _set_mDataDirFile(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDataDirFile();

    @BFieldNotProcess
    File mDataDirFile();

    @BFieldSetNotProcess
    void _set_mDeviceProtectedDataDirFile(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDeviceProtectedDataDirFile();

    @BFieldNotProcess
    File mDeviceProtectedDataDirFile();

    @BFieldSetNotProcess
    void _set_mLibDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLibDir();

    @BFieldNotProcess
    String mLibDir();

    @BFieldSetNotProcess
    void _set_mSecurityViolation(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSecurityViolation();

    @BFieldNotProcess
    Boolean mSecurityViolation();

    @BFieldSetNotProcess
    void _set_mPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackageName();

    @BFieldNotProcess
    Boolean mPackageName();
}
