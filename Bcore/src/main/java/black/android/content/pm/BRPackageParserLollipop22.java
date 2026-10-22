package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageParserLollipop22.class */
public class BRPackageParserLollipop22 {
    public static PackageParserLollipop22Static getWithException() {
        return (PackageParserLollipop22Static) BlackReflection.create(PackageParserLollipop22Static.class, (Object) null, true);
    }

    public static PackageParserLollipop22Static get() {
        return (PackageParserLollipop22Static) BlackReflection.create(PackageParserLollipop22Static.class, (Object) null, false);
    }

    public static PackageParserLollipop22Context getWithException(Object caller) {
        return (PackageParserLollipop22Context) BlackReflection.create(PackageParserLollipop22Context.class, caller, true);
    }

    public static PackageParserLollipop22Context get(Object caller) {
        return (PackageParserLollipop22Context) BlackReflection.create(PackageParserLollipop22Context.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageParserLollipop22Context.class);
    }
}
