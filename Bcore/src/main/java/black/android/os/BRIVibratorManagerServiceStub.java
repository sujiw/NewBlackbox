package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIVibratorManagerServiceStub.class */
public class BRIVibratorManagerServiceStub {
    public static IVibratorManagerServiceStubStatic getWithException() {
        return (IVibratorManagerServiceStubStatic) BlackReflection.create(IVibratorManagerServiceStubStatic.class, (Object) null, true);
    }

    public static IVibratorManagerServiceStubStatic get() {
        return (IVibratorManagerServiceStubStatic) BlackReflection.create(IVibratorManagerServiceStubStatic.class, (Object) null, false);
    }

    public static IVibratorManagerServiceStubContext getWithException(Object caller) {
        return (IVibratorManagerServiceStubContext) BlackReflection.create(IVibratorManagerServiceStubContext.class, caller, true);
    }

    public static IVibratorManagerServiceStubContext get(Object caller) {
        return (IVibratorManagerServiceStubContext) BlackReflection.create(IVibratorManagerServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVibratorManagerServiceStubContext.class);
    }
}
