package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserPie.class */
public class BRPackageParserPie {
    public static PackageParserPieStatic getWithException() {
        return (PackageParserPieStatic) BlackReflection.create(PackageParserPieStatic.class, (Object) null, true);
    }

    public static PackageParserPieStatic get() {
        return (PackageParserPieStatic) BlackReflection.create(PackageParserPieStatic.class, (Object) null, false);
    }

    public static PackageParserPieContext getWithException(Object caller) {
        return (PackageParserPieContext) BlackReflection.create(PackageParserPieContext.class, caller, true);
    }

    public static PackageParserPieContext get(Object caller) {
        return (PackageParserPieContext) BlackReflection.create(PackageParserPieContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserPieContext.class);
    }
}
