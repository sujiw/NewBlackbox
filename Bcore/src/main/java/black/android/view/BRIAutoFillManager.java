package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIAutoFillManager.class */
public class BRIAutoFillManager {
    public static IAutoFillManagerStatic getWithException() {
        return (IAutoFillManagerStatic) BlackReflection.create(IAutoFillManagerStatic.class, (Object) null, true);
    }

    public static IAutoFillManagerStatic get() {
        return (IAutoFillManagerStatic) BlackReflection.create(IAutoFillManagerStatic.class, (Object) null, false);
    }

    public static IAutoFillManagerContext getWithException(Object caller) {
        return (IAutoFillManagerContext) BlackReflection.create(IAutoFillManagerContext.class, caller, true);
    }

    public static IAutoFillManagerContext get(Object caller) {
        return (IAutoFillManagerContext) BlackReflection.create(IAutoFillManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAutoFillManagerContext.class);
    }
}
