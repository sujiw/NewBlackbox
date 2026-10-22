package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRISub.class */
public class BRISub {
    public static ISubStatic getWithException() {
        return (ISubStatic) BlackReflection.create(ISubStatic.class, (Object) null, true);
    }

    public static ISubStatic get() {
        return (ISubStatic) BlackReflection.create(ISubStatic.class, (Object) null, false);
    }

    public static ISubContext getWithException(Object caller) {
        return (ISubContext) BlackReflection.create(ISubContext.class, caller, true);
    }

    public static ISubContext get(Object caller) {
        return (ISubContext) BlackReflection.create(ISubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISubContext.class);
    }
}
