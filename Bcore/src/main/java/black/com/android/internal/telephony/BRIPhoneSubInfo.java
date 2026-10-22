package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRIPhoneSubInfo.class */
public class BRIPhoneSubInfo {
    public static IPhoneSubInfoStatic getWithException() {
        return (IPhoneSubInfoStatic) BlackReflection.create(IPhoneSubInfoStatic.class, (Object) null, true);
    }

    public static IPhoneSubInfoStatic get() {
        return (IPhoneSubInfoStatic) BlackReflection.create(IPhoneSubInfoStatic.class, (Object) null, false);
    }

    public static IPhoneSubInfoContext getWithException(Object caller) {
        return (IPhoneSubInfoContext) BlackReflection.create(IPhoneSubInfoContext.class, caller, true);
    }

    public static IPhoneSubInfoContext get(Object caller) {
        return (IPhoneSubInfoContext) BlackReflection.create(IPhoneSubInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPhoneSubInfoContext.class);
    }
}
