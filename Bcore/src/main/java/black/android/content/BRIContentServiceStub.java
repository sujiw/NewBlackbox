package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIContentServiceStub.class */
public class BRIContentServiceStub {
    public static IContentServiceStubStatic getWithException() {
        return (IContentServiceStubStatic) BlackReflection.create(IContentServiceStubStatic.class, (Object) null, true);
    }

    public static IContentServiceStubStatic get() {
        return (IContentServiceStubStatic) BlackReflection.create(IContentServiceStubStatic.class, (Object) null, false);
    }

    public static IContentServiceStubContext getWithException(Object caller) {
        return (IContentServiceStubContext) BlackReflection.create(IContentServiceStubContext.class, caller, true);
    }

    public static IContentServiceStubContext get(Object caller) {
        return (IContentServiceStubContext) BlackReflection.create(IContentServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IContentServiceStubContext.class);
    }
}
