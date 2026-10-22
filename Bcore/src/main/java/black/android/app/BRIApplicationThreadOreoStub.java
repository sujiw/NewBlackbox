package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIApplicationThreadOreoStub.class */
public class BRIApplicationThreadOreoStub {
    public static IApplicationThreadOreoStubStatic getWithException() {
        return (IApplicationThreadOreoStubStatic) BlackReflection.create(IApplicationThreadOreoStubStatic.class, (Object) null, true);
    }

    public static IApplicationThreadOreoStubStatic get() {
        return (IApplicationThreadOreoStubStatic) BlackReflection.create(IApplicationThreadOreoStubStatic.class, (Object) null, false);
    }

    public static IApplicationThreadOreoStubContext getWithException(Object caller) {
        return (IApplicationThreadOreoStubContext) BlackReflection.create(IApplicationThreadOreoStubContext.class, caller, true);
    }

    public static IApplicationThreadOreoStubContext get(Object caller) {
        return (IApplicationThreadOreoStubContext) BlackReflection.create(IApplicationThreadOreoStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IApplicationThreadOreoStubContext.class);
    }
}
