package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIWindowManager.class */
public class BRIWindowManager {
    public static IWindowManagerStatic getWithException() {
        return (IWindowManagerStatic) BlackReflection.create(IWindowManagerStatic.class, (Object) null, true);
    }

    public static IWindowManagerStatic get() {
        return (IWindowManagerStatic) BlackReflection.create(IWindowManagerStatic.class, (Object) null, false);
    }

    public static IWindowManagerContext getWithException(Object caller) {
        return (IWindowManagerContext) BlackReflection.create(IWindowManagerContext.class, caller, true);
    }

    public static IWindowManagerContext get(Object caller) {
        return (IWindowManagerContext) BlackReflection.create(IWindowManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWindowManagerContext.class);
    }
}
