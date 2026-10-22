package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApk.class */
public class BRLoadedApk {
    public static LoadedApkStatic getWithException() {
        return (LoadedApkStatic) BlackReflection.create(LoadedApkStatic.class, (Object) null, true);
    }

    public static LoadedApkStatic get() {
        return (LoadedApkStatic) BlackReflection.create(LoadedApkStatic.class, (Object) null, false);
    }

    public static LoadedApkContext getWithException(Object caller) {
        return (LoadedApkContext) BlackReflection.create(LoadedApkContext.class, caller, true);
    }

    public static LoadedApkContext get(Object caller) {
        return (LoadedApkContext) BlackReflection.create(LoadedApkContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkContext.class);
    }
}
