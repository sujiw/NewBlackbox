package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIntentSender.class */
public class BRIntentSender {
    public static IntentSenderStatic getWithException() {
        return (IntentSenderStatic) BlackReflection.create(IntentSenderStatic.class, (Object) null, true);
    }

    public static IntentSenderStatic get() {
        return (IntentSenderStatic) BlackReflection.create(IntentSenderStatic.class, (Object) null, false);
    }

    public static IntentSenderContext getWithException(Object caller) {
        return (IntentSenderContext) BlackReflection.create(IntentSenderContext.class, caller, true);
    }

    public static IntentSenderContext get(Object caller) {
        return (IntentSenderContext) BlackReflection.create(IntentSenderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IntentSenderContext.class);
    }
}
