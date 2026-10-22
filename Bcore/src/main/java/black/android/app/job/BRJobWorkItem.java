package black.android.app.job;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/BRJobWorkItem.class */
public class BRJobWorkItem {
    public static JobWorkItemStatic getWithException() {
        return (JobWorkItemStatic) BlackReflection.create(JobWorkItemStatic.class, (Object) null, true);
    }

    public static JobWorkItemStatic get() {
        return (JobWorkItemStatic) BlackReflection.create(JobWorkItemStatic.class, (Object) null, false);
    }

    public static JobWorkItemContext getWithException(Object caller) {
        return (JobWorkItemContext) BlackReflection.create(JobWorkItemContext.class, caller, true);
    }

    public static JobWorkItemContext get(Object caller) {
        return (JobWorkItemContext) BlackReflection.create(JobWorkItemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(JobWorkItemContext.class);
    }
}
