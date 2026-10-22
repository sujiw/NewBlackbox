package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRISearchManagerStub.class */
public class BRISearchManagerStub {
    public static ISearchManagerStubStatic getWithException() {
        return (ISearchManagerStubStatic) BlackReflection.create(ISearchManagerStubStatic.class, (Object) null, true);
    }

    public static ISearchManagerStubStatic get() {
        return (ISearchManagerStubStatic) BlackReflection.create(ISearchManagerStubStatic.class, (Object) null, false);
    }

    public static ISearchManagerStubContext getWithException(Object caller) {
        return (ISearchManagerStubContext) BlackReflection.create(ISearchManagerStubContext.class, caller, true);
    }

    public static ISearchManagerStubContext get(Object caller) {
        return (ISearchManagerStubContext) BlackReflection.create(ISearchManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ISearchManagerStubContext.class);
    }
}
