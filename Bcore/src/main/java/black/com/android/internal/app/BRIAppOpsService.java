package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRIAppOpsService.class */
public class BRIAppOpsService {
    public static IAppOpsServiceStatic getWithException() {
        return (IAppOpsServiceStatic) BlackReflection.create(IAppOpsServiceStatic.class, (Object) null, true);
    }

    public static IAppOpsServiceStatic get() {
        return (IAppOpsServiceStatic) BlackReflection.create(IAppOpsServiceStatic.class, (Object) null, false);
    }

    public static IAppOpsServiceContext getWithException(Object caller) {
        return (IAppOpsServiceContext) BlackReflection.create(IAppOpsServiceContext.class, caller, true);
    }

    public static IAppOpsServiceContext get(Object caller) {
        return (IAppOpsServiceContext) BlackReflection.create(IAppOpsServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAppOpsServiceContext.class);
    }
}
