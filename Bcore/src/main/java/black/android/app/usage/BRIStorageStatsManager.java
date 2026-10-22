package black.android.app.usage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/usage/BRIStorageStatsManager.class */
public class BRIStorageStatsManager {
    public static IStorageStatsManagerStatic getWithException() {
        return (IStorageStatsManagerStatic) BlackReflection.create(IStorageStatsManagerStatic.class, (Object) null, true);
    }

    public static IStorageStatsManagerStatic get() {
        return (IStorageStatsManagerStatic) BlackReflection.create(IStorageStatsManagerStatic.class, (Object) null, false);
    }

    public static IStorageStatsManagerContext getWithException(Object caller) {
        return (IStorageStatsManagerContext) BlackReflection.create(IStorageStatsManagerContext.class, caller, true);
    }

    public static IStorageStatsManagerContext get(Object caller) {
        return (IStorageStatsManagerContext) BlackReflection.create(IStorageStatsManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IStorageStatsManagerContext.class);
    }
}
