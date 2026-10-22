package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRService.class */
public class BRService {
    public static ServiceStatic getWithException() {
        return (ServiceStatic) BlackReflection.create(ServiceStatic.class, (Object) null, true);
    }

    public static ServiceStatic get() {
        return (ServiceStatic) BlackReflection.create(ServiceStatic.class, (Object) null, false);
    }

    public static ServiceContext getWithException(Object caller) {
        return (ServiceContext) BlackReflection.create(ServiceContext.class, caller, true);
    }

    public static ServiceContext get(Object caller) {
        return (ServiceContext) BlackReflection.create(ServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ServiceContext.class);
    }
}
