package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRITelephonyRegistryStub.class */
public class BRITelephonyRegistryStub {
    public static ITelephonyRegistryStubStatic getWithException() {
        return (ITelephonyRegistryStubStatic) BlackReflection.create(ITelephonyRegistryStubStatic.class, (Object) null, true);
    }

    public static ITelephonyRegistryStubStatic get() {
        return (ITelephonyRegistryStubStatic) BlackReflection.create(ITelephonyRegistryStubStatic.class, (Object) null, false);
    }

    public static ITelephonyRegistryStubContext getWithException(Object caller) {
        return (ITelephonyRegistryStubContext) BlackReflection.create(ITelephonyRegistryStubContext.class, caller, true);
    }

    public static ITelephonyRegistryStubContext get(Object caller) {
        return (ITelephonyRegistryStubContext) BlackReflection.create(ITelephonyRegistryStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelephonyRegistryStubContext.class);
    }
}
