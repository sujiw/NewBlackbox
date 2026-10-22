package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationChannel.class */
public class BRNotificationChannel {
    public static NotificationChannelStatic getWithException() {
        return (NotificationChannelStatic) BlackReflection.create(NotificationChannelStatic.class, (Object) null, true);
    }

    public static NotificationChannelStatic get() {
        return (NotificationChannelStatic) BlackReflection.create(NotificationChannelStatic.class, (Object) null, false);
    }

    public static NotificationChannelContext getWithException(Object caller) {
        return (NotificationChannelContext) BlackReflection.create(NotificationChannelContext.class, caller, true);
    }

    public static NotificationChannelContext get(Object caller) {
        return (NotificationChannelContext) BlackReflection.create(NotificationChannelContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationChannelContext.class);
    }
}
