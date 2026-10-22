package black.android.accounts;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/accounts/BRIAccountManager.class */
public class BRIAccountManager {
    public static IAccountManagerStatic getWithException() {
        return (IAccountManagerStatic) BlackReflection.create(IAccountManagerStatic.class, (Object) null, true);
    }

    public static IAccountManagerStatic get() {
        return (IAccountManagerStatic) BlackReflection.create(IAccountManagerStatic.class, (Object) null, false);
    }

    public static IAccountManagerContext getWithException(Object caller) {
        return (IAccountManagerContext) BlackReflection.create(IAccountManagerContext.class, caller, true);
    }

    public static IAccountManagerContext get(Object caller) {
        return (IAccountManagerContext) BlackReflection.create(IAccountManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAccountManagerContext.class);
    }
}
