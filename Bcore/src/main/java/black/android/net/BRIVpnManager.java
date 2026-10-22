package black.android.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/BRIVpnManager.class */
public class BRIVpnManager {
    public static IVpnManagerStatic getWithException() {
        return (IVpnManagerStatic) BlackReflection.create(IVpnManagerStatic.class, (Object) null, true);
    }

    public static IVpnManagerStatic get() {
        return (IVpnManagerStatic) BlackReflection.create(IVpnManagerStatic.class, (Object) null, false);
    }

    public static IVpnManagerContext getWithException(Object caller) {
        return (IVpnManagerContext) BlackReflection.create(IVpnManagerContext.class, caller, true);
    }

    public static IVpnManagerContext get(Object caller) {
        return (IVpnManagerContext) BlackReflection.create(IVpnManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVpnManagerContext.class);
    }
}
