package black.android.os.storage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/BRIStorageManagerStub.class */
public class BRIStorageManagerStub {
    public static IStorageManagerStubStatic getWithException() {
        return (IStorageManagerStubStatic) BlackReflection.create(IStorageManagerStubStatic.class, (Object) null, true);
    }

    public static IStorageManagerStubStatic get() {
        return (IStorageManagerStubStatic) BlackReflection.create(IStorageManagerStubStatic.class, (Object) null, false);
    }

    public static IStorageManagerStubContext getWithException(Object caller) {
        return (IStorageManagerStubContext) BlackReflection.create(IStorageManagerStubContext.class, caller, true);
    }

    public static IStorageManagerStubContext get(Object caller) {
        return (IStorageManagerStubContext) BlackReflection.create(IStorageManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IStorageManagerStubContext.class);
    }
}
