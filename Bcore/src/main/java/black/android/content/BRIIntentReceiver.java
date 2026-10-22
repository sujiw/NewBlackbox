package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIIntentReceiver.class */
public class BRIIntentReceiver {
    public static IIntentReceiverStatic getWithException() {
        return (IIntentReceiverStatic) BlackReflection.create(IIntentReceiverStatic.class, (Object) null, true);
    }

    public static IIntentReceiverStatic get() {
        return (IIntentReceiverStatic) BlackReflection.create(IIntentReceiverStatic.class, (Object) null, false);
    }

    public static IIntentReceiverContext getWithException(Object caller) {
        return (IIntentReceiverContext) BlackReflection.create(IIntentReceiverContext.class, caller, true);
    }

    public static IIntentReceiverContext get(Object caller) {
        return (IIntentReceiverContext) BlackReflection.create(IIntentReceiverContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IIntentReceiverContext.class);
    }
}
