package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRStrictMode.class */
public class BRStrictMode {
    public static StrictModeStatic getWithException() {
        return (StrictModeStatic) BlackReflection.create(StrictModeStatic.class, (Object) null, true);
    }

    public static StrictModeStatic get() {
        return (StrictModeStatic) BlackReflection.create(StrictModeStatic.class, (Object) null, false);
    }

    public static StrictModeContext getWithException(Object caller) {
        return (StrictModeContext) BlackReflection.create(StrictModeContext.class, caller, true);
    }

    public static StrictModeContext get(Object caller) {
        return (StrictModeContext) BlackReflection.create(StrictModeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(StrictModeContext.class);
    }
}
