package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParser.class */
public class BRPackageParser {
    public static PackageParserStatic getWithException() {
        return (PackageParserStatic) BlackReflection.create(PackageParserStatic.class, (Object) null, true);
    }

    public static PackageParserStatic get() {
        return (PackageParserStatic) BlackReflection.create(PackageParserStatic.class, (Object) null, false);
    }

    public static PackageParserContext getWithException(Object caller) {
        return (PackageParserContext) BlackReflection.create(PackageParserContext.class, caller, true);
    }

    public static PackageParserContext get(Object caller) {
        return (PackageParserContext) BlackReflection.create(PackageParserContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserContext.class);
    }
}
