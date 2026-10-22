package black.android.app.admin;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/admin/BRIDevicePolicyManager.class */
public class BRIDevicePolicyManager {
    public static IDevicePolicyManagerStatic getWithException() {
        return (IDevicePolicyManagerStatic) BlackReflection.create(IDevicePolicyManagerStatic.class, (Object) null, true);
    }

    public static IDevicePolicyManagerStatic get() {
        return (IDevicePolicyManagerStatic) BlackReflection.create(IDevicePolicyManagerStatic.class, (Object) null, false);
    }

    public static IDevicePolicyManagerContext getWithException(Object caller) {
        return (IDevicePolicyManagerContext) BlackReflection.create(IDevicePolicyManagerContext.class, caller, true);
    }

    public static IDevicePolicyManagerContext get(Object caller) {
        return (IDevicePolicyManagerContext) BlackReflection.create(IDevicePolicyManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDevicePolicyManagerContext.class);
    }
}
