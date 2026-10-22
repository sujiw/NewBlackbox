package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRServiceManager.class */
public class BRServiceManager {
    public static ServiceManagerStatic getWithException() {
        return (ServiceManagerStatic) BlackReflection.create(ServiceManagerStatic.class, (Object) null, true);
    }

    public static ServiceManagerStatic get() {
        return (ServiceManagerStatic) BlackReflection.create(ServiceManagerStatic.class, (Object) null, false);
    }

    public static ServiceManagerContext getWithException(Object caller) {
        return (ServiceManagerContext) BlackReflection.create(ServiceManagerContext.class, caller, true);
    }

    public static ServiceManagerContext get(Object caller) {
        return (ServiceManagerContext) BlackReflection.create(ServiceManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ServiceManagerContext.class);
    }
}
