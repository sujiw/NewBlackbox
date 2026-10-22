package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRLoadedApkReceiverDispatcherInnerReceiver.class */
public class BRLoadedApkReceiverDispatcherInnerReceiver {
    public static LoadedApkReceiverDispatcherInnerReceiverStatic getWithException() {
        return (LoadedApkReceiverDispatcherInnerReceiverStatic) BlackReflection.create(LoadedApkReceiverDispatcherInnerReceiverStatic.class, (Object) null, true);
    }

    public static LoadedApkReceiverDispatcherInnerReceiverStatic get() {
        return (LoadedApkReceiverDispatcherInnerReceiverStatic) BlackReflection.create(LoadedApkReceiverDispatcherInnerReceiverStatic.class, (Object) null, false);
    }

    public static LoadedApkReceiverDispatcherInnerReceiverContext getWithException(Object caller) {
        return (LoadedApkReceiverDispatcherInnerReceiverContext) BlackReflection.create(LoadedApkReceiverDispatcherInnerReceiverContext.class, caller, true);
    }

    public static LoadedApkReceiverDispatcherInnerReceiverContext get(Object caller) {
        return (LoadedApkReceiverDispatcherInnerReceiverContext) BlackReflection.create(LoadedApkReceiverDispatcherInnerReceiverContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LoadedApkReceiverDispatcherInnerReceiverContext.class);
    }
}
