package black.android.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/BRIVpnManagerStub.class */
public class BRIVpnManagerStub {
    public static IVpnManagerStubStatic getWithException() {
        return (IVpnManagerStubStatic) BlackReflection.create(IVpnManagerStubStatic.class, (Object) null, true);
    }

    public static IVpnManagerStubStatic get() {
        return (IVpnManagerStubStatic) BlackReflection.create(IVpnManagerStubStatic.class, (Object) null, false);
    }

    public static IVpnManagerStubContext getWithException(Object caller) {
        return (IVpnManagerStubContext) BlackReflection.create(IVpnManagerStubContext.class, caller, true);
    }

    public static IVpnManagerStubContext get(Object caller) {
        return (IVpnManagerStubContext) BlackReflection.create(IVpnManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVpnManagerStubContext.class);
    }
}
