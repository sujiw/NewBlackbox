package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRUserManagerStub.class */
public class BRUserManagerStub {
    public static UserManagerStubStatic getWithException() {
        return (UserManagerStubStatic) BlackReflection.create(UserManagerStubStatic.class, (Object) null, true);
    }

    public static UserManagerStubStatic get() {
        return (UserManagerStubStatic) BlackReflection.create(UserManagerStubStatic.class, (Object) null, false);
    }

    public static UserManagerStubContext getWithException(Object caller) {
        return (UserManagerStubContext) BlackReflection.create(UserManagerStubContext.class, caller, true);
    }

    public static UserManagerStubContext get(Object caller) {
        return (UserManagerStubContext) BlackReflection.create(UserManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(UserManagerStubContext.class);
    }
}
