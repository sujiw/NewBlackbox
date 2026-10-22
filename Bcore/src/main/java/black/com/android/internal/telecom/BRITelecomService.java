package black.com.android.internal.telecom;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telecom/BRITelecomService.class */
public class BRITelecomService {
    public static ITelecomServiceStatic getWithException() {
        return (ITelecomServiceStatic) BlackReflection.create(ITelecomServiceStatic.class, (Object) null, true);
    }

    public static ITelecomServiceStatic get() {
        return (ITelecomServiceStatic) BlackReflection.create(ITelecomServiceStatic.class, (Object) null, false);
    }

    public static ITelecomServiceContext getWithException(Object caller) {
        return (ITelecomServiceContext) BlackReflection.create(ITelecomServiceContext.class, caller, true);
    }

    public static ITelecomServiceContext get(Object caller) {
        return (ITelecomServiceContext) BlackReflection.create(ITelecomServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelecomServiceContext.class);
    }
}
