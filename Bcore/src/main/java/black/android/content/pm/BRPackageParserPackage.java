package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserPackage.class */
public class BRPackageParserPackage {
    public static PackageParserPackageStatic getWithException() {
        return (PackageParserPackageStatic) BlackReflection.create(PackageParserPackageStatic.class, (Object) null, true);
    }

    public static PackageParserPackageStatic get() {
        return (PackageParserPackageStatic) BlackReflection.create(PackageParserPackageStatic.class, (Object) null, false);
    }

    public static PackageParserPackageContext getWithException(Object caller) {
        return (PackageParserPackageContext) BlackReflection.create(PackageParserPackageContext.class, caller, true);
    }

    public static PackageParserPackageContext get(Object caller) {
        return (PackageParserPackageContext) BlackReflection.create(PackageParserPackageContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserPackageContext.class);
    }
}
