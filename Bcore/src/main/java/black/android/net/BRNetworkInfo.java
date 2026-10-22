package black.android.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/net/BRNetworkInfo.class */
public class BRNetworkInfo {
    public static NetworkInfoStatic getWithException() {
        return (NetworkInfoStatic) BlackReflection.create(NetworkInfoStatic.class, (Object) null, true);
    }

    public static NetworkInfoStatic get() {
        return (NetworkInfoStatic) BlackReflection.create(NetworkInfoStatic.class, (Object) null, false);
    }

    public static NetworkInfoContext getWithException(Object caller) {
        return (NetworkInfoContext) BlackReflection.create(NetworkInfoContext.class, caller, true);
    }

    public static NetworkInfoContext get(Object caller) {
        return (NetworkInfoContext) BlackReflection.create(NetworkInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NetworkInfoContext.class);
    }
}
