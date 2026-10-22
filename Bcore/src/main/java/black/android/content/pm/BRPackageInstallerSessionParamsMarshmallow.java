package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageInstallerSessionParamsMarshmallow.class */
public class BRPackageInstallerSessionParamsMarshmallow {
    public static PackageInstallerSessionParamsMarshmallowStatic getWithException() {
        return (PackageInstallerSessionParamsMarshmallowStatic) BlackReflection.create(PackageInstallerSessionParamsMarshmallowStatic.class, (Object) null, true);
    }

    public static PackageInstallerSessionParamsMarshmallowStatic get() {
        return (PackageInstallerSessionParamsMarshmallowStatic) BlackReflection.create(PackageInstallerSessionParamsMarshmallowStatic.class, (Object) null, false);
    }

    public static PackageInstallerSessionParamsMarshmallowContext getWithException(Object caller) {
        return (PackageInstallerSessionParamsMarshmallowContext) BlackReflection.create(PackageInstallerSessionParamsMarshmallowContext.class, caller, true);
    }

    public static PackageInstallerSessionParamsMarshmallowContext get(Object caller) {
        return (PackageInstallerSessionParamsMarshmallowContext) BlackReflection.create(PackageInstallerSessionParamsMarshmallowContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageInstallerSessionParamsMarshmallowContext.class);
    }
}
