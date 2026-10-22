package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThread.class */
public class BRActivityThread {
    public static ActivityThreadStatic getWithException() {
        return (ActivityThreadStatic) BlackReflection.create(ActivityThreadStatic.class, (Object) null, true);
    }

    public static ActivityThreadStatic get() {
        return (ActivityThreadStatic) BlackReflection.create(ActivityThreadStatic.class, (Object) null, false);
    }

    public static ActivityThreadContext getWithException(Object caller) {
        return (ActivityThreadContext) BlackReflection.create(ActivityThreadContext.class, caller, true);
    }

    public static ActivityThreadContext get(Object caller) {
        return (ActivityThreadContext) BlackReflection.create(ActivityThreadContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadContext.class);
    }
}
