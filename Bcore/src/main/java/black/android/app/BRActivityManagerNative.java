package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityManagerNative.class */
public class BRActivityManagerNative {
    public static ActivityManagerNativeStatic getWithException() {
        return (ActivityManagerNativeStatic) BlackReflection.create(ActivityManagerNativeStatic.class, (Object) null, true);
    }

    public static ActivityManagerNativeStatic get() {
        return (ActivityManagerNativeStatic) BlackReflection.create(ActivityManagerNativeStatic.class, (Object) null, false);
    }

    public static ActivityManagerNativeContext getWithException(Object caller) {
        return (ActivityManagerNativeContext) BlackReflection.create(ActivityManagerNativeContext.class, caller, true);
    }

    public static ActivityManagerNativeContext get(Object caller) {
        return (ActivityManagerNativeContext) BlackReflection.create(ActivityManagerNativeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityManagerNativeContext.class);
    }
}
