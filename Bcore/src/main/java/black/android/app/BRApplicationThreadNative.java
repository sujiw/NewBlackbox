package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRApplicationThreadNative.class */
public class BRApplicationThreadNative {
    public static ApplicationThreadNativeStatic getWithException() {
        return (ApplicationThreadNativeStatic) BlackReflection.create(ApplicationThreadNativeStatic.class, (Object) null, true);
    }

    public static ApplicationThreadNativeStatic get() {
        return (ApplicationThreadNativeStatic) BlackReflection.create(ApplicationThreadNativeStatic.class, (Object) null, false);
    }

    public static ApplicationThreadNativeContext getWithException(Object caller) {
        return (ApplicationThreadNativeContext) BlackReflection.create(ApplicationThreadNativeContext.class, caller, true);
    }

    public static ApplicationThreadNativeContext get(Object caller) {
        return (ApplicationThreadNativeContext) BlackReflection.create(ApplicationThreadNativeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ApplicationThreadNativeContext.class);
    }
}
