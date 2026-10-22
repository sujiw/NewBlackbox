package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIAlarmManagerStub.class */
public class BRIAlarmManagerStub {
    public static IAlarmManagerStubStatic getWithException() {
        return (IAlarmManagerStubStatic) BlackReflection.create(IAlarmManagerStubStatic.class, (Object) null, true);
    }

    public static IAlarmManagerStubStatic get() {
        return (IAlarmManagerStubStatic) BlackReflection.create(IAlarmManagerStubStatic.class, (Object) null, false);
    }

    public static IAlarmManagerStubContext getWithException(Object caller) {
        return (IAlarmManagerStubContext) BlackReflection.create(IAlarmManagerStubContext.class, caller, true);
    }

    public static IAlarmManagerStubContext get(Object caller) {
        return (IAlarmManagerStubContext) BlackReflection.create(IAlarmManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAlarmManagerStubContext.class);
    }
}
