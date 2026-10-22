package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageInstaller.class */
public class BRPackageInstaller {
    public static PackageInstallerStatic getWithException() {
        return (PackageInstallerStatic) BlackReflection.create(PackageInstallerStatic.class, (Object) null, true);
    }

    public static PackageInstallerStatic get() {
        return (PackageInstallerStatic) BlackReflection.create(PackageInstallerStatic.class, (Object) null, false);
    }

    public static PackageInstallerContext getWithException(Object caller) {
        return (PackageInstallerContext) BlackReflection.create(PackageInstallerContext.class, caller, true);
    }

    public static PackageInstallerContext get(Object caller) {
        return (PackageInstallerContext) BlackReflection.create(PackageInstallerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageInstallerContext.class);
    }
}
