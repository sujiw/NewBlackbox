package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRCompatibilityInfoHolder.class */
public class BRCompatibilityInfoHolder {
    public static CompatibilityInfoHolderStatic getWithException() {
        return (CompatibilityInfoHolderStatic) BlackReflection.create(CompatibilityInfoHolderStatic.class, (Object) null, true);
    }

    public static CompatibilityInfoHolderStatic get() {
        return (CompatibilityInfoHolderStatic) BlackReflection.create(CompatibilityInfoHolderStatic.class, (Object) null, false);
    }

    public static CompatibilityInfoHolderContext getWithException(Object caller) {
        return (CompatibilityInfoHolderContext) BlackReflection.create(CompatibilityInfoHolderContext.class, caller, true);
    }

    public static CompatibilityInfoHolderContext get(Object caller) {
        return (CompatibilityInfoHolderContext) BlackReflection.create(CompatibilityInfoHolderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CompatibilityInfoHolderContext.class);
    }
}
