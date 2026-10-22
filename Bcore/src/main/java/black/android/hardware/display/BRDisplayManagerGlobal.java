package black.android.hardware.display;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/display/BRDisplayManagerGlobal.class */
public class BRDisplayManagerGlobal {
    public static DisplayManagerGlobalStatic getWithException() {
        return (DisplayManagerGlobalStatic) BlackReflection.create(DisplayManagerGlobalStatic.class, (Object) null, true);
    }

    public static DisplayManagerGlobalStatic get() {
        return (DisplayManagerGlobalStatic) BlackReflection.create(DisplayManagerGlobalStatic.class, (Object) null, false);
    }

    public static DisplayManagerGlobalContext getWithException(Object caller) {
        return (DisplayManagerGlobalContext) BlackReflection.create(DisplayManagerGlobalContext.class, caller, true);
    }

    public static DisplayManagerGlobalContext get(Object caller) {
        return (DisplayManagerGlobalContext) BlackReflection.create(DisplayManagerGlobalContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(DisplayManagerGlobalContext.class);
    }
}
