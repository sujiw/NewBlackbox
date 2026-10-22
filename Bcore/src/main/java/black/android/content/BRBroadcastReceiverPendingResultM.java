package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRBroadcastReceiverPendingResultM.class */
public class BRBroadcastReceiverPendingResultM {
    public static BroadcastReceiverPendingResultMStatic getWithException() {
        return (BroadcastReceiverPendingResultMStatic) BlackReflection.create(BroadcastReceiverPendingResultMStatic.class, (Object) null, true);
    }

    public static BroadcastReceiverPendingResultMStatic get() {
        return (BroadcastReceiverPendingResultMStatic) BlackReflection.create(BroadcastReceiverPendingResultMStatic.class, (Object) null, false);
    }

    public static BroadcastReceiverPendingResultMContext getWithException(Object caller) {
        return (BroadcastReceiverPendingResultMContext) BlackReflection.create(BroadcastReceiverPendingResultMContext.class, caller, true);
    }

    public static BroadcastReceiverPendingResultMContext get(Object caller) {
        return (BroadcastReceiverPendingResultMContext) BlackReflection.create(BroadcastReceiverPendingResultMContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BroadcastReceiverPendingResultMContext.class);
    }
}
