package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRNotificationLBuilder.class */
public class BRNotificationLBuilder {
    public static NotificationLBuilderStatic getWithException() {
        return (NotificationLBuilderStatic) BlackReflection.create(NotificationLBuilderStatic.class, (Object) null, true);
    }

    public static NotificationLBuilderStatic get() {
        return (NotificationLBuilderStatic) BlackReflection.create(NotificationLBuilderStatic.class, (Object) null, false);
    }

    public static NotificationLBuilderContext getWithException(Object caller) {
        return (NotificationLBuilderContext) BlackReflection.create(NotificationLBuilderContext.class, caller, true);
    }

    public static NotificationLBuilderContext get(Object caller) {
        return (NotificationLBuilderContext) BlackReflection.create(NotificationLBuilderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NotificationLBuilderContext.class);
    }
}
