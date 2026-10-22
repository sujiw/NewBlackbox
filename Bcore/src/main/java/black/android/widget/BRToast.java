package black.android.widget;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/widget/BRToast.class */
public class BRToast {
    public static ToastStatic getWithException() {
        return (ToastStatic) BlackReflection.create(ToastStatic.class, (Object) null, true);
    }

    public static ToastStatic get() {
        return (ToastStatic) BlackReflection.create(ToastStatic.class, (Object) null, false);
    }

    public static ToastContext getWithException(Object caller) {
        return (ToastContext) BlackReflection.create(ToastContext.class, caller, true);
    }

    public static ToastContext get(Object caller) {
        return (ToastContext) BlackReflection.create(ToastContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ToastContext.class);
    }
}
