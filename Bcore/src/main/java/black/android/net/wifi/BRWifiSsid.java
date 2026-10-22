package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRWifiSsid.class */
public class BRWifiSsid {
    public static WifiSsidStatic getWithException() {
        return (WifiSsidStatic) BlackReflection.create(WifiSsidStatic.class, (Object) null, true);
    }

    public static WifiSsidStatic get() {
        return (WifiSsidStatic) BlackReflection.create(WifiSsidStatic.class, (Object) null, false);
    }

    public static WifiSsidContext getWithException(Object caller) {
        return (WifiSsidContext) BlackReflection.create(WifiSsidContext.class, caller, true);
    }

    public static WifiSsidContext get(Object caller) {
        return (WifiSsidContext) BlackReflection.create(WifiSsidContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WifiSsidContext.class);
    }
}
