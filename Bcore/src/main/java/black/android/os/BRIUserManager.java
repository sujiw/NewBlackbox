package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIUserManager.class */
public class BRIUserManager {
    public static IUserManagerStatic getWithException() {
        return (IUserManagerStatic) BlackReflection.create(IUserManagerStatic.class, (Object) null, true);
    }

    public static IUserManagerStatic get() {
        return (IUserManagerStatic) BlackReflection.create(IUserManagerStatic.class, (Object) null, false);
    }

    public static IUserManagerContext getWithException(Object caller) {
        return (IUserManagerContext) BlackReflection.create(IUserManagerContext.class, caller, true);
    }

    public static IUserManagerContext get(Object caller) {
        return (IUserManagerContext) BlackReflection.create(IUserManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IUserManagerContext.class);
    }
}
