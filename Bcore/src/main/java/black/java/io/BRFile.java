package black.java.io;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/java/io/BRFile.class */
public class BRFile {
    public static FileStatic getWithException() {
        return (FileStatic) BlackReflection.create(FileStatic.class, (Object) null, true);
    }

    public static FileStatic get() {
        return (FileStatic) BlackReflection.create(FileStatic.class, (Object) null, false);
    }

    public static FileContext getWithException(Object caller) {
        return (FileContext) BlackReflection.create(FileContext.class, caller, true);
    }

    public static FileContext get(Object caller) {
        return (FileContext) BlackReflection.create(FileContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(FileContext.class);
    }
}
