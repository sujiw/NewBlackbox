package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationM.class */
public class BRNotificationM {
    public static NotificationMStatic getWithException() {
        return (NotificationMStatic) BlackReflection.create(NotificationMStatic.class, (Object) null, true);
    }

    public static NotificationMStatic get() {
        return (NotificationMStatic) BlackReflection.create(NotificationMStatic.class, (Object) null, false);
    }

    public static NotificationMContext getWithException(Object caller) {
        return (NotificationMContext) BlackReflection.create(NotificationMContext.class, caller, true);
    }

    public static NotificationMContext get(Object caller) {
        return (NotificationMContext) BlackReflection.create(NotificationMContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationMContext.class);
    }
}
