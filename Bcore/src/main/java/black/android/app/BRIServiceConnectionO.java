package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIServiceConnectionO.class */
public class BRIServiceConnectionO {
    public static IServiceConnectionOStatic getWithException() {
        return (IServiceConnectionOStatic) BlackReflection.create(IServiceConnectionOStatic.class, (Object) null, true);
    }

    public static IServiceConnectionOStatic get() {
        return (IServiceConnectionOStatic) BlackReflection.create(IServiceConnectionOStatic.class, (Object) null, false);
    }

    public static IServiceConnectionOContext getWithException(Object caller) {
        return (IServiceConnectionOContext) BlackReflection.create(IServiceConnectionOContext.class, caller, true);
    }

    public static IServiceConnectionOContext get(Object caller) {
        return (IServiceConnectionOContext) BlackReflection.create(IServiceConnectionOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IServiceConnectionOContext.class);
    }
}
