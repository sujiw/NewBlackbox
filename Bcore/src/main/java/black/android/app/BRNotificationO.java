package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationO.class */
public class BRNotificationO {
    public static NotificationOStatic getWithException() {
        return (NotificationOStatic) BlackReflection.create(NotificationOStatic.class, (Object) null, true);
    }

    public static NotificationOStatic get() {
        return (NotificationOStatic) BlackReflection.create(NotificationOStatic.class, (Object) null, false);
    }

    public static NotificationOContext getWithException(Object caller) {
        return (NotificationOContext) BlackReflection.create(NotificationOContext.class, caller, true);
    }

    public static NotificationOContext get(Object caller) {
        return (NotificationOContext) BlackReflection.create(NotificationOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationOContext.class);
    }
}
