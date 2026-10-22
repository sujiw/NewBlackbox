package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRISmtOpsService.class */
public class BRISmtOpsService {
    public static ISmtOpsServiceStatic getWithException() {
        return (ISmtOpsServiceStatic) BlackReflection.create(ISmtOpsServiceStatic.class, (Object) null, true);
    }

    public static ISmtOpsServiceStatic get() {
        return (ISmtOpsServiceStatic) BlackReflection.create(ISmtOpsServiceStatic.class, (Object) null, false);
    }

    public static ISmtOpsServiceContext getWithException(Object caller) {
        return (ISmtOpsServiceContext) BlackReflection.create(ISmtOpsServiceContext.class, caller, true);
    }

    public static ISmtOpsServiceContext get(Object caller) {
        return (ISmtOpsServiceContext) BlackReflection.create(ISmtOpsServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISmtOpsServiceContext.class);
    }
}
