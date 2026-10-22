package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotification.class */
public class BRNotification {
    public static NotificationStatic getWithException() {
        return (NotificationStatic) BlackReflection.create(NotificationStatic.class, (Object) null, true);
    }

    public static NotificationStatic get() {
        return (NotificationStatic) BlackReflection.create(NotificationStatic.class, (Object) null, false);
    }

    public static NotificationContext getWithException(Object caller) {
        return (NotificationContext) BlackReflection.create(NotificationContext.class, caller, true);
    }

    public static NotificationContext get(Object caller) {
        return (NotificationContext) BlackReflection.create(NotificationContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationContext.class);
    }
}
