package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRProcess.class */
public class BRProcess {
    public static ProcessStatic getWithException() {
        return (ProcessStatic) BlackReflection.create(ProcessStatic.class, (Object) null, true);
    }

    public static ProcessStatic get() {
        return (ProcessStatic) BlackReflection.create(ProcessStatic.class, (Object) null, false);
    }

    public static ProcessContext getWithException(Object caller) {
        return (ProcessContext) BlackReflection.create(ProcessContext.class, caller, true);
    }

    public static ProcessContext get(Object caller) {
        return (ProcessContext) BlackReflection.create(ProcessContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ProcessContext.class);
    }
}
