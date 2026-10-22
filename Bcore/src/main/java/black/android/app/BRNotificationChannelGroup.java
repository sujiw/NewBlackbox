package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationChannelGroup.class */
public class BRNotificationChannelGroup {
    public static NotificationChannelGroupStatic getWithException() {
        return (NotificationChannelGroupStatic) BlackReflection.create(NotificationChannelGroupStatic.class, (Object) null, true);
    }

    public static NotificationChannelGroupStatic get() {
        return (NotificationChannelGroupStatic) BlackReflection.create(NotificationChannelGroupStatic.class, (Object) null, false);
    }

    public static NotificationChannelGroupContext getWithException(Object caller) {
        return (NotificationChannelGroupContext) BlackReflection.create(NotificationChannelGroupContext.class, caller, true);
    }

    public static NotificationChannelGroupContext get(Object caller) {
        return (NotificationChannelGroupContext) BlackReflection.create(NotificationChannelGroupContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationChannelGroupContext.class);
    }
}
