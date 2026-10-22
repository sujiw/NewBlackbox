package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRIVibratorServiceStub.class */
public class BRIVibratorServiceStub {
    public static IVibratorServiceStubStatic getWithException() {
        return (IVibratorServiceStubStatic) BlackReflection.create(IVibratorServiceStubStatic.class, (Object) null, true);
    }

    public static IVibratorServiceStubStatic get() {
        return (IVibratorServiceStubStatic) BlackReflection.create(IVibratorServiceStubStatic.class, (Object) null, false);
    }

    public static IVibratorServiceStubContext getWithException(Object caller) {
        return (IVibratorServiceStubContext) BlackReflection.create(IVibratorServiceStubContext.class, caller, true);
    }

    public static IVibratorServiceStubContext get(Object caller) {
        return (IVibratorServiceStubContext) BlackReflection.create(IVibratorServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVibratorServiceStubContext.class);
    }
}
