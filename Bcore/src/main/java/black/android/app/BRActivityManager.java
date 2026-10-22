package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityManager.class */
public class BRActivityManager {
    public static ActivityManagerStatic getWithException() {
        return (ActivityManagerStatic) BlackReflection.create(ActivityManagerStatic.class, (Object) null, true);
    }

    public static ActivityManagerStatic get() {
        return (ActivityManagerStatic) BlackReflection.create(ActivityManagerStatic.class, (Object) null, false);
    }

    public static ActivityManagerContext getWithException(Object caller) {
        return (ActivityManagerContext) BlackReflection.create(ActivityManagerContext.class, caller, true);
    }

    public static ActivityManagerContext get(Object caller) {
        return (ActivityManagerContext) BlackReflection.create(ActivityManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityManagerContext.class);
    }
}
