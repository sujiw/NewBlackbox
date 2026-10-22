package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadActivityClientRecord.class */
public class BRActivityThreadActivityClientRecord {
    public static ActivityThreadActivityClientRecordStatic getWithException() {
        return (ActivityThreadActivityClientRecordStatic) BlackReflection.create(ActivityThreadActivityClientRecordStatic.class, (Object) null, true);
    }

    public static ActivityThreadActivityClientRecordStatic get() {
        return (ActivityThreadActivityClientRecordStatic) BlackReflection.create(ActivityThreadActivityClientRecordStatic.class, (Object) null, false);
    }

    public static ActivityThreadActivityClientRecordContext getWithException(Object caller) {
        return (ActivityThreadActivityClientRecordContext) BlackReflection.create(ActivityThreadActivityClientRecordContext.class, caller, true);
    }

    public static ActivityThreadActivityClientRecordContext get(Object caller) {
        return (ActivityThreadActivityClientRecordContext) BlackReflection.create(ActivityThreadActivityClientRecordContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadActivityClientRecordContext.class);
    }
}
