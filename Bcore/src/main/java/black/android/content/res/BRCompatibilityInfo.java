package black.android.content.res;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/res/BRCompatibilityInfo.class */
public class BRCompatibilityInfo {
    public static CompatibilityInfoStatic getWithException() {
        return (CompatibilityInfoStatic) BlackReflection.create(CompatibilityInfoStatic.class, (Object) null, true);
    }

    public static CompatibilityInfoStatic get() {
        return (CompatibilityInfoStatic) BlackReflection.create(CompatibilityInfoStatic.class, (Object) null, false);
    }

    public static CompatibilityInfoContext getWithException(Object caller) {
        return (CompatibilityInfoContext) BlackReflection.create(CompatibilityInfoContext.class, caller, true);
    }

    public static CompatibilityInfoContext get(Object caller) {
        return (CompatibilityInfoContext) BlackReflection.create(CompatibilityInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CompatibilityInfoContext.class);
    }
}
