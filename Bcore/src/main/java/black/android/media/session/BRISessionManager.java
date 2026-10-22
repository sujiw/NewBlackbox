package black.android.media.session;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/session/BRISessionManager.class */
public class BRISessionManager {
    public static ISessionManagerStatic getWithException() {
        return (ISessionManagerStatic) BlackReflection.create(ISessionManagerStatic.class, (Object) null, true);
    }

    public static ISessionManagerStatic get() {
        return (ISessionManagerStatic) BlackReflection.create(ISessionManagerStatic.class, (Object) null, false);
    }

    public static ISessionManagerContext getWithException(Object caller) {
        return (ISessionManagerContext) BlackReflection.create(ISessionManagerContext.class, caller, true);
    }

    public static ISessionManagerContext get(Object caller) {
        return (ISessionManagerContext) BlackReflection.create(ISessionManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISessionManagerContext.class);
    }
}
