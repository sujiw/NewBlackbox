package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkKitkat.class */
public class BRLoadedApkKitkat {
    public static LoadedApkKitkatStatic getWithException() {
        return (LoadedApkKitkatStatic) BlackReflection.create(LoadedApkKitkatStatic.class, (Object) null, true);
    }

    public static LoadedApkKitkatStatic get() {
        return (LoadedApkKitkatStatic) BlackReflection.create(LoadedApkKitkatStatic.class, (Object) null, false);
    }

    public static LoadedApkKitkatContext getWithException(Object caller) {
        return (LoadedApkKitkatContext) BlackReflection.create(LoadedApkKitkatContext.class, caller, true);
    }

    public static LoadedApkKitkatContext get(Object caller) {
        return (LoadedApkKitkatContext) BlackReflection.create(LoadedApkKitkatContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkKitkatContext.class);
    }
}
