package black.android.location.provider;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/provider/BRProviderProperties.class */
public class BRProviderProperties {
    public static ProviderPropertiesStatic getWithException() {
        return (ProviderPropertiesStatic) BlackReflection.create(ProviderPropertiesStatic.class, (Object) null, true);
    }

    public static ProviderPropertiesStatic get() {
        return (ProviderPropertiesStatic) BlackReflection.create(ProviderPropertiesStatic.class, (Object) null, false);
    }

    public static ProviderPropertiesContext getWithException(Object caller) {
        return (ProviderPropertiesContext) BlackReflection.create(ProviderPropertiesContext.class, caller, true);
    }

    public static ProviderPropertiesContext get(Object caller) {
        return (ProviderPropertiesContext) BlackReflection.create(ProviderPropertiesContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ProviderPropertiesContext.class);
    }
}
