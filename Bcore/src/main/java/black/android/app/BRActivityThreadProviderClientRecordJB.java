package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadProviderClientRecordJB.class */
public class BRActivityThreadProviderClientRecordJB {
    public static ActivityThreadProviderClientRecordJBStatic getWithException() {
        return (ActivityThreadProviderClientRecordJBStatic) BlackReflection.create(ActivityThreadProviderClientRecordJBStatic.class, (Object) null, true);
    }

    public static ActivityThreadProviderClientRecordJBStatic get() {
        return (ActivityThreadProviderClientRecordJBStatic) BlackReflection.create(ActivityThreadProviderClientRecordJBStatic.class, (Object) null, false);
    }

    public static ActivityThreadProviderClientRecordJBContext getWithException(Object caller) {
        return (ActivityThreadProviderClientRecordJBContext) BlackReflection.create(ActivityThreadProviderClientRecordJBContext.class, caller, true);
    }

    public static ActivityThreadProviderClientRecordJBContext get(Object caller) {
        return (ActivityThreadProviderClientRecordJBContext) BlackReflection.create(ActivityThreadProviderClientRecordJBContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadProviderClientRecordJBContext.class);
    }
}
