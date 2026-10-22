package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityManager.class */
public class BRIActivityManager {
    public static IActivityManagerStatic getWithException() {
        return (IActivityManagerStatic) BlackReflection.create(IActivityManagerStatic.class, (Object) null, true);
    }

    public static IActivityManagerStatic get() {
        return (IActivityManagerStatic) BlackReflection.create(IActivityManagerStatic.class, (Object) null, false);
    }

    public static IActivityManagerContext getWithException(Object caller) {
        return (IActivityManagerContext) BlackReflection.create(IActivityManagerContext.class, caller, true);
    }

    public static IActivityManagerContext get(Object caller) {
        return (IActivityManagerContext) BlackReflection.create(IActivityManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityManagerContext.class);
    }
}
