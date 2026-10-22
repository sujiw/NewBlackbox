package black.android.app.job;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/BRIJobSchedulerStub.class */
public class BRIJobSchedulerStub {
    public static IJobSchedulerStubStatic getWithException() {
        return (IJobSchedulerStubStatic) BlackReflection.create(IJobSchedulerStubStatic.class, (Object) null, true);
    }

    public static IJobSchedulerStubStatic get() {
        return (IJobSchedulerStubStatic) BlackReflection.create(IJobSchedulerStubStatic.class, (Object) null, false);
    }

    public static IJobSchedulerStubContext getWithException(Object caller) {
        return (IJobSchedulerStubContext) BlackReflection.create(IJobSchedulerStubContext.class, caller, true);
    }

    public static IJobSchedulerStubContext get(Object caller) {
        return (IJobSchedulerStubContext) BlackReflection.create(IJobSchedulerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IJobSchedulerStubContext.class);
    }
}
