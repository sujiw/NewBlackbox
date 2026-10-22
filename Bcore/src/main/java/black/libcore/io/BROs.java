package black.libcore.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/libcore/io/BROs.class */
public class BROs {
    public static OsStatic getWithException() {
        return (OsStatic) BlackReflection.create(OsStatic.class, (Object) null, true);
    }

    public static OsStatic get() {
        return (OsStatic) BlackReflection.create(OsStatic.class, (Object) null, false);
    }

    public static OsContext getWithException(Object caller) {
        return (OsContext) BlackReflection.create(OsContext.class, caller, true);
    }

    public static OsContext get(Object caller) {
        return (OsContext) BlackReflection.create(OsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(OsContext.class);
    }
}
