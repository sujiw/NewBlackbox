package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIUserManagerStub.class */
public class BRIUserManagerStub {
    public static IUserManagerStubStatic getWithException() {
        return (IUserManagerStubStatic) BlackReflection.create(IUserManagerStubStatic.class, (Object) null, true);
    }

    public static IUserManagerStubStatic get() {
        return (IUserManagerStubStatic) BlackReflection.create(IUserManagerStubStatic.class, (Object) null, false);
    }

    public static IUserManagerStubContext getWithException(Object caller) {
        return (IUserManagerStubContext) BlackReflection.create(IUserManagerStubContext.class, caller, true);
    }

    public static IUserManagerStubContext get(Object caller) {
        return (IUserManagerStubContext) BlackReflection.create(IUserManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IUserManagerStubContext.class);
    }
}
