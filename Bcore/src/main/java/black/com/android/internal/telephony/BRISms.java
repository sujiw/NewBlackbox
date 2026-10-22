package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRISms.class */
public class BRISms {
    public static ISmsStatic getWithException() {
        return (ISmsStatic) BlackReflection.create(ISmsStatic.class, (Object) null, true);
    }

    public static ISmsStatic get() {
        return (ISmsStatic) BlackReflection.create(ISmsStatic.class, (Object) null, false);
    }

    public static ISmsContext getWithException(Object caller) {
        return (ISmsContext) BlackReflection.create(ISmsContext.class, caller, true);
    }

    public static ISmsContext get(Object caller) {
        return (ISmsContext) BlackReflection.create(ISmsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISmsContext.class);
    }
}
