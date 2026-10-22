package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserMarshmallow.class */
public class BRPackageParserMarshmallow {
    public static PackageParserMarshmallowStatic getWithException() {
        return (PackageParserMarshmallowStatic) BlackReflection.create(PackageParserMarshmallowStatic.class, (Object) null, true);
    }

    public static PackageParserMarshmallowStatic get() {
        return (PackageParserMarshmallowStatic) BlackReflection.create(PackageParserMarshmallowStatic.class, (Object) null, false);
    }

    public static PackageParserMarshmallowContext getWithException(Object caller) {
        return (PackageParserMarshmallowContext) BlackReflection.create(PackageParserMarshmallowContext.class, caller, true);
    }

    public static PackageParserMarshmallowContext get(Object caller) {
        return (PackageParserMarshmallowContext) BlackReflection.create(PackageParserMarshmallowContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserMarshmallowContext.class);
    }
}
