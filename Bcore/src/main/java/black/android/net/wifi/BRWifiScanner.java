package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRWifiScanner.class */
public class BRWifiScanner {
    public static WifiScannerStatic getWithException() {
        return (WifiScannerStatic) BlackReflection.create(WifiScannerStatic.class, (Object) null, true);
    }

    public static WifiScannerStatic get() {
        return (WifiScannerStatic) BlackReflection.create(WifiScannerStatic.class, (Object) null, false);
    }

    public static WifiScannerContext getWithException(Object caller) {
        return (WifiScannerContext) BlackReflection.create(WifiScannerContext.class, caller, true);
    }

    public static WifiScannerContext get(Object caller) {
        return (WifiScannerContext) BlackReflection.create(WifiScannerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WifiScannerContext.class);
    }
}
