package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserNougat.class */
public class BRPackageParserNougat {
    public static PackageParserNougatStatic getWithException() {
        return (PackageParserNougatStatic) BlackReflection.create(PackageParserNougatStatic.class, (Object) null, true);
    }

    public static PackageParserNougatStatic get() {
        return (PackageParserNougatStatic) BlackReflection.create(PackageParserNougatStatic.class, (Object) null, false);
    }

    public static PackageParserNougatContext getWithException(Object caller) {
        return (PackageParserNougatContext) BlackReflection.create(PackageParserNougatContext.class, caller, true);
    }

    public static PackageParserNougatContext get(Object caller) {
        return (PackageParserNougatContext) BlackReflection.create(PackageParserNougatContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserNougatContext.class);
    }
}
