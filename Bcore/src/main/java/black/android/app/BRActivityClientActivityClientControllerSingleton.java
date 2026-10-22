package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityClientActivityClientControllerSingleton.class */
public class BRActivityClientActivityClientControllerSingleton {
    public static ActivityClientActivityClientControllerSingletonStatic getWithException() {
        return (ActivityClientActivityClientControllerSingletonStatic) BlackReflection.create(ActivityClientActivityClientControllerSingletonStatic.class, (Object) null, true);
    }

    public static ActivityClientActivityClientControllerSingletonStatic get() {
        return (ActivityClientActivityClientControllerSingletonStatic) BlackReflection.create(ActivityClientActivityClientControllerSingletonStatic.class, (Object) null, false);
    }

    public static ActivityClientActivityClientControllerSingletonContext getWithException(Object caller) {
        return (ActivityClientActivityClientControllerSingletonContext) BlackReflection.create(ActivityClientActivityClientControllerSingletonContext.class, caller, true);
    }

    public static ActivityClientActivityClientControllerSingletonContext get(Object caller) {
        return (ActivityClientActivityClientControllerSingletonContext) BlackReflection.create(ActivityClientActivityClientControllerSingletonContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityClientActivityClientControllerSingletonContext.class);
    }
}
