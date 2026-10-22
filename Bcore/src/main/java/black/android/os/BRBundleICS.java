package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRBundleICS.class */
public class BRBundleICS {
    public static BundleICSStatic getWithException() {
        return (BundleICSStatic) BlackReflection.create(BundleICSStatic.class, (Object) null, true);
    }

    public static BundleICSStatic get() {
        return (BundleICSStatic) BlackReflection.create(BundleICSStatic.class, (Object) null, false);
    }

    public static BundleICSContext getWithException(Object caller) {
        return (BundleICSContext) BlackReflection.create(BundleICSContext.class, caller, true);
    }

    public static BundleICSContext get(Object caller) {
        return (BundleICSContext) BlackReflection.create(BundleICSContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BundleICSContext.class);
    }
}
