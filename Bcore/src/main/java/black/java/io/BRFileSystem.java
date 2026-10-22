package black.java.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/java/io/BRFileSystem.class */
public class BRFileSystem {
    public static FileSystemStatic getWithException() {
        return (FileSystemStatic) BlackReflection.create(FileSystemStatic.class, (Object) null, true);
    }

    public static FileSystemStatic get() {
        return (FileSystemStatic) BlackReflection.create(FileSystemStatic.class, (Object) null, false);
    }

    public static FileSystemContext getWithException(Object caller) {
        return (FileSystemContext) BlackReflection.create(FileSystemContext.class, caller, true);
    }

    public static FileSystemContext get(Object caller) {
        return (FileSystemContext) BlackReflection.create(FileSystemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(FileSystemContext.class);
    }
}
