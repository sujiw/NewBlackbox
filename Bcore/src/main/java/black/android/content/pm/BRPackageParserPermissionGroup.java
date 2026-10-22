package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserPermissionGroup.class */
public class BRPackageParserPermissionGroup {
    public static PackageParserPermissionGroupStatic getWithException() {
        return (PackageParserPermissionGroupStatic) BlackReflection.create(PackageParserPermissionGroupStatic.class, (Object) null, true);
    }

    public static PackageParserPermissionGroupStatic get() {
        return (PackageParserPermissionGroupStatic) BlackReflection.create(PackageParserPermissionGroupStatic.class, (Object) null, false);
    }

    public static PackageParserPermissionGroupContext getWithException(Object caller) {
        return (PackageParserPermissionGroupContext) BlackReflection.create(PackageParserPermissionGroupContext.class, caller, true);
    }

    public static PackageParserPermissionGroupContext get(Object caller) {
        return (PackageParserPermissionGroupContext) BlackReflection.create(PackageParserPermissionGroupContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserPermissionGroupContext.class);
    }
}
