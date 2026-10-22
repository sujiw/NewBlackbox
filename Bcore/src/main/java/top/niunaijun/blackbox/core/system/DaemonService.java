package top.niunaijun.blackbox.core.system;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/DaemonService.class */
public class DaemonService extends Service {
    public static final String TAG = "DaemonService";
    private static final int NOTIFY_ID = BlackBoxCore.getHostPkg().hashCode();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent innerIntent = new Intent(this, (Class<?>) DaemonInnerService.class);
        startService(innerIntent);
        if (BuildCompat.isOreo()) {
            showNotification();
            return 1;
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), getPackageName() + ".blackbox_core").setPriority(2);
        startForeground(NOTIFY_ID, builder.build());
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/DaemonService$DaemonInnerService.class */
    public static class DaemonInnerService extends Service {
        @Override // android.app.Service
        public void onCreate() {
            Log.i(DaemonService.TAG, "DaemonInnerService -> onCreate");
            super.onCreate();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(DaemonService.TAG, "DaemonInnerService -> onStartCommand");
            NotificationManager nm = (NotificationManager) getSystemService("notification");
            nm.cancel(DaemonService.NOTIFY_ID);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override // android.app.Service
        public void onDestroy() {
            Log.i(DaemonService.TAG, "DaemonInnerService -> onDestroy");
            super.onDestroy();
        }
    }
}
