package black.android.media.session;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/session/BRISessionManagerStub.class */
public class BRISessionManagerStub {
    public static ISessionManagerStubStatic getWithException() {
        return (ISessionManagerStubStatic) BlackReflection.create(ISessionManagerStubStatic.class, (Object) null, true);
    }

    public static ISessionManagerStubStatic get() {
        return (ISessionManagerStubStatic) BlackReflection.create(ISessionManagerStubStatic.class, (Object) null, false);
    }

    public static ISessionManagerStubContext getWithException(Object caller) {
        return (ISessionManagerStubContext) BlackReflection.create(ISessionManagerStubContext.class, caller, true);
    }

    public static ISessionManagerStubContext get(Object caller) {
        return (ISessionManagerStubContext) BlackReflection.create(ISessionManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISessionManagerStubContext.class);
    }
}
