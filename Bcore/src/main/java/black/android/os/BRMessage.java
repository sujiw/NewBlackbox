package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRMessage.class */
public class BRMessage {
    public static MessageStatic getWithException() {
        return (MessageStatic) BlackReflection.create(MessageStatic.class, (Object) null, true);
    }

    public static MessageStatic get() {
        return (MessageStatic) BlackReflection.create(MessageStatic.class, (Object) null, false);
    }

    public static MessageContext getWithException(Object caller) {
        return (MessageContext) BlackReflection.create(MessageContext.class, caller, true);
    }

    public static MessageContext get(Object caller) {
        return (MessageContext) BlackReflection.create(MessageContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(MessageContext.class);
    }
}
