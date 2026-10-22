package black.java.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/java/io/BRUnixFileSystem.class */
public class BRUnixFileSystem {
    public static UnixFileSystemStatic getWithException() {
        return (UnixFileSystemStatic) BlackReflection.create(UnixFileSystemStatic.class, (Object) null, true);
    }

    public static UnixFileSystemStatic get() {
        return (UnixFileSystemStatic) BlackReflection.create(UnixFileSystemStatic.class, (Object) null, false);
    }

    public static UnixFileSystemContext getWithException(Object caller) {
        return (UnixFileSystemContext) BlackReflection.create(UnixFileSystemContext.class, caller, true);
    }

    public static UnixFileSystemContext get(Object caller) {
        return (UnixFileSystemContext) BlackReflection.create(UnixFileSystemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(UnixFileSystemContext.class);
    }
}
