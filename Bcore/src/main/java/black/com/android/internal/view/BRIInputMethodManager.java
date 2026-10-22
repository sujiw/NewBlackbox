package black.com.android.internal.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/view/BRIInputMethodManager.class */
public class BRIInputMethodManager {
    public static IInputMethodManagerStatic getWithException() {
        return (IInputMethodManagerStatic) BlackReflection.create(IInputMethodManagerStatic.class, (Object) null, true);
    }

    public static IInputMethodManagerStatic get() {
        return (IInputMethodManagerStatic) BlackReflection.create(IInputMethodManagerStatic.class, (Object) null, false);
    }

    public static IInputMethodManagerContext getWithException(Object caller) {
        return (IInputMethodManagerContext) BlackReflection.create(IInputMethodManagerContext.class, caller, true);
    }

    public static IInputMethodManagerContext get(Object caller) {
        return (IInputMethodManagerContext) BlackReflection.create(IInputMethodManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IInputMethodManagerContext.class);
    }
}
