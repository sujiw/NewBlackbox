package black.android.rms.resource;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/BRReceiverResourceLP.class */
public class BRReceiverResourceLP {
    public static ReceiverResourceLPStatic getWithException() {
        return (ReceiverResourceLPStatic) BlackReflection.create(ReceiverResourceLPStatic.class, (Object) null, true);
    }

    public static ReceiverResourceLPStatic get() {
        return (ReceiverResourceLPStatic) BlackReflection.create(ReceiverResourceLPStatic.class, (Object) null, false);
    }

    public static ReceiverResourceLPContext getWithException(Object caller) {
        return (ReceiverResourceLPContext) BlackReflection.create(ReceiverResourceLPContext.class, caller, true);
    }

    public static ReceiverResourceLPContext get(Object caller) {
        return (ReceiverResourceLPContext) BlackReflection.create(ReceiverResourceLPContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ReceiverResourceLPContext.class);
    }
}
