package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRPendingIntentO.class */
public class BRPendingIntentO {
    public static PendingIntentOStatic getWithException() {
        return (PendingIntentOStatic) BlackReflection.create(PendingIntentOStatic.class, (Object) null, true);
    }

    public static PendingIntentOStatic get() {
        return (PendingIntentOStatic) BlackReflection.create(PendingIntentOStatic.class, (Object) null, false);
    }

    public static PendingIntentOContext getWithException(Object caller) {
        return (PendingIntentOContext) BlackReflection.create(PendingIntentOContext.class, caller, true);
    }

    public static PendingIntentOContext get(Object caller) {
        return (PendingIntentOContext) BlackReflection.create(PendingIntentOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PendingIntentOContext.class);
    }
}
