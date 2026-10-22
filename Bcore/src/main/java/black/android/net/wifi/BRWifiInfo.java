package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRWifiInfo.class */
public class BRWifiInfo {
    public static WifiInfoStatic getWithException() {
        return (WifiInfoStatic) BlackReflection.create(WifiInfoStatic.class, (Object) null, true);
    }

    public static WifiInfoStatic get() {
        return (WifiInfoStatic) BlackReflection.create(WifiInfoStatic.class, (Object) null, false);
    }

    public static WifiInfoContext getWithException(Object caller) {
        return (WifiInfoContext) BlackReflection.create(WifiInfoContext.class, caller, true);
    }

    public static WifiInfoContext get(Object caller) {
        return (WifiInfoContext) BlackReflection.create(WifiInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WifiInfoContext.class);
    }
}
