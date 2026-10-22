package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationManager.class */
public class BRNotificationManager {
    public static NotificationManagerStatic getWithException() {
        return (NotificationManagerStatic) BlackReflection.create(NotificationManagerStatic.class, (Object) null, true);
    }

    public static NotificationManagerStatic get() {
        return (NotificationManagerStatic) BlackReflection.create(NotificationManagerStatic.class, (Object) null, false);
    }

    public static NotificationManagerContext getWithException(Object caller) {
        return (NotificationManagerContext) BlackReflection.create(NotificationManagerContext.class, caller, true);
    }

    public static NotificationManagerContext get(Object caller) {
        return (NotificationManagerContext) BlackReflection.create(NotificationManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationManagerContext.class);
    }
}
