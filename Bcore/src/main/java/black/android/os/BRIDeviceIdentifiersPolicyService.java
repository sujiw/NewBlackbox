package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIDeviceIdentifiersPolicyService.class */
public class BRIDeviceIdentifiersPolicyService {
    public static IDeviceIdentifiersPolicyServiceStatic getWithException() {
        return (IDeviceIdentifiersPolicyServiceStatic) BlackReflection.create(IDeviceIdentifiersPolicyServiceStatic.class, (Object) null, true);
    }

    public static IDeviceIdentifiersPolicyServiceStatic get() {
        return (IDeviceIdentifiersPolicyServiceStatic) BlackReflection.create(IDeviceIdentifiersPolicyServiceStatic.class, (Object) null, false);
    }

    public static IDeviceIdentifiersPolicyServiceContext getWithException(Object caller) {
        return (IDeviceIdentifiersPolicyServiceContext) BlackReflection.create(IDeviceIdentifiersPolicyServiceContext.class, caller, true);
    }

    public static IDeviceIdentifiersPolicyServiceContext get(Object caller) {
        return (IDeviceIdentifiersPolicyServiceContext) BlackReflection.create(IDeviceIdentifiersPolicyServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDeviceIdentifiersPolicyServiceContext.class);
    }
}
