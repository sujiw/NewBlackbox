package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRHandler.class */
public class BRHandler {
    public static HandlerStatic getWithException() {
        return (HandlerStatic) BlackReflection.create(HandlerStatic.class, (Object) null, true);
    }

    public static HandlerStatic get() {
        return (HandlerStatic) BlackReflection.create(HandlerStatic.class, (Object) null, false);
    }

    public static HandlerContext getWithException(Object caller) {
        return (HandlerContext) BlackReflection.create(HandlerContext.class, caller, true);
    }

    public static HandlerContext get(Object caller) {
        return (HandlerContext) BlackReflection.create(HandlerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(HandlerContext.class);
    }
}
