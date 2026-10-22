package black.android.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/BRIConnectivityManager.class */
public class BRIConnectivityManager {
    public static IConnectivityManagerStatic getWithException() {
        return (IConnectivityManagerStatic) BlackReflection.create(IConnectivityManagerStatic.class, (Object) null, true);
    }

    public static IConnectivityManagerStatic get() {
        return (IConnectivityManagerStatic) BlackReflection.create(IConnectivityManagerStatic.class, (Object) null, false);
    }

    public static IConnectivityManagerContext getWithException(Object caller) {
        return (IConnectivityManagerContext) BlackReflection.create(IConnectivityManagerContext.class, caller, true);
    }

    public static IConnectivityManagerContext get(Object caller) {
        return (IConnectivityManagerContext) BlackReflection.create(IConnectivityManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IConnectivityManagerContext.class);
    }
}
