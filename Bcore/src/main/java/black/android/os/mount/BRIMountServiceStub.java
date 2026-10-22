package black.android.os.mount;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/mount/BRIMountServiceStub.class */
public class BRIMountServiceStub {
    public static IMountServiceStubStatic getWithException() {
        return (IMountServiceStubStatic) BlackReflection.create(IMountServiceStubStatic.class, (Object) null, true);
    }

    public static IMountServiceStubStatic get() {
        return (IMountServiceStubStatic) BlackReflection.create(IMountServiceStubStatic.class, (Object) null, false);
    }

    public static IMountServiceStubContext getWithException(Object caller) {
        return (IMountServiceStubContext) BlackReflection.create(IMountServiceStubContext.class, caller, true);
    }

    public static IMountServiceStubContext get(Object caller) {
        return (IMountServiceStubContext) BlackReflection.create(IMountServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMountServiceStubContext.class);
    }
}
