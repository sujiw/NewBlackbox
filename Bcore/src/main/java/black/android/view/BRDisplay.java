package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRDisplay.class */
public class BRDisplay {
    public static DisplayStatic getWithException() {
        return (DisplayStatic) BlackReflection.create(DisplayStatic.class, (Object) null, true);
    }

    public static DisplayStatic get() {
        return (DisplayStatic) BlackReflection.create(DisplayStatic.class, (Object) null, false);
    }

    public static DisplayContext getWithException(Object caller) {
        return (DisplayContext) BlackReflection.create(DisplayContext.class, caller, true);
    }

    public static DisplayContext get(Object caller) {
        return (DisplayContext) BlackReflection.create(DisplayContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(DisplayContext.class);
    }
}
