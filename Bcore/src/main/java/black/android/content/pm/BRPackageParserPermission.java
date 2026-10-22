package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserPermission.class */
public class BRPackageParserPermission {
    public static PackageParserPermissionStatic getWithException() {
        return (PackageParserPermissionStatic) BlackReflection.create(PackageParserPermissionStatic.class, (Object) null, true);
    }

    public static PackageParserPermissionStatic get() {
        return (PackageParserPermissionStatic) BlackReflection.create(PackageParserPermissionStatic.class, (Object) null, false);
    }

    public static PackageParserPermissionContext getWithException(Object caller) {
        return (PackageParserPermissionContext) BlackReflection.create(PackageParserPermissionContext.class, caller, true);
    }

    public static PackageParserPermissionContext get(Object caller) {
        return (PackageParserPermissionContext) BlackReflection.create(PackageParserPermissionContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserPermissionContext.class);
    }
}
