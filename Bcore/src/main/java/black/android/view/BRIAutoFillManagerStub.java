package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIAutoFillManagerStub.class */
public class BRIAutoFillManagerStub {
    public static IAutoFillManagerStubStatic getWithException() {
        return (IAutoFillManagerStubStatic) BlackReflection.create(IAutoFillManagerStubStatic.class, (Object) null, true);
    }

    public static IAutoFillManagerStubStatic get() {
        return (IAutoFillManagerStubStatic) BlackReflection.create(IAutoFillManagerStubStatic.class, (Object) null, false);
    }

    public static IAutoFillManagerStubContext getWithException(Object caller) {
        return (IAutoFillManagerStubContext) BlackReflection.create(IAutoFillManagerStubContext.class, caller, true);
    }

    public static IAutoFillManagerStubContext get(Object caller) {
        return (IAutoFillManagerStubContext) BlackReflection.create(IAutoFillManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAutoFillManagerStubContext.class);
    }
}
