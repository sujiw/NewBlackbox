package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadProviderClientRecordP.class */
public class BRActivityThreadProviderClientRecordP {
    public static ActivityThreadProviderClientRecordPStatic getWithException() {
        return (ActivityThreadProviderClientRecordPStatic) BlackReflection.create(ActivityThreadProviderClientRecordPStatic.class, (Object) null, true);
    }

    public static ActivityThreadProviderClientRecordPStatic get() {
        return (ActivityThreadProviderClientRecordPStatic) BlackReflection.create(ActivityThreadProviderClientRecordPStatic.class, (Object) null, false);
    }

    public static ActivityThreadProviderClientRecordPContext getWithException(Object caller) {
        return (ActivityThreadProviderClientRecordPContext) BlackReflection.create(ActivityThreadProviderClientRecordPContext.class, caller, true);
    }

    public static ActivityThreadProviderClientRecordPContext get(Object caller) {
        return (ActivityThreadProviderClientRecordPContext) BlackReflection.create(ActivityThreadProviderClientRecordPContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadProviderClientRecordPContext.class);
    }
}
