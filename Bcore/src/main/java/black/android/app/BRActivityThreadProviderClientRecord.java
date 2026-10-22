package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadProviderClientRecord.class */
public class BRActivityThreadProviderClientRecord {
    public static ActivityThreadProviderClientRecordStatic getWithException() {
        return (ActivityThreadProviderClientRecordStatic) BlackReflection.create(ActivityThreadProviderClientRecordStatic.class, (Object) null, true);
    }

    public static ActivityThreadProviderClientRecordStatic get() {
        return (ActivityThreadProviderClientRecordStatic) BlackReflection.create(ActivityThreadProviderClientRecordStatic.class, (Object) null, false);
    }

    public static ActivityThreadProviderClientRecordContext getWithException(Object caller) {
        return (ActivityThreadProviderClientRecordContext) BlackReflection.create(ActivityThreadProviderClientRecordContext.class, caller, true);
    }

    public static ActivityThreadProviderClientRecordContext get(Object caller) {
        return (ActivityThreadProviderClientRecordContext) BlackReflection.create(ActivityThreadProviderClientRecordContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadProviderClientRecordContext.class);
    }
}
