package black.android.rms.resource;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/BRReceiverResourceO.class */
public class BRReceiverResourceO {
    public static ReceiverResourceOStatic getWithException() {
        return (ReceiverResourceOStatic) BlackReflection.create(ReceiverResourceOStatic.class, (Object) null, true);
    }

    public static ReceiverResourceOStatic get() {
        return (ReceiverResourceOStatic) BlackReflection.create(ReceiverResourceOStatic.class, (Object) null, false);
    }

    public static ReceiverResourceOContext getWithException(Object caller) {
        return (ReceiverResourceOContext) BlackReflection.create(ReceiverResourceOContext.class, caller, true);
    }

    public static ReceiverResourceOContext get(Object caller) {
        return (ReceiverResourceOContext) BlackReflection.create(ReceiverResourceOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ReceiverResourceOContext.class);
    }
}
