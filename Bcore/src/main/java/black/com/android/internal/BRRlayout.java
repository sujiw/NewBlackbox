package black.com.android.internal;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/BRRlayout.class */
public class BRRlayout {
    public static RlayoutStatic getWithException() {
        return (RlayoutStatic) BlackReflection.create(RlayoutStatic.class, (Object) null, true);
    }

    public static RlayoutStatic get() {
        return (RlayoutStatic) BlackReflection.create(RlayoutStatic.class, (Object) null, false);
    }

    public static RlayoutContext getWithException(Object caller) {
        return (RlayoutContext) BlackReflection.create(RlayoutContext.class, caller, true);
    }

    public static RlayoutContext get(Object caller) {
        return (RlayoutContext) BlackReflection.create(RlayoutContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RlayoutContext.class);
    }
}
