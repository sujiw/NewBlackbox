package black.android.app;

import android.app.PendingIntent;
import android.content.Context;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/Notification.class */
@BClassName("android.app.Notification")
public interface Notification {
    @BMethod
    void setLatestEventInfo(Context context, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent);
}
