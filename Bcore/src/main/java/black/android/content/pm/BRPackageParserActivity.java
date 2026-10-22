package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserActivity.class */
public class BRPackageParserActivity {
    public static PackageParserActivityStatic getWithException() {
        return (PackageParserActivityStatic) BlackReflection.create(PackageParserActivityStatic.class, (Object) null, true);
    }

    public static PackageParserActivityStatic get() {
        return (PackageParserActivityStatic) BlackReflection.create(PackageParserActivityStatic.class, (Object) null, false);
    }

    public static PackageParserActivityContext getWithException(Object caller) {
        return (PackageParserActivityContext) BlackReflection.create(PackageParserActivityContext.class, caller, true);
    }

    public static PackageParserActivityContext get(Object caller) {
        return (PackageParserActivityContext) BlackReflection.create(PackageParserActivityContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserActivityContext.class);
    }
}
