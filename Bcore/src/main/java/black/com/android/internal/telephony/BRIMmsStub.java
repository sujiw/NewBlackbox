package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRIMmsStub.class */
public class BRIMmsStub {
    public static IMmsStubStatic getWithException() {
        return (IMmsStubStatic) BlackReflection.create(IMmsStubStatic.class, (Object) null, true);
    }

    public static IMmsStubStatic get() {
        return (IMmsStubStatic) BlackReflection.create(IMmsStubStatic.class, (Object) null, false);
    }

    public static IMmsStubContext getWithException(Object caller) {
        return (IMmsStubContext) BlackReflection.create(IMmsStubContext.class, caller, true);
    }

    public static IMmsStubContext get(Object caller) {
        return (IMmsStubContext) BlackReflection.create(IMmsStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMmsStubContext.class);
    }
}
