package black.android.app.usage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/usage/BRStorageStats.class */
public class BRStorageStats {
    public static StorageStatsStatic getWithException() {
        return (StorageStatsStatic) BlackReflection.create(StorageStatsStatic.class, (Object) null, true);
    }

    public static StorageStatsStatic get() {
        return (StorageStatsStatic) BlackReflection.create(StorageStatsStatic.class, (Object) null, false);
    }

    public static StorageStatsContext getWithException(Object caller) {
        return (StorageStatsContext) BlackReflection.create(StorageStatsContext.class, caller, true);
    }

    public static StorageStatsContext get(Object caller) {
        return (StorageStatsContext) BlackReflection.create(StorageStatsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(StorageStatsContext.class);
    }
}
