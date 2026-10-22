package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIWindowManagerStub.class */
public class BRIWindowManagerStub {
    public static IWindowManagerStubStatic getWithException() {
        return (IWindowManagerStubStatic) BlackReflection.create(IWindowManagerStubStatic.class, (Object) null, true);
    }

    public static IWindowManagerStubStatic get() {
        return (IWindowManagerStubStatic) BlackReflection.create(IWindowManagerStubStatic.class, (Object) null, false);
    }

    public static IWindowManagerStubContext getWithException(Object caller) {
        return (IWindowManagerStubContext) BlackReflection.create(IWindowManagerStubContext.class, caller, true);
    }

    public static IWindowManagerStubContext get(Object caller) {
        return (IWindowManagerStubContext) BlackReflection.create(IWindowManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWindowManagerStubContext.class);
    }
}
