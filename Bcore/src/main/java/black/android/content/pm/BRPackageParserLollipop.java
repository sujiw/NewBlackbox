package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserLollipop.class */
public class BRPackageParserLollipop {
    public static PackageParserLollipopStatic getWithException() {
        return (PackageParserLollipopStatic) BlackReflection.create(PackageParserLollipopStatic.class, (Object) null, true);
    }

    public static PackageParserLollipopStatic get() {
        return (PackageParserLollipopStatic) BlackReflection.create(PackageParserLollipopStatic.class, (Object) null, false);
    }

    public static PackageParserLollipopContext getWithException(Object caller) {
        return (PackageParserLollipopContext) BlackReflection.create(PackageParserLollipopContext.class, caller, true);
    }

    public static PackageParserLollipopContext get(Object caller) {
        return (PackageParserLollipopContext) BlackReflection.create(PackageParserLollipopContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserLollipopContext.class);
    }
}
