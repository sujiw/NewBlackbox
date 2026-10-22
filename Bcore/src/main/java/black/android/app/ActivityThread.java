package black.android.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.view.Window;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BMethod;
import top.niunaijun.blackreflection.annotation.BParamClassName;
import top.niunaijun.blackreflection.annotation.BStaticField;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread.class */
@BClassName("android.app.ActivityThread")
public interface ActivityThread {

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$ActivityClientRecord.class */
    @BClassName("android.app.ActivityThread$ActivityClientRecord")
    public interface ActivityClientRecord {
        @BField
        android.app.Activity activity();

        @BField
        ActivityInfo activityInfo();

        @BField
        Intent intent();

        @BField
        Boolean isTopResumedActivity();

        @BField
        IBinder token();

        @BField
        Object packageInfo();

        @BField
        Window window();

        @BMethod
        String toString();

        @BField
        boolean mPreserveWindow();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$AppBindData.class */
    @BClassName("android.app.ActivityThread$AppBindData")
    public interface AppBindData {
        @BField
        ApplicationInfo appInfo();

        @BField
        Object info();

        @BField
        ComponentName instrumentationName();

        @BField
        String processName();

        @BField
        List<ProviderInfo> providers();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$CreateServiceData.class */
    @BClassName("android.app.ActivityThread$CreateServiceData")
    public interface CreateServiceData {
        @BField
        Object compatInfo();

        @BField
        ServiceInfo info();

        @BField
        Intent intent();

        @BField
        IBinder token();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$H.class */
    @BClassName("android.app.ActivityThread$H")
    public interface H {
        @BStaticField
        int CREATE_SERVICE();

        @BStaticField
        int EXECUTE_TRANSACTION();

        @BStaticField
        int LAUNCH_ACTIVITY();

        @BStaticField
        int SCHEDULE_CRASH();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$ProviderClientRecord.class */
    @BClassName("android.app.ActivityThread$ProviderClientRecord")
    public interface ProviderClientRecord {
        @BConstructor
        ProviderClientRecord _new();

        @BField
        String mName();

        @BField
        IInterface mProvider();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$ProviderClientRecordJB.class */
    @BClassName("android.app.ActivityThread$ProviderClientRecord")
    public interface ProviderClientRecordJB {
        @BField
        Object mHolder();

        @BField
        IInterface mProvider();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$ProviderClientRecordP.class */
    @BClassName("android.app.ActivityThread$ProviderClientRecord")
    public interface ProviderClientRecordP {
        @BConstructor
        ProviderClientRecordP _new();

        @BField
        String[] mNames();

        @BField
        IInterface mProvider();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThread$ProviderKeyJBMR1.class */
    @BClassName("android.app.ActivityThread$ProviderKey")
    public interface ProviderKeyJBMR1 {
        @BConstructor
        ProviderKeyJBMR1 _new(String str, int i);
    }

    @BField
    Object mAppThread();

    @BStaticField
    IInterface sPackageManager();

    @BStaticField
    IInterface sPermissionManager();

    @BField
    Map<IBinder, Object> mActivities();

    @BField
    Object mBoundApplication();

    @BField
    Handler mH();

    @BField
    Application mInitialApplication();

    @BField
    android.app.Instrumentation mInstrumentation();

    @BField
    Map<String, WeakReference<?>> mPackages();

    @BField
    Map<?, ?> mProviderMap();

    @BField
    Map<?, ?> mLocalProvidersByName();

    @BStaticMethod
    Object currentActivityThread();

    @BStaticMethod
    Application currentApplication();

    @BStaticMethod
    String currentPackageName();

    @BMethod
    IBinder getApplicationThread();

    @BMethod
    Handler getHandler();

    @BMethod
    String getProcessName();

    @BMethod
    Object getSystemContext();

    @BMethod
    Object getActivityClient(IBinder iBinder);

    @BMethod
    Object getLaunchingActivity(IBinder iBinder);

    @BMethod
    Object getPackageInfo(ApplicationInfo applicationInfo, @BParamClassName("android.content.res.CompatibilityInfo") Object obj, int i);

    @BMethod
    void performNewIntents(IBinder iBinder, List list);

    @BMethod
    void sendActivityResult(IBinder iBinder, String str, int i, int i2, Intent intent);
}
