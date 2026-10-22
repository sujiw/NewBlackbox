package black.android.os.health;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/health/BRSystemHealthManager.class */
public class BRSystemHealthManager {
    public static SystemHealthManagerStatic getWithException() {
        return (SystemHealthManagerStatic) BlackReflection.create(SystemHealthManagerStatic.class, (Object) null, true);
    }

    public static SystemHealthManagerStatic get() {
        return (SystemHealthManagerStatic) BlackReflection.create(SystemHealthManagerStatic.class, (Object) null, false);
    }

    public static SystemHealthManagerContext getWithException(Object caller) {
        return (SystemHealthManagerContext) BlackReflection.create(SystemHealthManagerContext.class, caller, true);
    }

    public static SystemHealthManagerContext get(Object caller) {
        return (SystemHealthManagerContext) BlackReflection.create(SystemHealthManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SystemHealthManagerContext.class);
    }
}
