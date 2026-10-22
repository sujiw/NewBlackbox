package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRWindowManagerGlobal.class */
public class BRWindowManagerGlobal {
    public static WindowManagerGlobalStatic getWithException() {
        return (WindowManagerGlobalStatic) BlackReflection.create(WindowManagerGlobalStatic.class, (Object) null, true);
    }

    public static WindowManagerGlobalStatic get() {
        return (WindowManagerGlobalStatic) BlackReflection.create(WindowManagerGlobalStatic.class, (Object) null, false);
    }

    public static WindowManagerGlobalContext getWithException(Object caller) {
        return (WindowManagerGlobalContext) BlackReflection.create(WindowManagerGlobalContext.class, caller, true);
    }

    public static WindowManagerGlobalContext get(Object caller) {
        return (WindowManagerGlobalContext) BlackReflection.create(WindowManagerGlobalContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WindowManagerGlobalContext.class);
    }
}
