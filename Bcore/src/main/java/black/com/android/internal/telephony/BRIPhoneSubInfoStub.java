package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRIPhoneSubInfoStub.class */
public class BRIPhoneSubInfoStub {
    public static IPhoneSubInfoStubStatic getWithException() {
        return (IPhoneSubInfoStubStatic) BlackReflection.create(IPhoneSubInfoStubStatic.class, (Object) null, true);
    }

    public static IPhoneSubInfoStubStatic get() {
        return (IPhoneSubInfoStubStatic) BlackReflection.create(IPhoneSubInfoStubStatic.class, (Object) null, false);
    }

    public static IPhoneSubInfoStubContext getWithException(Object caller) {
        return (IPhoneSubInfoStubContext) BlackReflection.create(IPhoneSubInfoStubContext.class, caller, true);
    }

    public static IPhoneSubInfoStubContext get(Object caller) {
        return (IPhoneSubInfoStubContext) BlackReflection.create(IPhoneSubInfoStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPhoneSubInfoStubContext.class);
    }
}
