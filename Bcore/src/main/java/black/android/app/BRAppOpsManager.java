package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRAppOpsManager.class */
public class BRAppOpsManager {
    public static AppOpsManagerStatic getWithException() {
        return (AppOpsManagerStatic) BlackReflection.create(AppOpsManagerStatic.class, (Object) null, true);
    }

    public static AppOpsManagerStatic get() {
        return (AppOpsManagerStatic) BlackReflection.create(AppOpsManagerStatic.class, (Object) null, false);
    }

    public static AppOpsManagerContext getWithException(Object caller) {
        return (AppOpsManagerContext) BlackReflection.create(AppOpsManagerContext.class, caller, true);
    }

    public static AppOpsManagerContext get(Object caller) {
        return (AppOpsManagerContext) BlackReflection.create(AppOpsManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AppOpsManagerContext.class);
    }
}
