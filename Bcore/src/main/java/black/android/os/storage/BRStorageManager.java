package black.android.os.storage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/BRStorageManager.class */
public class BRStorageManager {
    public static StorageManagerStatic getWithException() {
        return (StorageManagerStatic) BlackReflection.create(StorageManagerStatic.class, (Object) null, true);
    }

    public static StorageManagerStatic get() {
        return (StorageManagerStatic) BlackReflection.create(StorageManagerStatic.class, (Object) null, false);
    }

    public static StorageManagerContext getWithException(Object caller) {
        return (StorageManagerContext) BlackReflection.create(StorageManagerContext.class, caller, true);
    }

    public static StorageManagerContext get(Object caller) {
        return (StorageManagerContext) BlackReflection.create(StorageManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(StorageManagerContext.class);
    }
}
