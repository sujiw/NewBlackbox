package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRISubStub.class */
public class BRISubStub {
    public static ISubStubStatic getWithException() {
        return (ISubStubStatic) BlackReflection.create(ISubStubStatic.class, (Object) null, true);
    }

    public static ISubStubStatic get() {
        return (ISubStubStatic) BlackReflection.create(ISubStubStatic.class, (Object) null, false);
    }

    public static ISubStubContext getWithException(Object caller) {
        return (ISubStubContext) BlackReflection.create(ISubStubContext.class, caller, true);
    }

    public static ISubStubContext get(Object caller) {
        return (ISubStubContext) BlackReflection.create(ISubStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISubStubContext.class);
    }
}
