package black.libcore.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/libcore/io/BRLibcore.class */
public class BRLibcore {
    public static LibcoreStatic getWithException() {
        return (LibcoreStatic) BlackReflection.create(LibcoreStatic.class, (Object) null, true);
    }

    public static LibcoreStatic get() {
        return (LibcoreStatic) BlackReflection.create(LibcoreStatic.class, (Object) null, false);
    }

    public static LibcoreContext getWithException(Object caller) {
        return (LibcoreContext) BlackReflection.create(LibcoreContext.class, caller, true);
    }

    public static LibcoreContext get(Object caller) {
        return (LibcoreContext) BlackReflection.create(LibcoreContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LibcoreContext.class);
    }
}
