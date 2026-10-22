package black.android.app.job;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/BRJobParameters.class */
public class BRJobParameters {
    public static JobParametersStatic getWithException() {
        return (JobParametersStatic) BlackReflection.create(JobParametersStatic.class, (Object) null, true);
    }

    public static JobParametersStatic get() {
        return (JobParametersStatic) BlackReflection.create(JobParametersStatic.class, (Object) null, false);
    }

    public static JobParametersContext getWithException(Object caller) {
        return (JobParametersContext) BlackReflection.create(JobParametersContext.class, caller, true);
    }

    public static JobParametersContext get(Object caller) {
        return (JobParametersContext) BlackReflection.create(JobParametersContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(JobParametersContext.class);
    }
}
