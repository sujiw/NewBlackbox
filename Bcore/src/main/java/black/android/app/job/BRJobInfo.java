package black.android.app.job;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/BRJobInfo.class */
public class BRJobInfo {
    public static JobInfoStatic getWithException() {
        return (JobInfoStatic) BlackReflection.create(JobInfoStatic.class, (Object) null, true);
    }

    public static JobInfoStatic get() {
        return (JobInfoStatic) BlackReflection.create(JobInfoStatic.class, (Object) null, false);
    }

    public static JobInfoContext getWithException(Object caller) {
        return (JobInfoContext) BlackReflection.create(JobInfoContext.class, caller, true);
    }

    public static JobInfoContext get(Object caller) {
        return (JobInfoContext) BlackReflection.create(JobInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(JobInfoContext.class);
    }
}
