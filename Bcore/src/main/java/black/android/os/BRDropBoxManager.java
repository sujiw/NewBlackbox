package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRDropBoxManager.class */
public class BRDropBoxManager {
    public static DropBoxManagerStatic getWithException() {
        return (DropBoxManagerStatic) BlackReflection.create(DropBoxManagerStatic.class, (Object) null, true);
    }

    public static DropBoxManagerStatic get() {
        return (DropBoxManagerStatic) BlackReflection.create(DropBoxManagerStatic.class, (Object) null, false);
    }

    public static DropBoxManagerContext getWithException(Object caller) {
        return (DropBoxManagerContext) BlackReflection.create(DropBoxManagerContext.class, caller, true);
    }

    public static DropBoxManagerContext get(Object caller) {
        return (DropBoxManagerContext) BlackReflection.create(DropBoxManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(DropBoxManagerContext.class);
    }
}
