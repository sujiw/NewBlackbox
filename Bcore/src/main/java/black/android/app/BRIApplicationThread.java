package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIApplicationThread.class */
public class BRIApplicationThread {
    public static IApplicationThreadStatic getWithException() {
        return (IApplicationThreadStatic) BlackReflection.create(IApplicationThreadStatic.class, (Object) null, true);
    }

    public static IApplicationThreadStatic get() {
        return (IApplicationThreadStatic) BlackReflection.create(IApplicationThreadStatic.class, (Object) null, false);
    }

    public static IApplicationThreadContext getWithException(Object caller) {
        return (IApplicationThreadContext) BlackReflection.create(IApplicationThreadContext.class, caller, true);
    }

    public static IApplicationThreadContext get(Object caller) {
        return (IApplicationThreadContext) BlackReflection.create(IApplicationThreadContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IApplicationThreadContext.class);
    }
}
