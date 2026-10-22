package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRIMms.class */
public class BRIMms {
    public static IMmsStatic getWithException() {
        return (IMmsStatic) BlackReflection.create(IMmsStatic.class, (Object) null, true);
    }

    public static IMmsStatic get() {
        return (IMmsStatic) BlackReflection.create(IMmsStatic.class, (Object) null, false);
    }

    public static IMmsContext getWithException(Object caller) {
        return (IMmsContext) BlackReflection.create(IMmsContext.class, caller, true);
    }

    public static IMmsContext get(Object caller) {
        return (IMmsContext) BlackReflection.create(IMmsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMmsContext.class);
    }
}
