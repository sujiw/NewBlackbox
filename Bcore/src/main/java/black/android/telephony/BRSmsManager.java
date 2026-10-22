package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRSmsManager.class */
public class BRSmsManager {
    public static SmsManagerStatic getWithException() {
        return (SmsManagerStatic) BlackReflection.create(SmsManagerStatic.class, (Object) null, true);
    }

    public static SmsManagerStatic get() {
        return (SmsManagerStatic) BlackReflection.create(SmsManagerStatic.class, (Object) null, false);
    }

    public static SmsManagerContext getWithException(Object caller) {
        return (SmsManagerContext) BlackReflection.create(SmsManagerContext.class, caller, true);
    }

    public static SmsManagerContext get(Object caller) {
        return (SmsManagerContext) BlackReflection.create(SmsManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SmsManagerContext.class);
    }
}
