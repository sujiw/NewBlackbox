package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserProvider.class */
public class BRPackageParserProvider {
    public static PackageParserProviderStatic getWithException() {
        return (PackageParserProviderStatic) BlackReflection.create(PackageParserProviderStatic.class, (Object) null, true);
    }

    public static PackageParserProviderStatic get() {
        return (PackageParserProviderStatic) BlackReflection.create(PackageParserProviderStatic.class, (Object) null, false);
    }

    public static PackageParserProviderContext getWithException(Object caller) {
        return (PackageParserProviderContext) BlackReflection.create(PackageParserProviderContext.class, caller, true);
    }

    public static PackageParserProviderContext get(Object caller) {
        return (PackageParserProviderContext) BlackReflection.create(PackageParserProviderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserProviderContext.class);
    }
}
