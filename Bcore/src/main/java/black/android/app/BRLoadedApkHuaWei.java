package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkHuaWei.class */
public class BRLoadedApkHuaWei {
    public static LoadedApkHuaWeiStatic getWithException() {
        return (LoadedApkHuaWeiStatic) BlackReflection.create(LoadedApkHuaWeiStatic.class, (Object) null, true);
    }

    public static LoadedApkHuaWeiStatic get() {
        return (LoadedApkHuaWeiStatic) BlackReflection.create(LoadedApkHuaWeiStatic.class, (Object) null, false);
    }

    public static LoadedApkHuaWeiContext getWithException(Object caller) {
        return (LoadedApkHuaWeiContext) BlackReflection.create(LoadedApkHuaWeiContext.class, caller, true);
    }

    public static LoadedApkHuaWeiContext get(Object caller) {
        return (LoadedApkHuaWeiContext) BlackReflection.create(LoadedApkHuaWeiContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkHuaWeiContext.class);
    }
}
