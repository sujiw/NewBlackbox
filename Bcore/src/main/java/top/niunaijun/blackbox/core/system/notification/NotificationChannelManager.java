package top.niunaijun.blackbox.core.system.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/NotificationChannelManager.class */
public class NotificationChannelManager {
    private static final NotificationChannelManager sManager = new NotificationChannelManager();
    public static NotificationChannel APP_CHANNEL;

    public static NotificationChannelManager get() {
        return sManager;
    }

    public NotificationChannelManager() {
        if (Build.VERSION.SDK_INT >= 26) {
            registerAppChannel();
        }
    }

    @RequiresApi(api = 26)
    private void registerAppChannel() {
        NotificationManager nm = (NotificationManager) BlackBoxCore.getContext().getSystemService("notification");
        String CHANNEL_ONE_ID = BlackBoxCore.getContext().getPackageName();
        APP_CHANNEL = new NotificationChannel(CHANNEL_ONE_ID, "black-box-app", 4);
        APP_CHANNEL.enableLights(true);
        APP_CHANNEL.setLightColor(-65536);
        APP_CHANNEL.setShowBadge(true);
        APP_CHANNEL.setLockscreenVisibility(1);
        nm.createNotificationChannel(APP_CHANNEL);
    }
}
