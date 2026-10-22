package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRBundle.class */
public class BRBundle {
    public static BundleStatic getWithException() {
        return (BundleStatic) BlackReflection.create(BundleStatic.class, (Object) null, true);
    }

    public static BundleStatic get() {
        return (BundleStatic) BlackReflection.create(BundleStatic.class, (Object) null, false);
    }

    public static BundleContext getWithException(Object caller) {
        return (BundleContext) BlackReflection.create(BundleContext.class, caller, true);
    }

    public static BundleContext get(Object caller) {
        return (BundleContext) BlackReflection.create(BundleContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BundleContext.class);
    }
}
