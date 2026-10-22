package black.android.rms.resource;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/BRReceiverResourceN.class */
public class BRReceiverResourceN {
    public static ReceiverResourceNStatic getWithException() {
        return (ReceiverResourceNStatic) BlackReflection.create(ReceiverResourceNStatic.class, (Object) null, true);
    }

    public static ReceiverResourceNStatic get() {
        return (ReceiverResourceNStatic) BlackReflection.create(ReceiverResourceNStatic.class, (Object) null, false);
    }

    public static ReceiverResourceNContext getWithException(Object caller) {
        return (ReceiverResourceNContext) BlackReflection.create(ReceiverResourceNContext.class, caller, true);
    }

    public static ReceiverResourceNContext get(Object caller) {
        return (ReceiverResourceNContext) BlackReflection.create(ReceiverResourceNContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ReceiverResourceNContext.class);
    }
}
