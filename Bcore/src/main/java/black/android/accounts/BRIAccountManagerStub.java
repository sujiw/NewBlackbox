package black.android.accounts;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/accounts/BRIAccountManagerStub.class */
public class BRIAccountManagerStub {
    public static IAccountManagerStubStatic getWithException() {
        return (IAccountManagerStubStatic) BlackReflection.create(IAccountManagerStubStatic.class, (Object) null, true);
    }

    public static IAccountManagerStubStatic get() {
        return (IAccountManagerStubStatic) BlackReflection.create(IAccountManagerStubStatic.class, (Object) null, false);
    }

    public static IAccountManagerStubContext getWithException(Object caller) {
        return (IAccountManagerStubContext) BlackReflection.create(IAccountManagerStubContext.class, caller, true);
    }

    public static IAccountManagerStubContext get(Object caller) {
        return (IAccountManagerStubContext) BlackReflection.create(IAccountManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAccountManagerStubContext.class);
    }
}
