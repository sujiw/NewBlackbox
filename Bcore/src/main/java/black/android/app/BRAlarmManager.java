package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRAlarmManager.class */
public class BRAlarmManager {
    public static AlarmManagerStatic getWithException() {
        return (AlarmManagerStatic) BlackReflection.create(AlarmManagerStatic.class, (Object) null, true);
    }

    public static AlarmManagerStatic get() {
        return (AlarmManagerStatic) BlackReflection.create(AlarmManagerStatic.class, (Object) null, false);
    }

    public static AlarmManagerContext getWithException(Object caller) {
        return (AlarmManagerContext) BlackReflection.create(AlarmManagerContext.class, caller, true);
    }

    public static AlarmManagerContext get(Object caller) {
        return (AlarmManagerContext) BlackReflection.create(AlarmManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AlarmManagerContext.class);
    }
}
