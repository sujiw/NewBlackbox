package black.android.app.admin;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/admin/BRIDevicePolicyManagerStub.class */
public class BRIDevicePolicyManagerStub {
    public static IDevicePolicyManagerStubStatic getWithException() {
        return (IDevicePolicyManagerStubStatic) BlackReflection.create(IDevicePolicyManagerStubStatic.class, (Object) null, true);
    }

    public static IDevicePolicyManagerStubStatic get() {
        return (IDevicePolicyManagerStubStatic) BlackReflection.create(IDevicePolicyManagerStubStatic.class, (Object) null, false);
    }

    public static IDevicePolicyManagerStubContext getWithException(Object caller) {
        return (IDevicePolicyManagerStubContext) BlackReflection.create(IDevicePolicyManagerStubContext.class, caller, true);
    }

    public static IDevicePolicyManagerStubContext get(Object caller) {
        return (IDevicePolicyManagerStubContext) BlackReflection.create(IDevicePolicyManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDevicePolicyManagerStubContext.class);
    }
}
