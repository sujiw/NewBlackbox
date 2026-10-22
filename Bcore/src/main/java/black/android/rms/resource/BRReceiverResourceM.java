package black.android.rms.resource;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/BRReceiverResourceM.class */
public class BRReceiverResourceM {
    public static ReceiverResourceMStatic getWithException() {
        return (ReceiverResourceMStatic) BlackReflection.create(ReceiverResourceMStatic.class, (Object) null, true);
    }

    public static ReceiverResourceMStatic get() {
        return (ReceiverResourceMStatic) BlackReflection.create(ReceiverResourceMStatic.class, (Object) null, false);
    }

    public static ReceiverResourceMContext getWithException(Object caller) {
        return (ReceiverResourceMContext) BlackReflection.create(ReceiverResourceMContext.class, caller, true);
    }

    public static ReceiverResourceMContext get(Object caller) {
        return (ReceiverResourceMContext) BlackReflection.create(ReceiverResourceMContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ReceiverResourceMContext.class);
    }
}
