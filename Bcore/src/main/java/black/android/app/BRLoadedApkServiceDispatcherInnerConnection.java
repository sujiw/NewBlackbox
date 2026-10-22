package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkServiceDispatcherInnerConnection.class */
public class BRLoadedApkServiceDispatcherInnerConnection {
    public static LoadedApkServiceDispatcherInnerConnectionStatic getWithException() {
        return (LoadedApkServiceDispatcherInnerConnectionStatic) BlackReflection.create(LoadedApkServiceDispatcherInnerConnectionStatic.class, (Object) null, true);
    }

    public static LoadedApkServiceDispatcherInnerConnectionStatic get() {
        return (LoadedApkServiceDispatcherInnerConnectionStatic) BlackReflection.create(LoadedApkServiceDispatcherInnerConnectionStatic.class, (Object) null, false);
    }

    public static LoadedApkServiceDispatcherInnerConnectionContext getWithException(Object caller) {
        return (LoadedApkServiceDispatcherInnerConnectionContext) BlackReflection.create(LoadedApkServiceDispatcherInnerConnectionContext.class, caller, true);
    }

    public static LoadedApkServiceDispatcherInnerConnectionContext get(Object caller) {
        return (LoadedApkServiceDispatcherInnerConnectionContext) BlackReflection.create(LoadedApkServiceDispatcherInnerConnectionContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkServiceDispatcherInnerConnectionContext.class);
    }
}
