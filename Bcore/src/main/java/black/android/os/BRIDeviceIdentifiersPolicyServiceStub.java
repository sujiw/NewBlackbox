package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIDeviceIdentifiersPolicyServiceStub.class */
public class BRIDeviceIdentifiersPolicyServiceStub {
    public static IDeviceIdentifiersPolicyServiceStubStatic getWithException() {
        return (IDeviceIdentifiersPolicyServiceStubStatic) BlackReflection.create(IDeviceIdentifiersPolicyServiceStubStatic.class, (Object) null, true);
    }

    public static IDeviceIdentifiersPolicyServiceStubStatic get() {
        return (IDeviceIdentifiersPolicyServiceStubStatic) BlackReflection.create(IDeviceIdentifiersPolicyServiceStubStatic.class, (Object) null, false);
    }

    public static IDeviceIdentifiersPolicyServiceStubContext getWithException(Object caller) {
        return (IDeviceIdentifiersPolicyServiceStubContext) BlackReflection.create(IDeviceIdentifiersPolicyServiceStubContext.class, caller, true);
    }

    public static IDeviceIdentifiersPolicyServiceStubContext get(Object caller) {
        return (IDeviceIdentifiersPolicyServiceStubContext) BlackReflection.create(IDeviceIdentifiersPolicyServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDeviceIdentifiersPolicyServiceStubContext.class);
    }
}
