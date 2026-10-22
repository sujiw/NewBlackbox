package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRISmsStub.class */
public class BRISmsStub {
    public static ISmsStubStatic getWithException() {
        return (ISmsStubStatic) BlackReflection.create(ISmsStubStatic.class, (Object) null, true);
    }

    public static ISmsStubStatic get() {
        return (ISmsStubStatic) BlackReflection.create(ISmsStubStatic.class, (Object) null, false);
    }

    public static ISmsStubContext getWithException(Object caller) {
        return (ISmsStubContext) BlackReflection.create(ISmsStubContext.class, caller, true);
    }

    public static ISmsStubContext get(Object caller) {
        return (ISmsStubContext) BlackReflection.create(ISmsStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISmsStubContext.class);
    }
}
