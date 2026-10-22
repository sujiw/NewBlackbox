package black.android.app;

import android.app.PendingIntent;
import android.content.Context;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationContext.class */
@BClassNameNotProcess("android.app.Notification")
public interface NotificationContext {
    @BMethodCheckNotProcess
    Method _check_setLatestEventInfo(Context context, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent);

    Void setLatestEventInfo(Context context, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent);
}
