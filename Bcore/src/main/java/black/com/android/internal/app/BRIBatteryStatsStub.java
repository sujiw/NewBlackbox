package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRIBatteryStatsStub.class */
public class BRIBatteryStatsStub {
    public static IBatteryStatsStubStatic getWithException() {
        return (IBatteryStatsStubStatic) BlackReflection.create(IBatteryStatsStubStatic.class, (Object) null, true);
    }

    public static IBatteryStatsStubStatic get() {
        return (IBatteryStatsStubStatic) BlackReflection.create(IBatteryStatsStubStatic.class, (Object) null, false);
    }

    public static IBatteryStatsStubContext getWithException(Object caller) {
        return (IBatteryStatsStubContext) BlackReflection.create(IBatteryStatsStubContext.class, caller, true);
    }

    public static IBatteryStatsStubContext get(Object caller) {
        return (IBatteryStatsStubContext) BlackReflection.create(IBatteryStatsStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IBatteryStatsStubContext.class);
    }
}
