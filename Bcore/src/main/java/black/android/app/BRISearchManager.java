package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRISearchManager.class */
public class BRISearchManager {
    public static ISearchManagerStatic getWithException() {
        return (ISearchManagerStatic) BlackReflection.create(ISearchManagerStatic.class, (Object) null, true);
    }

    public static ISearchManagerStatic get() {
        return (ISearchManagerStatic) BlackReflection.create(ISearchManagerStatic.class, (Object) null, false);
    }

    public static ISearchManagerContext getWithException(Object caller) {
        return (ISearchManagerContext) BlackReflection.create(ISearchManagerContext.class, caller, true);
    }

    public static ISearchManagerContext get(Object caller) {
        return (ISearchManagerContext) BlackReflection.create(ISearchManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISearchManagerContext.class);
    }
}
