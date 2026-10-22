package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRIVibratorManagerService.class */
public class BRIVibratorManagerService {
    public static IVibratorManagerServiceStatic getWithException() {
        return (IVibratorManagerServiceStatic) BlackReflection.create(IVibratorManagerServiceStatic.class, (Object) null, true);
    }

    public static IVibratorManagerServiceStatic get() {
        return (IVibratorManagerServiceStatic) BlackReflection.create(IVibratorManagerServiceStatic.class, (Object) null, false);
    }

    public static IVibratorManagerServiceContext getWithException(Object caller) {
        return (IVibratorManagerServiceContext) BlackReflection.create(IVibratorManagerServiceContext.class, caller, true);
    }

    public static IVibratorManagerServiceContext get(Object caller) {
        return (IVibratorManagerServiceContext) BlackReflection.create(IVibratorManagerServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVibratorManagerServiceContext.class);
    }
}
