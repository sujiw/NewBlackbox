package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageInstallerSessionParamsLOLLIPOP.class */
public class BRPackageInstallerSessionParamsLOLLIPOP {
    public static PackageInstallerSessionParamsLOLLIPOPStatic getWithException() {
        return (PackageInstallerSessionParamsLOLLIPOPStatic) BlackReflection.create(PackageInstallerSessionParamsLOLLIPOPStatic.class, (Object) null, true);
    }

    public static PackageInstallerSessionParamsLOLLIPOPStatic get() {
        return (PackageInstallerSessionParamsLOLLIPOPStatic) BlackReflection.create(PackageInstallerSessionParamsLOLLIPOPStatic.class, (Object) null, false);
    }

    public static PackageInstallerSessionParamsLOLLIPOPContext getWithException(Object caller) {
        return (PackageInstallerSessionParamsLOLLIPOPContext) BlackReflection.create(PackageInstallerSessionParamsLOLLIPOPContext.class, caller, true);
    }

    public static PackageInstallerSessionParamsLOLLIPOPContext get(Object caller) {
        return (PackageInstallerSessionParamsLOLLIPOPContext) BlackReflection.create(PackageInstallerSessionParamsLOLLIPOPContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageInstallerSessionParamsLOLLIPOPContext.class);
    }
}
