package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRITelephony.class */
public class BRITelephony {
    public static ITelephonyStatic getWithException() {
        return (ITelephonyStatic) BlackReflection.create(ITelephonyStatic.class, (Object) null, true);
    }

    public static ITelephonyStatic get() {
        return (ITelephonyStatic) BlackReflection.create(ITelephonyStatic.class, (Object) null, false);
    }

    public static ITelephonyContext getWithException(Object caller) {
        return (ITelephonyContext) BlackReflection.create(ITelephonyContext.class, caller, true);
    }

    public static ITelephonyContext get(Object caller) {
        return (ITelephonyContext) BlackReflection.create(ITelephonyContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelephonyContext.class);
    }
}
