package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIRestrictionsManagerStub.class */
public class BRIRestrictionsManagerStub {
    public static IRestrictionsManagerStubStatic getWithException() {
        return (IRestrictionsManagerStubStatic) BlackReflection.create(IRestrictionsManagerStubStatic.class, (Object) null, true);
    }

    public static IRestrictionsManagerStubStatic get() {
        return (IRestrictionsManagerStubStatic) BlackReflection.create(IRestrictionsManagerStubStatic.class, (Object) null, false);
    }

    public static IRestrictionsManagerStubContext getWithException(Object caller) {
        return (IRestrictionsManagerStubContext) BlackReflection.create(IRestrictionsManagerStubContext.class, caller, true);
    }

    public static IRestrictionsManagerStubContext get(Object caller) {
        return (IRestrictionsManagerStubContext) BlackReflection.create(IRestrictionsManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IRestrictionsManagerStubContext.class);
    }
}
