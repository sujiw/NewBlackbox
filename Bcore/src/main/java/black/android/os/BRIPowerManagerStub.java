package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIPowerManagerStub.class */
public class BRIPowerManagerStub {
    public static IPowerManagerStubStatic getWithException() {
        return (IPowerManagerStubStatic) BlackReflection.create(IPowerManagerStubStatic.class, (Object) null, true);
    }

    public static IPowerManagerStubStatic get() {
        return (IPowerManagerStubStatic) BlackReflection.create(IPowerManagerStubStatic.class, (Object) null, false);
    }

    public static IPowerManagerStubContext getWithException(Object caller) {
        return (IPowerManagerStubContext) BlackReflection.create(IPowerManagerStubContext.class, caller, true);
    }

    public static IPowerManagerStubContext get(Object caller) {
        return (IPowerManagerStubContext) BlackReflection.create(IPowerManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPowerManagerStubContext.class);
    }
}
