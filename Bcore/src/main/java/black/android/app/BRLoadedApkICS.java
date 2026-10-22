package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkICS.class */
public class BRLoadedApkICS {
    public static LoadedApkICSStatic getWithException() {
        return (LoadedApkICSStatic) BlackReflection.create(LoadedApkICSStatic.class, (Object) null, true);
    }

    public static LoadedApkICSStatic get() {
        return (LoadedApkICSStatic) BlackReflection.create(LoadedApkICSStatic.class, (Object) null, false);
    }

    public static LoadedApkICSContext getWithException(Object caller) {
        return (LoadedApkICSContext) BlackReflection.create(LoadedApkICSContext.class, caller, true);
    }

    public static LoadedApkICSContext get(Object caller) {
        return (LoadedApkICSContext) BlackReflection.create(LoadedApkICSContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkICSContext.class);
    }
}
