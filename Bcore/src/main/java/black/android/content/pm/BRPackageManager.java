package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageManager.class */
public class BRPackageManager {
    public static PackageManagerStatic getWithException() {
        return (PackageManagerStatic) BlackReflection.create(PackageManagerStatic.class, (Object) null, true);
    }

    public static PackageManagerStatic get() {
        return (PackageManagerStatic) BlackReflection.create(PackageManagerStatic.class, (Object) null, false);
    }

    public static PackageManagerContext getWithException(Object caller) {
        return (PackageManagerContext) BlackReflection.create(PackageManagerContext.class, caller, true);
    }

    public static PackageManagerContext get(Object caller) {
        return (PackageManagerContext) BlackReflection.create(PackageManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageManagerContext.class);
    }
}
