package black.libcore.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/libcore/io/BRForwardingOs.class */
public class BRForwardingOs {
    public static ForwardingOsStatic getWithException() {
        return (ForwardingOsStatic) BlackReflection.create(ForwardingOsStatic.class, (Object) null, true);
    }

    public static ForwardingOsStatic get() {
        return (ForwardingOsStatic) BlackReflection.create(ForwardingOsStatic.class, (Object) null, false);
    }

    public static ForwardingOsContext getWithException(Object caller) {
        return (ForwardingOsContext) BlackReflection.create(ForwardingOsContext.class, caller, true);
    }

    public static ForwardingOsContext get(Object caller) {
        return (ForwardingOsContext) BlackReflection.create(ForwardingOsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ForwardingOsContext.class);
    }
}
