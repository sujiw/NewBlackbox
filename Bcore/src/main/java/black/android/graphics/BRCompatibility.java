package black.android.graphics;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/graphics/BRCompatibility.class */
public class BRCompatibility {
    public static CompatibilityStatic getWithException() {
        return (CompatibilityStatic) BlackReflection.create(CompatibilityStatic.class, (Object) null, true);
    }

    public static CompatibilityStatic get() {
        return (CompatibilityStatic) BlackReflection.create(CompatibilityStatic.class, (Object) null, false);
    }

    public static CompatibilityContext getWithException(Object caller) {
        return (CompatibilityContext) BlackReflection.create(CompatibilityContext.class, caller, true);
    }

    public static CompatibilityContext get(Object caller) {
        return (CompatibilityContext) BlackReflection.create(CompatibilityContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CompatibilityContext.class);
    }
}
