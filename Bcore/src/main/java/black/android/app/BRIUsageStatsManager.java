package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIUsageStatsManager.class */
public class BRIUsageStatsManager {
    public static IUsageStatsManagerStatic getWithException() {
        return (IUsageStatsManagerStatic) BlackReflection.create(IUsageStatsManagerStatic.class, (Object) null, true);
    }

    public static IUsageStatsManagerStatic get() {
        return (IUsageStatsManagerStatic) BlackReflection.create(IUsageStatsManagerStatic.class, (Object) null, false);
    }

    public static IUsageStatsManagerContext getWithException(Object caller) {
        return (IUsageStatsManagerContext) BlackReflection.create(IUsageStatsManagerContext.class, caller, true);
    }

    public static IUsageStatsManagerContext get(Object caller) {
        return (IUsageStatsManagerContext) BlackReflection.create(IUsageStatsManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IUsageStatsManagerContext.class);
    }
}
