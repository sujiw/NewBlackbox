package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserSigningDetails.class */
public class BRPackageParserSigningDetails {
    public static PackageParserSigningDetailsStatic getWithException() {
        return (PackageParserSigningDetailsStatic) BlackReflection.create(PackageParserSigningDetailsStatic.class, (Object) null, true);
    }

    public static PackageParserSigningDetailsStatic get() {
        return (PackageParserSigningDetailsStatic) BlackReflection.create(PackageParserSigningDetailsStatic.class, (Object) null, false);
    }

    public static PackageParserSigningDetailsContext getWithException(Object caller) {
        return (PackageParserSigningDetailsContext) BlackReflection.create(PackageParserSigningDetailsContext.class, caller, true);
    }

    public static PackageParserSigningDetailsContext get(Object caller) {
        return (PackageParserSigningDetailsContext) BlackReflection.create(PackageParserSigningDetailsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserSigningDetailsContext.class);
    }
}
