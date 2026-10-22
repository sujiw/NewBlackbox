package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRIVibratorService.class */
public class BRIVibratorService {
    public static IVibratorServiceStatic getWithException() {
        return (IVibratorServiceStatic) BlackReflection.create(IVibratorServiceStatic.class, (Object) null, true);
    }

    public static IVibratorServiceStatic get() {
        return (IVibratorServiceStatic) BlackReflection.create(IVibratorServiceStatic.class, (Object) null, false);
    }

    public static IVibratorServiceContext getWithException(Object caller) {
        return (IVibratorServiceContext) BlackReflection.create(IVibratorServiceContext.class, caller, true);
    }

    public static IVibratorServiceContext get(Object caller) {
        return (IVibratorServiceContext) BlackReflection.create(IVibratorServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IVibratorServiceContext.class);
    }
}
