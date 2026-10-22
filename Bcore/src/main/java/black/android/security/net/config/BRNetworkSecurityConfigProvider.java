package black.android.security.net.config;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/security/net/config/BRNetworkSecurityConfigProvider.class */
public class BRNetworkSecurityConfigProvider {
    public static NetworkSecurityConfigProviderStatic getWithException() {
        return (NetworkSecurityConfigProviderStatic) BlackReflection.create(NetworkSecurityConfigProviderStatic.class, (Object) null, true);
    }

    public static NetworkSecurityConfigProviderStatic get() {
        return (NetworkSecurityConfigProviderStatic) BlackReflection.create(NetworkSecurityConfigProviderStatic.class, (Object) null, false);
    }

    public static NetworkSecurityConfigProviderContext getWithException(Object caller) {
        return (NetworkSecurityConfigProviderContext) BlackReflection.create(NetworkSecurityConfigProviderContext.class, caller, true);
    }

    public static NetworkSecurityConfigProviderContext get(Object caller) {
        return (NetworkSecurityConfigProviderContext) BlackReflection.create(NetworkSecurityConfigProviderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NetworkSecurityConfigProviderContext.class);
    }
}
