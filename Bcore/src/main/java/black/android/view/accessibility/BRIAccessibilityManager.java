package black.android.view.accessibility;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/accessibility/BRIAccessibilityManager.class */
public class BRIAccessibilityManager {
    public static IAccessibilityManagerStatic getWithException() {
        return (IAccessibilityManagerStatic) BlackReflection.create(IAccessibilityManagerStatic.class, (Object) null, true);
    }

    public static IAccessibilityManagerStatic get() {
        return (IAccessibilityManagerStatic) BlackReflection.create(IAccessibilityManagerStatic.class, (Object) null, false);
    }

    public static IAccessibilityManagerContext getWithException(Object caller) {
        return (IAccessibilityManagerContext) BlackReflection.create(IAccessibilityManagerContext.class, caller, true);
    }

    public static IAccessibilityManagerContext get(Object caller) {
        return (IAccessibilityManagerContext) BlackReflection.create(IAccessibilityManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAccessibilityManagerContext.class);
    }
}
