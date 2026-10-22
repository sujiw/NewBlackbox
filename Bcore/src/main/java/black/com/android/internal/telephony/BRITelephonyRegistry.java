package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRITelephonyRegistry.class */
public class BRITelephonyRegistry {
    public static ITelephonyRegistryStatic getWithException() {
        return (ITelephonyRegistryStatic) BlackReflection.create(ITelephonyRegistryStatic.class, (Object) null, true);
    }

    public static ITelephonyRegistryStatic get() {
        return (ITelephonyRegistryStatic) BlackReflection.create(ITelephonyRegistryStatic.class, (Object) null, false);
    }

    public static ITelephonyRegistryContext getWithException(Object caller) {
        return (ITelephonyRegistryContext) BlackReflection.create(ITelephonyRegistryContext.class, caller, true);
    }

    public static ITelephonyRegistryContext get(Object caller) {
        return (ITelephonyRegistryContext) BlackReflection.create(ITelephonyRegistryContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelephonyRegistryContext.class);
    }
}
