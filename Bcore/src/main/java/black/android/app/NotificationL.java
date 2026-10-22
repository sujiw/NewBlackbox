package black.android.app;

import android.content.Context;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationL.class */
@BClassName("android.app.Notification")
public interface NotificationL {

    /* JADX INFO: loaded from: classes.jar:black/android/app/NotificationL$Builder.class */
    @BClassName("android.app.Notification$Builder")
    public interface Builder {
        @BStaticMethod
        android.app.Notification rebuild(Context context, android.app.Notification notification);
    }
}
