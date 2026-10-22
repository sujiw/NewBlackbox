package black.android.app.usage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/usage/BRIStorageStatsManagerStub.class */
public class BRIStorageStatsManagerStub {
    public static IStorageStatsManagerStubStatic getWithException() {
        return (IStorageStatsManagerStubStatic) BlackReflection.create(IStorageStatsManagerStubStatic.class, (Object) null, true);
    }

    public static IStorageStatsManagerStubStatic get() {
        return (IStorageStatsManagerStubStatic) BlackReflection.create(IStorageStatsManagerStubStatic.class, (Object) null, false);
    }

    public static IStorageStatsManagerStubContext getWithException(Object caller) {
        return (IStorageStatsManagerStubContext) BlackReflection.create(IStorageStatsManagerStubContext.class, caller, true);
    }

    public static IStorageStatsManagerStubContext get(Object caller) {
        return (IStorageStatsManagerStubContext) BlackReflection.create(IStorageStatsManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IStorageStatsManagerStubContext.class);
    }
}
