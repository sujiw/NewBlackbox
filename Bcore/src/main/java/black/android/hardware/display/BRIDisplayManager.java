package black.android.hardware.display;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/display/BRIDisplayManager.class */
public class BRIDisplayManager {
    public static IDisplayManagerStatic getWithException() {
        return (IDisplayManagerStatic) BlackReflection.create(IDisplayManagerStatic.class, (Object) null, true);
    }

    public static IDisplayManagerStatic get() {
        return (IDisplayManagerStatic) BlackReflection.create(IDisplayManagerStatic.class, (Object) null, false);
    }

    public static IDisplayManagerContext getWithException(Object caller) {
        return (IDisplayManagerContext) BlackReflection.create(IDisplayManagerContext.class, caller, true);
    }

    public static IDisplayManagerContext get(Object caller) {
        return (IDisplayManagerContext) BlackReflection.create(IDisplayManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDisplayManagerContext.class);
    }
}
