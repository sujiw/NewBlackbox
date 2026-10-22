package black.android.app;

import android.content.Context;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationLBuilderStatic.class */
@BClassNameNotProcess("android.app.Notification$Builder")
public interface NotificationLBuilderStatic {
    @BMethodCheckNotProcess
    Method _check_rebuild(Context context, android.app.Notification notification);

    android.app.Notification rebuild(Context context, android.app.Notification notification);
}
