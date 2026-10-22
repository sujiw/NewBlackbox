package black.android.app;

import android.app.Application;
import android.app.IServiceConnection;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IInterface;
import java.io.File;
import java.lang.ref.WeakReference;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApk.class */
@BClassName("android.app.LoadedApk")
public interface LoadedApk {

    /* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApk$ReceiverDispatcher.class */
    @BClassName("android.app.LoadedApk$ReceiverDispatcher")
    public interface ReceiverDispatcher {

        /* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApk$ReceiverDispatcher$InnerReceiver.class */
        @BClassName("android.app.LoadedApk$ReceiverDispatcher$InnerReceiver")
        public interface InnerReceiver {
            @BField
            WeakReference<?> mDispatcher();
        }

        @BField
        IIntentReceiver mIIntentReceiver();

        @BField
        BroadcastReceiver mReceiver();

        @BMethod
        IInterface getIIntentReceiver();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApk$ServiceDispatcher.class */
    @BClassName("android.app.LoadedApk$ServiceDispatcher")
    public interface ServiceDispatcher {

        /* JADX INFO: loaded from: classes.jar:black/android/app/LoadedApk$ServiceDispatcher$InnerConnection.class */
        @BClassName("android.app.LoadedApk$ServiceDispatcher$InnerConnection")
        public interface InnerConnection {
            @BField
            WeakReference<?> mDispatcher();
        }

        @BField
        ServiceConnection mConnection();

        @BField
        Context mContext();
    }

    @BField
    Application mApplication();

    @BField
    ApplicationInfo mApplicationInfo();

    @BField
    File mCredentialProtectedDataDirFile();

    @BField
    String mDataDir();

    @BField
    File mDataDirFile();

    @BField
    File mDeviceProtectedDataDirFile();

    @BField
    String mLibDir();

    @BField
    boolean mSecurityViolation();

    @BField
    boolean mPackageName();

    @BMethod
    Object getResources();

    @BMethod
    IServiceConnection forgetServiceDispatcher(Context context, ServiceConnection serviceConnection);

    @BMethod
    ClassLoader getClassLoader();

    @BMethod
    IServiceConnection getServiceDispatcher(ServiceConnection serviceConnection, Context context, Handler handler, int i);

    @BMethod
    Application makeApplication(boolean z, android.app.Instrumentation instrumentation);
}
