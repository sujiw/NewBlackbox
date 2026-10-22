package black.android.view.accessibility;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/accessibility/BRIAccessibilityManagerStub.class */
public class BRIAccessibilityManagerStub {
    public static IAccessibilityManagerStubStatic getWithException() {
        return (IAccessibilityManagerStubStatic) BlackReflection.create(IAccessibilityManagerStubStatic.class, (Object) null, true);
    }

    public static IAccessibilityManagerStubStatic get() {
        return (IAccessibilityManagerStubStatic) BlackReflection.create(IAccessibilityManagerStubStatic.class, (Object) null, false);
    }

    public static IAccessibilityManagerStubContext getWithException(Object caller) {
        return (IAccessibilityManagerStubContext) BlackReflection.create(IAccessibilityManagerStubContext.class, caller, true);
    }

    public static IAccessibilityManagerStubContext get(Object caller) {
        return (IAccessibilityManagerStubContext) BlackReflection.create(IAccessibilityManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAccessibilityManagerStubContext.class);
    }
}
