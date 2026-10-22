package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadQ.class */
public class BRActivityThreadQ {
    public static ActivityThreadQStatic getWithException() {
        return (ActivityThreadQStatic) BlackReflection.create(ActivityThreadQStatic.class, (Object) null, true);
    }

    public static ActivityThreadQStatic get() {
        return (ActivityThreadQStatic) BlackReflection.create(ActivityThreadQStatic.class, (Object) null, false);
    }

    public static ActivityThreadQContext getWithException(Object caller) {
        return (ActivityThreadQContext) BlackReflection.create(ActivityThreadQContext.class, caller, true);
    }

    public static ActivityThreadQContext get(Object caller) {
        return (ActivityThreadQContext) BlackReflection.create(ActivityThreadQContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadQContext.class);
    }
}
