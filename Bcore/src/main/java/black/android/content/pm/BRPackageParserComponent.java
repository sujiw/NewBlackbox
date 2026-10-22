package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserComponent.class */
public class BRPackageParserComponent {
    public static PackageParserComponentStatic getWithException() {
        return (PackageParserComponentStatic) BlackReflection.create(PackageParserComponentStatic.class, (Object) null, true);
    }

    public static PackageParserComponentStatic get() {
        return (PackageParserComponentStatic) BlackReflection.create(PackageParserComponentStatic.class, (Object) null, false);
    }

    public static PackageParserComponentContext getWithException(Object caller) {
        return (PackageParserComponentContext) BlackReflection.create(PackageParserComponentContext.class, caller, true);
    }

    public static PackageParserComponentContext get(Object caller) {
        return (PackageParserComponentContext) BlackReflection.create(PackageParserComponentContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserComponentContext.class);
    }
}
