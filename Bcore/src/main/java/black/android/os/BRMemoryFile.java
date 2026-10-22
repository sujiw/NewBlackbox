package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRMemoryFile.class */
public class BRMemoryFile {
    public static MemoryFileStatic getWithException() {
        return (MemoryFileStatic) BlackReflection.create(MemoryFileStatic.class, (Object) null, true);
    }

    public static MemoryFileStatic get() {
        return (MemoryFileStatic) BlackReflection.create(MemoryFileStatic.class, (Object) null, false);
    }

    public static MemoryFileContext getWithException(Object caller) {
        return (MemoryFileContext) BlackReflection.create(MemoryFileContext.class, caller, true);
    }

    public static MemoryFileContext get(Object caller) {
        return (MemoryFileContext) BlackReflection.create(MemoryFileContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(MemoryFileContext.class);
    }
}
