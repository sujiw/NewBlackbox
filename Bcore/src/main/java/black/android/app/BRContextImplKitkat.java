package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRContextImplKitkat.class */
public class BRContextImplKitkat {
    public static ContextImplKitkatStatic getWithException() {
        return (ContextImplKitkatStatic) BlackReflection.create(ContextImplKitkatStatic.class, (Object) null, true);
    }

    public static ContextImplKitkatStatic get() {
        return (ContextImplKitkatStatic) BlackReflection.create(ContextImplKitkatStatic.class, (Object) null, false);
    }

    public static ContextImplKitkatContext getWithException(Object caller) {
        return (ContextImplKitkatContext) BlackReflection.create(ContextImplKitkatContext.class, caller, true);
    }

    public static ContextImplKitkatContext get(Object caller) {
        return (ContextImplKitkatContext) BlackReflection.create(ContextImplKitkatContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContextImplKitkatContext.class);
    }
}
