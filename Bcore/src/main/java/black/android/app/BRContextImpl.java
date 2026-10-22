package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRContextImpl.class */
public class BRContextImpl {
    public static ContextImplStatic getWithException() {
        return (ContextImplStatic) BlackReflection.create(ContextImplStatic.class, (Object) null, true);
    }

    public static ContextImplStatic get() {
        return (ContextImplStatic) BlackReflection.create(ContextImplStatic.class, (Object) null, false);
    }

    public static ContextImplContext getWithException(Object caller) {
        return (ContextImplContext) BlackReflection.create(ContextImplContext.class, caller, true);
    }

    public static ContextImplContext get(Object caller) {
        return (ContextImplContext) BlackReflection.create(ContextImplContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContextImplContext.class);
    }
}
