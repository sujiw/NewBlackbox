package black.com.android.internal.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/BRIBatteryStats.class */
public class BRIBatteryStats {
    public static IBatteryStatsStatic getWithException() {
        return (IBatteryStatsStatic) BlackReflection.create(IBatteryStatsStatic.class, (Object) null, true);
    }

    public static IBatteryStatsStatic get() {
        return (IBatteryStatsStatic) BlackReflection.create(IBatteryStatsStatic.class, (Object) null, false);
    }

    public static IBatteryStatsContext getWithException(Object caller) {
        return (IBatteryStatsContext) BlackReflection.create(IBatteryStatsContext.class, caller, true);
    }

    public static IBatteryStatsContext get(Object caller) {
        return (IBatteryStatsContext) BlackReflection.create(IBatteryStatsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IBatteryStatsContext.class);
    }
}
