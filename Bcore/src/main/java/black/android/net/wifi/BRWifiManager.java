package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRWifiManager.class */
public class BRWifiManager {
    public static WifiManagerStatic getWithException() {
        return (WifiManagerStatic) BlackReflection.create(WifiManagerStatic.class, (Object) null, true);
    }

    public static WifiManagerStatic get() {
        return (WifiManagerStatic) BlackReflection.create(WifiManagerStatic.class, (Object) null, false);
    }

    public static WifiManagerContext getWithException(Object caller) {
        return (WifiManagerContext) BlackReflection.create(WifiManagerContext.class, caller, true);
    }

    public static WifiManagerContext get(Object caller) {
        return (WifiManagerContext) BlackReflection.create(WifiManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WifiManagerContext.class);
    }
}
