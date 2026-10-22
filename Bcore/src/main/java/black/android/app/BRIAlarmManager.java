package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIAlarmManager.class */
public class BRIAlarmManager {
    public static IAlarmManagerStatic getWithException() {
        return (IAlarmManagerStatic) BlackReflection.create(IAlarmManagerStatic.class, (Object) null, true);
    }

    public static IAlarmManagerStatic get() {
        return (IAlarmManagerStatic) BlackReflection.create(IAlarmManagerStatic.class, (Object) null, false);
    }

    public static IAlarmManagerContext getWithException(Object caller) {
        return (IAlarmManagerContext) BlackReflection.create(IAlarmManagerContext.class, caller, true);
    }

    public static IAlarmManagerContext get(Object caller) {
        return (IAlarmManagerContext) BlackReflection.create(IAlarmManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAlarmManagerContext.class);
    }
}
