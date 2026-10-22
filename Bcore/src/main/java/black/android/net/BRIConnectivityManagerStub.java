package black.android.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/BRIConnectivityManagerStub.class */
public class BRIConnectivityManagerStub {
    public static IConnectivityManagerStubStatic getWithException() {
        return (IConnectivityManagerStubStatic) BlackReflection.create(IConnectivityManagerStubStatic.class, (Object) null, true);
    }

    public static IConnectivityManagerStubStatic get() {
        return (IConnectivityManagerStubStatic) BlackReflection.create(IConnectivityManagerStubStatic.class, (Object) null, false);
    }

    public static IConnectivityManagerStubContext getWithException(Object caller) {
        return (IConnectivityManagerStubContext) BlackReflection.create(IConnectivityManagerStubContext.class, caller, true);
    }

    public static IConnectivityManagerStubContext get(Object caller) {
        return (IConnectivityManagerStubContext) BlackReflection.create(IConnectivityManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IConnectivityManagerStubContext.class);
    }
}
