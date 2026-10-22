package black.android.os.storage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/BRIStorageManager.class */
public class BRIStorageManager {
    public static IStorageManagerStatic getWithException() {
        return (IStorageManagerStatic) BlackReflection.create(IStorageManagerStatic.class, (Object) null, true);
    }

    public static IStorageManagerStatic get() {
        return (IStorageManagerStatic) BlackReflection.create(IStorageManagerStatic.class, (Object) null, false);
    }

    public static IStorageManagerContext getWithException(Object caller) {
        return (IStorageManagerContext) BlackReflection.create(IStorageManagerContext.class, caller, true);
    }

    public static IStorageManagerContext get(Object caller) {
        return (IStorageManagerContext) BlackReflection.create(IStorageManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IStorageManagerContext.class);
    }
}
