package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRIAppOpsServiceStub.class */
public class BRIAppOpsServiceStub {
    public static IAppOpsServiceStubStatic getWithException() {
        return (IAppOpsServiceStubStatic) BlackReflection.create(IAppOpsServiceStubStatic.class, (Object) null, true);
    }

    public static IAppOpsServiceStubStatic get() {
        return (IAppOpsServiceStubStatic) BlackReflection.create(IAppOpsServiceStubStatic.class, (Object) null, false);
    }

    public static IAppOpsServiceStubContext getWithException(Object caller) {
        return (IAppOpsServiceStubContext) BlackReflection.create(IAppOpsServiceStubContext.class, caller, true);
    }

    public static IAppOpsServiceStubContext get(Object caller) {
        return (IAppOpsServiceStubContext) BlackReflection.create(IAppOpsServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAppOpsServiceStubContext.class);
    }
}
