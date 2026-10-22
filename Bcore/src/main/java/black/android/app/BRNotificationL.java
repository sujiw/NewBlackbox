package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationL.class */
public class BRNotificationL {
    public static NotificationLStatic getWithException() {
        return (NotificationLStatic) BlackReflection.create(NotificationLStatic.class, (Object) null, true);
    }

    public static NotificationLStatic get() {
        return (NotificationLStatic) BlackReflection.create(NotificationLStatic.class, (Object) null, false);
    }

    public static NotificationLContext getWithException(Object caller) {
        return (NotificationLContext) BlackReflection.create(NotificationLContext.class, caller, true);
    }

    public static NotificationLContext get(Object caller) {
        return (NotificationLContext) BlackReflection.create(NotificationLContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationLContext.class);
    }
}
