package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRUserManager.class */
public class BRUserManager {
    public static UserManagerStatic getWithException() {
        return (UserManagerStatic) BlackReflection.create(UserManagerStatic.class, (Object) null, true);
    }

    public static UserManagerStatic get() {
        return (UserManagerStatic) BlackReflection.create(UserManagerStatic.class, (Object) null, false);
    }

    public static UserManagerContext getWithException(Object caller) {
        return (UserManagerContext) BlackReflection.create(UserManagerContext.class, caller, true);
    }

    public static UserManagerContext get(Object caller) {
        return (UserManagerContext) BlackReflection.create(UserManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(UserManagerContext.class);
    }
}
