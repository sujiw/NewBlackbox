package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageInstallerSessionInfo.class */
public class BRPackageInstallerSessionInfo {
    public static PackageInstallerSessionInfoStatic getWithException() {
        return (PackageInstallerSessionInfoStatic) BlackReflection.create(PackageInstallerSessionInfoStatic.class, (Object) null, true);
    }

    public static PackageInstallerSessionInfoStatic get() {
        return (PackageInstallerSessionInfoStatic) BlackReflection.create(PackageInstallerSessionInfoStatic.class, (Object) null, false);
    }

    public static PackageInstallerSessionInfoContext getWithException(Object caller) {
        return (PackageInstallerSessionInfoContext) BlackReflection.create(PackageInstallerSessionInfoContext.class, caller, true);
    }

    public static PackageInstallerSessionInfoContext get(Object caller) {
        return (PackageInstallerSessionInfoContext) BlackReflection.create(PackageInstallerSessionInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageInstallerSessionInfoContext.class);
    }
}
