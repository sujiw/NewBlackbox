package black.com.android.internal.net;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/net/BRVpnConfig.class */
public class BRVpnConfig {
    public static VpnConfigStatic getWithException() {
        return (VpnConfigStatic) BlackReflection.create(VpnConfigStatic.class, (Object) null, true);
    }

    public static VpnConfigStatic get() {
        return (VpnConfigStatic) BlackReflection.create(VpnConfigStatic.class, (Object) null, false);
    }

    public static VpnConfigContext getWithException(Object caller) {
        return (VpnConfigContext) BlackReflection.create(VpnConfigContext.class, caller, true);
    }

    public static VpnConfigContext get(Object caller) {
        return (VpnConfigContext) BlackReflection.create(VpnConfigContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(VpnConfigContext.class);
    }
}
