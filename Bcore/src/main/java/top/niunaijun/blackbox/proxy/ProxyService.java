package top.niunaijun.blackbox.proxy;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.dispatcher.AppServiceDispatcher;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService.class */
public class ProxyService extends Service {
    public static final String TAG = "StubService";

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P0.class */
    public static class P0 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P1.class */
    public static class P1 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P10.class */
    public static class P10 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P11.class */
    public static class P11 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P12.class */
    public static class P12 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P13.class */
    public static class P13 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P14.class */
    public static class P14 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P15.class */
    public static class P15 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P16.class */
    public static class P16 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P17.class */
    public static class P17 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P18.class */
    public static class P18 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P19.class */
    public static class P19 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P2.class */
    public static class P2 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P20.class */
    public static class P20 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P21.class */
    public static class P21 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P22.class */
    public static class P22 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P23.class */
    public static class P23 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P24.class */
    public static class P24 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P25.class */
    public static class P25 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P26.class */
    public static class P26 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P27.class */
    public static class P27 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P28.class */
    public static class P28 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P29.class */
    public static class P29 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P3.class */
    public static class P3 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P30.class */
    public static class P30 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P31.class */
    public static class P31 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P32.class */
    public static class P32 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P33.class */
    public static class P33 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P34.class */
    public static class P34 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P35.class */
    public static class P35 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P36.class */
    public static class P36 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P37.class */
    public static class P37 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P38.class */
    public static class P38 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P39.class */
    public static class P39 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P4.class */
    public static class P4 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P40.class */
    public static class P40 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P41.class */
    public static class P41 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P42.class */
    public static class P42 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P43.class */
    public static class P43 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P44.class */
    public static class P44 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P45.class */
    public static class P45 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P46.class */
    public static class P46 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P47.class */
    public static class P47 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P48.class */
    public static class P48 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P49.class */
    public static class P49 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P5.class */
    public static class P5 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P6.class */
    public static class P6 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P7.class */
    public static class P7 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P8.class */
    public static class P8 extends ProxyService {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyService$P9.class */
    public static class P9 extends ProxyService {
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return AppServiceDispatcher.get().onBind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppServiceDispatcher.get().onStartCommand(intent, flags, startId);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AppServiceDispatcher.get().onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppServiceDispatcher.get().onConfigurationChanged(newConfig);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        AppServiceDispatcher.get().onLowMemory();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        AppServiceDispatcher.get().onTrimMemory(level);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        AppServiceDispatcher.get().onUnbind(intent);
        return false;
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), getPackageName() + ".blackbox_proxy").setPriority(2);
        if (BuildCompat.isOreo()) {
            startForeground(BlackBoxCore.getHostPkg().hashCode(), builder.build());
        }
    }
}
