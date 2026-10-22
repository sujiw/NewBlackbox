package black.android.app.job;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/BRIJobScheduler.class */
public class BRIJobScheduler {
    public static IJobSchedulerStatic getWithException() {
        return (IJobSchedulerStatic) BlackReflection.create(IJobSchedulerStatic.class, (Object) null, true);
    }

    public static IJobSchedulerStatic get() {
        return (IJobSchedulerStatic) BlackReflection.create(IJobSchedulerStatic.class, (Object) null, false);
    }

    public static IJobSchedulerContext getWithException(Object caller) {
        return (IJobSchedulerContext) BlackReflection.create(IJobSchedulerContext.class, caller, true);
    }

    public static IJobSchedulerContext get(Object caller) {
        return (IJobSchedulerContext) BlackReflection.create(IJobSchedulerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IJobSchedulerContext.class);
    }
}
