package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRPeriodicSync.class */
public class BRPeriodicSync {
    public static PeriodicSyncStatic getWithException() {
        return (PeriodicSyncStatic) BlackReflection.create(PeriodicSyncStatic.class, (Object) null, true);
    }

    public static PeriodicSyncStatic get() {
        return (PeriodicSyncStatic) BlackReflection.create(PeriodicSyncStatic.class, (Object) null, false);
    }

    public static PeriodicSyncContext getWithException(Object caller) {
        return (PeriodicSyncContext) BlackReflection.create(PeriodicSyncContext.class, caller, true);
    }

    public static PeriodicSyncContext get(Object caller) {
        return (PeriodicSyncContext) BlackReflection.create(PeriodicSyncContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PeriodicSyncContext.class);
    }
}
