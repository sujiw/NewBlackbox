package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRIWifiManager.class */
public class BRIWifiManager {
    public static IWifiManagerStatic getWithException() {
        return (IWifiManagerStatic) BlackReflection.create(IWifiManagerStatic.class, (Object) null, true);
    }

    public static IWifiManagerStatic get() {
        return (IWifiManagerStatic) BlackReflection.create(IWifiManagerStatic.class, (Object) null, false);
    }

    public static IWifiManagerContext getWithException(Object caller) {
        return (IWifiManagerContext) BlackReflection.create(IWifiManagerContext.class, caller, true);
    }

    public static IWifiManagerContext get(Object caller) {
        return (IWifiManagerContext) BlackReflection.create(IWifiManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWifiManagerContext.class);
    }
}
