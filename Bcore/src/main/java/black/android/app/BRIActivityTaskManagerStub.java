package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityTaskManagerStub.class */
public class BRIActivityTaskManagerStub {
    public static IActivityTaskManagerStubStatic getWithException() {
        return (IActivityTaskManagerStubStatic) BlackReflection.create(IActivityTaskManagerStubStatic.class, (Object) null, true);
    }

    public static IActivityTaskManagerStubStatic get() {
        return (IActivityTaskManagerStubStatic) BlackReflection.create(IActivityTaskManagerStubStatic.class, (Object) null, false);
    }

    public static IActivityTaskManagerStubContext getWithException(Object caller) {
        return (IActivityTaskManagerStubContext) BlackReflection.create(IActivityTaskManagerStubContext.class, caller, true);
    }

    public static IActivityTaskManagerStubContext get(Object caller) {
        return (IActivityTaskManagerStubContext) BlackReflection.create(IActivityTaskManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityTaskManagerStubContext.class);
    }
}
