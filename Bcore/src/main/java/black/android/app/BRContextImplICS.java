package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRContextImplICS.class */
public class BRContextImplICS {
    public static ContextImplICSStatic getWithException() {
        return (ContextImplICSStatic) BlackReflection.create(ContextImplICSStatic.class, (Object) null, true);
    }

    public static ContextImplICSStatic get() {
        return (ContextImplICSStatic) BlackReflection.create(ContextImplICSStatic.class, (Object) null, false);
    }

    public static ContextImplICSContext getWithException(Object caller) {
        return (ContextImplICSContext) BlackReflection.create(ContextImplICSContext.class, caller, true);
    }

    public static ContextImplICSContext get(Object caller) {
        return (ContextImplICSContext) BlackReflection.create(ContextImplICSContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContextImplICSContext.class);
    }
}
