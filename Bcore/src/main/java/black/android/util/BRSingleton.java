package black.android.util;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/util/BRSingleton.class */
public class BRSingleton {
    public static SingletonStatic getWithException() {
        return (SingletonStatic) BlackReflection.create(SingletonStatic.class, (Object) null, true);
    }

    public static SingletonStatic get() {
        return (SingletonStatic) BlackReflection.create(SingletonStatic.class, (Object) null, false);
    }

    public static SingletonContext getWithException(Object caller) {
        return (SingletonContext) BlackReflection.create(SingletonContext.class, caller, true);
    }

    public static SingletonContext get(Object caller) {
        return (SingletonContext) BlackReflection.create(SingletonContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SingletonContext.class);
    }
}
