package black.android.net.wifi;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/BRIWifiManagerStub.class */
public class BRIWifiManagerStub {
    public static IWifiManagerStubStatic getWithException() {
        return (IWifiManagerStubStatic) BlackReflection.create(IWifiManagerStubStatic.class, (Object) null, true);
    }

    public static IWifiManagerStubStatic get() {
        return (IWifiManagerStubStatic) BlackReflection.create(IWifiManagerStubStatic.class, (Object) null, false);
    }

    public static IWifiManagerStubContext getWithException(Object caller) {
        return (IWifiManagerStubContext) BlackReflection.create(IWifiManagerStubContext.class, caller, true);
    }

    public static IWifiManagerStubContext get(Object caller) {
        return (IWifiManagerStubContext) BlackReflection.create(IWifiManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWifiManagerStubContext.class);
    }
}
