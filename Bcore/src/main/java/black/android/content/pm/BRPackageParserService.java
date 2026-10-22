package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserService.class */
public class BRPackageParserService {
    public static PackageParserServiceStatic getWithException() {
        return (PackageParserServiceStatic) BlackReflection.create(PackageParserServiceStatic.class, (Object) null, true);
    }

    public static PackageParserServiceStatic get() {
        return (PackageParserServiceStatic) BlackReflection.create(PackageParserServiceStatic.class, (Object) null, false);
    }

    public static PackageParserServiceContext getWithException(Object caller) {
        return (PackageParserServiceContext) BlackReflection.create(PackageParserServiceContext.class, caller, true);
    }

    public static PackageParserServiceContext get(Object caller) {
        return (PackageParserServiceContext) BlackReflection.create(PackageParserServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserServiceContext.class);
    }
}
