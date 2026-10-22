package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRPackageUserState.class */
public class BRPackageUserState {
    public static PackageUserStateStatic getWithException() {
        return (PackageUserStateStatic) BlackReflection.create(PackageUserStateStatic.class, (Object) null, true);
    }

    public static PackageUserStateStatic get() {
        return (PackageUserStateStatic) BlackReflection.create(PackageUserStateStatic.class, (Object) null, false);
    }

    public static PackageUserStateContext getWithException(Object caller) {
        return (PackageUserStateContext) BlackReflection.create(PackageUserStateContext.class, caller, true);
    }

    public static PackageUserStateContext get(Object caller) {
        return (PackageUserStateContext) BlackReflection.create(PackageUserStateContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PackageUserStateContext.class);
    }
}
