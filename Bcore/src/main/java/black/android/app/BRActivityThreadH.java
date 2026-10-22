package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadH.class */
public class BRActivityThreadH {
    public static ActivityThreadHStatic getWithException() {
        return (ActivityThreadHStatic) BlackReflection.create(ActivityThreadHStatic.class, (Object) null, true);
    }

    public static ActivityThreadHStatic get() {
        return (ActivityThreadHStatic) BlackReflection.create(ActivityThreadHStatic.class, (Object) null, false);
    }

    public static ActivityThreadHContext getWithException(Object caller) {
        return (ActivityThreadHContext) BlackReflection.create(ActivityThreadHContext.class, caller, true);
    }

    public static ActivityThreadHContext get(Object caller) {
        return (ActivityThreadHContext) BlackReflection.create(ActivityThreadHContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadHContext.class);
    }
}
