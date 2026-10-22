package black.android.app;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IBinder;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BParamClassName;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadContext.class */
@BClassNameNotProcess("android.app.ActivityThread")
public interface ActivityThreadContext {
    @BMethodCheckNotProcess
    Method _check_getApplicationThread();

    IBinder getApplicationThread();

    @BMethodCheckNotProcess
    Method _check_getHandler();

    Handler getHandler();

    @BMethodCheckNotProcess
    Method _check_getProcessName();

    String getProcessName();

    @BMethodCheckNotProcess
    Method _check_getSystemContext();

    Object getSystemContext();

    @BMethodCheckNotProcess
    Method _check_getActivityClient(IBinder iBinder);

    Object getActivityClient(IBinder iBinder);

    @BMethodCheckNotProcess
    Method _check_getLaunchingActivity(IBinder iBinder);

    Object getLaunchingActivity(IBinder iBinder);

    @BMethodCheckNotProcess
    Method _check_getPackageInfo(ApplicationInfo applicationInfo, @BParamClassName("android.content.res.CompatibilityInfo") Object obj, int i);

    Object getPackageInfo(ApplicationInfo applicationInfo, @BParamClassName("android.content.res.CompatibilityInfo") Object obj, int i);

    @BMethodCheckNotProcess
    Method _check_performNewIntents(IBinder iBinder, List list);

    Void performNewIntents(IBinder iBinder, List list);

    @BMethodCheckNotProcess
    Method _check_sendActivityResult(IBinder iBinder, String str, int i, int i2, Intent intent);

    Void sendActivityResult(IBinder iBinder, String str, int i, int i2, Intent intent);

    @BFieldSetNotProcess
    void _set_mAppThread(Object obj);

    @BFieldCheckNotProcess
    Field _check_mAppThread();

    @BFieldNotProcess
    Object mAppThread();

    @BFieldSetNotProcess
    void _set_mActivities(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActivities();

    @BFieldNotProcess
    Map<IBinder, Object> mActivities();

    @BFieldSetNotProcess
    void _set_mBoundApplication(Object obj);

    @BFieldCheckNotProcess
    Field _check_mBoundApplication();

    @BFieldNotProcess
    Object mBoundApplication();

    @BFieldSetNotProcess
    void _set_mH(Object obj);

    @BFieldCheckNotProcess
    Field _check_mH();

    @BFieldNotProcess
    Handler mH();

    @BFieldSetNotProcess
    void _set_mInitialApplication(Object obj);

    @BFieldCheckNotProcess
    Field _check_mInitialApplication();

    @BFieldNotProcess
    Application mInitialApplication();

    @BFieldSetNotProcess
    void _set_mInstrumentation(Object obj);

    @BFieldCheckNotProcess
    Field _check_mInstrumentation();

    @BFieldNotProcess
    android.app.Instrumentation mInstrumentation();

    @BFieldSetNotProcess
    void _set_mPackages(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackages();

    @BFieldNotProcess
    Map<String, WeakReference<?>> mPackages();

    @BFieldSetNotProcess
    void _set_mProviderMap(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProviderMap();

    @BFieldNotProcess
    Map<?, ?> mProviderMap();

    @BFieldSetNotProcess
    void _set_mLocalProvidersByName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLocalProvidersByName();

    @BFieldNotProcess
    Map<?, ?> mLocalProvidersByName();
}
