package black.java.lang;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/java/lang/BRThreadGroupN.class */
public class BRThreadGroupN {
    public static ThreadGroupNStatic getWithException() {
        return (ThreadGroupNStatic) BlackReflection.create(ThreadGroupNStatic.class, (Object) null, true);
    }

    public static ThreadGroupNStatic get() {
        return (ThreadGroupNStatic) BlackReflection.create(ThreadGroupNStatic.class, (Object) null, false);
    }

    public static ThreadGroupNContext getWithException(Object caller) {
        return (ThreadGroupNContext) BlackReflection.create(ThreadGroupNContext.class, caller, true);
    }

    public static ThreadGroupNContext get(Object caller) {
        return (ThreadGroupNContext) BlackReflection.create(ThreadGroupNContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ThreadGroupNContext.class);
    }
}
