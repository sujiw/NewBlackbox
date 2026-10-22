package black.android.service.notification;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/service/notification/BRStatusBarNotification.class */
public class BRStatusBarNotification {
    public static StatusBarNotificationStatic getWithException() {
        return (StatusBarNotificationStatic) BlackReflection.create(StatusBarNotificationStatic.class, (Object) null, true);
    }

    public static StatusBarNotificationStatic get() {
        return (StatusBarNotificationStatic) BlackReflection.create(StatusBarNotificationStatic.class, (Object) null, false);
    }

    public static StatusBarNotificationContext getWithException(Object caller) {
        return (StatusBarNotificationContext) BlackReflection.create(StatusBarNotificationContext.class, caller, true);
    }

    public static StatusBarNotificationContext get(Object caller) {
        return (StatusBarNotificationContext) BlackReflection.create(StatusBarNotificationContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(StatusBarNotificationContext.class);
    }
}
