package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRBaseBundle.class */
public class BRBaseBundle {
    public static BaseBundleStatic getWithException() {
        return (BaseBundleStatic) BlackReflection.create(BaseBundleStatic.class, (Object) null, true);
    }

    public static BaseBundleStatic get() {
        return (BaseBundleStatic) BlackReflection.create(BaseBundleStatic.class, (Object) null, false);
    }

    public static BaseBundleContext getWithException(Object caller) {
        return (BaseBundleContext) BlackReflection.create(BaseBundleContext.class, caller, true);
    }

    public static BaseBundleContext get(Object caller) {
        return (BaseBundleContext) BlackReflection.create(BaseBundleContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BaseBundleContext.class);
    }
}
