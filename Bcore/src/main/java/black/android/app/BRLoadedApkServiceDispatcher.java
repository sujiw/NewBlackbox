package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkServiceDispatcher.class */
public class BRLoadedApkServiceDispatcher {
    public static LoadedApkServiceDispatcherStatic getWithException() {
        return (LoadedApkServiceDispatcherStatic) BlackReflection.create(LoadedApkServiceDispatcherStatic.class, (Object) null, true);
    }

    public static LoadedApkServiceDispatcherStatic get() {
        return (LoadedApkServiceDispatcherStatic) BlackReflection.create(LoadedApkServiceDispatcherStatic.class, (Object) null, false);
    }

    public static LoadedApkServiceDispatcherContext getWithException(Object caller) {
        return (LoadedApkServiceDispatcherContext) BlackReflection.create(LoadedApkServiceDispatcherContext.class, caller, true);
    }

    public static LoadedApkServiceDispatcherContext get(Object caller) {
        return (LoadedApkServiceDispatcherContext) BlackReflection.create(LoadedApkServiceDispatcherContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkServiceDispatcherContext.class);
    }
}
