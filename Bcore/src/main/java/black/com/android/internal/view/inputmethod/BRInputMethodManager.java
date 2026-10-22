package black.com.android.internal.view.inputmethod;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/view/inputmethod/BRInputMethodManager.class */
public class BRInputMethodManager {
    public static InputMethodManagerStatic getWithException() {
        return (InputMethodManagerStatic) BlackReflection.create(InputMethodManagerStatic.class, (Object) null, true);
    }

    public static InputMethodManagerStatic get() {
        return (InputMethodManagerStatic) BlackReflection.create(InputMethodManagerStatic.class, (Object) null, false);
    }

    public static InputMethodManagerContext getWithException(Object caller) {
        return (InputMethodManagerContext) BlackReflection.create(InputMethodManagerContext.class, caller, true);
    }

    public static InputMethodManagerContext get(Object caller) {
        return (InputMethodManagerContext) BlackReflection.create(InputMethodManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(InputMethodManagerContext.class);
    }
}
