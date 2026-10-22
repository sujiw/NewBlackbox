package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIUsageStatsManagerStub.class */
public class BRIUsageStatsManagerStub {
    public static IUsageStatsManagerStubStatic getWithException() {
        return (IUsageStatsManagerStubStatic) BlackReflection.create(IUsageStatsManagerStubStatic.class, (Object) null, true);
    }

    public static IUsageStatsManagerStubStatic get() {
        return (IUsageStatsManagerStubStatic) BlackReflection.create(IUsageStatsManagerStubStatic.class, (Object) null, false);
    }

    public static IUsageStatsManagerStubContext getWithException(Object caller) {
        return (IUsageStatsManagerStubContext) BlackReflection.create(IUsageStatsManagerStubContext.class, caller, true);
    }

    public static IUsageStatsManagerStubContext get(Object caller) {
        return (IUsageStatsManagerStubContext) BlackReflection.create(IUsageStatsManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IUsageStatsManagerStubContext.class);
    }
}
