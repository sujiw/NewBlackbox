package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityManagerL.class */
public class BRIActivityManagerL {
    public static IActivityManagerLStatic getWithException() {
        return (IActivityManagerLStatic) BlackReflection.create(IActivityManagerLStatic.class, (Object) null, true);
    }

    public static IActivityManagerLStatic get() {
        return (IActivityManagerLStatic) BlackReflection.create(IActivityManagerLStatic.class, (Object) null, false);
    }

    public static IActivityManagerLContext getWithException(Object caller) {
        return (IActivityManagerLContext) BlackReflection.create(IActivityManagerLContext.class, caller, true);
    }

    public static IActivityManagerLContext get(Object caller) {
        return (IActivityManagerLContext) BlackReflection.create(IActivityManagerLContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityManagerLContext.class);
    }
}
