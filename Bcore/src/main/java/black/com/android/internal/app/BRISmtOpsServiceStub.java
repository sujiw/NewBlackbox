package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRISmtOpsServiceStub.class */
public class BRISmtOpsServiceStub {
    public static ISmtOpsServiceStubStatic getWithException() {
        return (ISmtOpsServiceStubStatic) BlackReflection.create(ISmtOpsServiceStubStatic.class, (Object) null, true);
    }

    public static ISmtOpsServiceStubStatic get() {
        return (ISmtOpsServiceStubStatic) BlackReflection.create(ISmtOpsServiceStubStatic.class, (Object) null, false);
    }

    public static ISmtOpsServiceStubContext getWithException(Object caller) {
        return (ISmtOpsServiceStubContext) BlackReflection.create(ISmtOpsServiceStubContext.class, caller, true);
    }

    public static ISmtOpsServiceStubContext get(Object caller) {
        return (ISmtOpsServiceStubContext) BlackReflection.create(ISmtOpsServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISmtOpsServiceStubContext.class);
    }
}
