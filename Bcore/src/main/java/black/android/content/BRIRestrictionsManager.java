package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIRestrictionsManager.class */
public class BRIRestrictionsManager {
    public static IRestrictionsManagerStatic getWithException() {
        return (IRestrictionsManagerStatic) BlackReflection.create(IRestrictionsManagerStatic.class, (Object) null, true);
    }

    public static IRestrictionsManagerStatic get() {
        return (IRestrictionsManagerStatic) BlackReflection.create(IRestrictionsManagerStatic.class, (Object) null, false);
    }

    public static IRestrictionsManagerContext getWithException(Object caller) {
        return (IRestrictionsManagerContext) BlackReflection.create(IRestrictionsManagerContext.class, caller, true);
    }

    public static IRestrictionsManagerContext get(Object caller) {
        return (IRestrictionsManagerContext) BlackReflection.create(IRestrictionsManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IRestrictionsManagerContext.class);
    }
}
