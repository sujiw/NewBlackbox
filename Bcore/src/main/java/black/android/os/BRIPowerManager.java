package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIPowerManager.class */
public class BRIPowerManager {
    public static IPowerManagerStatic getWithException() {
        return (IPowerManagerStatic) BlackReflection.create(IPowerManagerStatic.class, (Object) null, true);
    }

    public static IPowerManagerStatic get() {
        return (IPowerManagerStatic) BlackReflection.create(IPowerManagerStatic.class, (Object) null, false);
    }

    public static IPowerManagerContext getWithException(Object caller) {
        return (IPowerManagerContext) BlackReflection.create(IPowerManagerContext.class, caller, true);
    }

    public static IPowerManagerContext get(Object caller) {
        return (IPowerManagerContext) BlackReflection.create(IPowerManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPowerManagerContext.class);
    }
}
