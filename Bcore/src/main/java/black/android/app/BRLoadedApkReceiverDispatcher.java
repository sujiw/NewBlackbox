package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkReceiverDispatcher.class */
public class BRLoadedApkReceiverDispatcher {
    public static LoadedApkReceiverDispatcherStatic getWithException() {
        return (LoadedApkReceiverDispatcherStatic) BlackReflection.create(LoadedApkReceiverDispatcherStatic.class, (Object) null, true);
    }

    public static LoadedApkReceiverDispatcherStatic get() {
        return (LoadedApkReceiverDispatcherStatic) BlackReflection.create(LoadedApkReceiverDispatcherStatic.class, (Object) null, false);
    }

    public static LoadedApkReceiverDispatcherContext getWithException(Object caller) {
        return (LoadedApkReceiverDispatcherContext) BlackReflection.create(LoadedApkReceiverDispatcherContext.class, caller, true);
    }

    public static LoadedApkReceiverDispatcherContext get(Object caller) {
        return (LoadedApkReceiverDispatcherContext) BlackReflection.create(LoadedApkReceiverDispatcherContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkReceiverDispatcherContext.class);
    }
}
