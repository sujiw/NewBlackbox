package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRBroadcastReceiverPendingResult.class */
public class BRBroadcastReceiverPendingResult {
    public static BroadcastReceiverPendingResultStatic getWithException() {
        return (BroadcastReceiverPendingResultStatic) BlackReflection.create(BroadcastReceiverPendingResultStatic.class, (Object) null, true);
    }

    public static BroadcastReceiverPendingResultStatic get() {
        return (BroadcastReceiverPendingResultStatic) BlackReflection.create(BroadcastReceiverPendingResultStatic.class, (Object) null, false);
    }

    public static BroadcastReceiverPendingResultContext getWithException(Object caller) {
        return (BroadcastReceiverPendingResultContext) BlackReflection.create(BroadcastReceiverPendingResultContext.class, caller, true);
    }

    public static BroadcastReceiverPendingResultContext get(Object caller) {
        return (BroadcastReceiverPendingResultContext) BlackReflection.create(BroadcastReceiverPendingResultContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BroadcastReceiverPendingResultContext.class);
    }
}
