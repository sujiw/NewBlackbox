package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRTelephonyManager.class */
public class BRTelephonyManager {
    public static TelephonyManagerStatic getWithException() {
        return (TelephonyManagerStatic) BlackReflection.create(TelephonyManagerStatic.class, (Object) null, true);
    }

    public static TelephonyManagerStatic get() {
        return (TelephonyManagerStatic) BlackReflection.create(TelephonyManagerStatic.class, (Object) null, false);
    }

    public static TelephonyManagerContext getWithException(Object caller) {
        return (TelephonyManagerContext) BlackReflection.create(TelephonyManagerContext.class, caller, true);
    }

    public static TelephonyManagerContext get(Object caller) {
        return (TelephonyManagerContext) BlackReflection.create(TelephonyManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(TelephonyManagerContext.class);
    }
}
