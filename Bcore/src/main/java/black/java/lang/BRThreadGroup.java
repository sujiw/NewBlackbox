package black.java.lang;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/java/lang/BRThreadGroup.class */
public class BRThreadGroup {
    public static ThreadGroupStatic getWithException() {
        return (ThreadGroupStatic) BlackReflection.create(ThreadGroupStatic.class, (Object) null, true);
    }

    public static ThreadGroupStatic get() {
        return (ThreadGroupStatic) BlackReflection.create(ThreadGroupStatic.class, (Object) null, false);
    }

    public static ThreadGroupContext getWithException(Object caller) {
        return (ThreadGroupContext) BlackReflection.create(ThreadGroupContext.class, caller, true);
    }

    public static ThreadGroupContext get(Object caller) {
        return (ThreadGroupContext) BlackReflection.create(ThreadGroupContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ThreadGroupContext.class);
    }
}
