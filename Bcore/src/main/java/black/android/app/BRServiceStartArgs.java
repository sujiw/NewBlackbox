package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRServiceStartArgs.class */
public class BRServiceStartArgs {
    public static ServiceStartArgsStatic getWithException() {
        return (ServiceStartArgsStatic) BlackReflection.create(ServiceStartArgsStatic.class, (Object) null, true);
    }

    public static ServiceStartArgsStatic get() {
        return (ServiceStartArgsStatic) BlackReflection.create(ServiceStartArgsStatic.class, (Object) null, false);
    }

    public static ServiceStartArgsContext getWithException(Object caller) {
        return (ServiceStartArgsContext) BlackReflection.create(ServiceStartArgsContext.class, caller, true);
    }

    public static ServiceStartArgsContext get(Object caller) {
        return (ServiceStartArgsContext) BlackReflection.create(ServiceStartArgsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ServiceStartArgsContext.class);
    }
}
