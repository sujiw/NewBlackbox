package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRBroadcastReceiver.class */
public class BRBroadcastReceiver {
    public static BroadcastReceiverStatic getWithException() {
        return (BroadcastReceiverStatic) BlackReflection.create(BroadcastReceiverStatic.class, (Object) null, true);
    }

    public static BroadcastReceiverStatic get() {
        return (BroadcastReceiverStatic) BlackReflection.create(BroadcastReceiverStatic.class, (Object) null, false);
    }

    public static BroadcastReceiverContext getWithException(Object caller) {
        return (BroadcastReceiverContext) BlackReflection.create(BroadcastReceiverContext.class, caller, true);
    }

    public static BroadcastReceiverContext get(Object caller) {
        return (BroadcastReceiverContext) BlackReflection.create(BroadcastReceiverContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BroadcastReceiverContext.class);
    }
}
