package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityManagerN.class */
public class BRIActivityManagerN {
    public static IActivityManagerNStatic getWithException() {
        return (IActivityManagerNStatic) BlackReflection.create(IActivityManagerNStatic.class, (Object) null, true);
    }

    public static IActivityManagerNStatic get() {
        return (IActivityManagerNStatic) BlackReflection.create(IActivityManagerNStatic.class, (Object) null, false);
    }

    public static IActivityManagerNContext getWithException(Object caller) {
        return (IActivityManagerNContext) BlackReflection.create(IActivityManagerNContext.class, caller, true);
    }

    public static IActivityManagerNContext get(Object caller) {
        return (IActivityManagerNContext) BlackReflection.create(IActivityManagerNContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityManagerNContext.class);
    }
}
