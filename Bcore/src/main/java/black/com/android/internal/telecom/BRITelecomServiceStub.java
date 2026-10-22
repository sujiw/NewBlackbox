package black.com.android.internal.telecom;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telecom/BRITelecomServiceStub.class */
public class BRITelecomServiceStub {
    public static ITelecomServiceStubStatic getWithException() {
        return (ITelecomServiceStubStatic) BlackReflection.create(ITelecomServiceStubStatic.class, (Object) null, true);
    }

    public static ITelecomServiceStubStatic get() {
        return (ITelecomServiceStubStatic) BlackReflection.create(ITelecomServiceStubStatic.class, (Object) null, false);
    }

    public static ITelecomServiceStubContext getWithException(Object caller) {
        return (ITelecomServiceStubContext) BlackReflection.create(ITelecomServiceStubContext.class, caller, true);
    }

    public static ITelecomServiceStubContext get(Object caller) {
        return (ITelecomServiceStubContext) BlackReflection.create(ITelecomServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ITelecomServiceStubContext.class);
    }
}
