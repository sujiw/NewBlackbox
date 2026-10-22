package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRUserHandle.class */
public class BRUserHandle {
    public static UserHandleStatic getWithException() {
        return (UserHandleStatic) BlackReflection.create(UserHandleStatic.class, (Object) null, true);
    }

    public static UserHandleStatic get() {
        return (UserHandleStatic) BlackReflection.create(UserHandleStatic.class, (Object) null, false);
    }

    public static UserHandleContext getWithException(Object caller) {
        return (UserHandleContext) BlackReflection.create(UserHandleContext.class, caller, true);
    }

    public static UserHandleContext get(Object caller) {
        return (UserHandleContext) BlackReflection.create(UserHandleContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(UserHandleContext.class);
    }
}
