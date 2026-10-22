package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRITelephonyStub.class */
public class BRITelephonyStub {
    public static ITelephonyStubStatic getWithException() {
        return (ITelephonyStubStatic) BlackReflection.create(ITelephonyStubStatic.class, (Object) null, true);
    }

    public static ITelephonyStubStatic get() {
        return (ITelephonyStubStatic) BlackReflection.create(ITelephonyStubStatic.class, (Object) null, false);
    }

    public static ITelephonyStubContext getWithException(Object caller) {
        return (ITelephonyStubContext) BlackReflection.create(ITelephonyStubContext.class, caller, true);
    }

    public static ITelephonyStubContext get(Object caller) {
        return (ITelephonyStubContext) BlackReflection.create(ITelephonyStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelephonyStubContext.class);
    }
}
