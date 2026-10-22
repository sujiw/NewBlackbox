package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityTaskManager.class */
public class BRActivityTaskManager {
    public static ActivityTaskManagerStatic getWithException() {
        return (ActivityTaskManagerStatic) BlackReflection.create(ActivityTaskManagerStatic.class, (Object) null, true);
    }

    public static ActivityTaskManagerStatic get() {
        return (ActivityTaskManagerStatic) BlackReflection.create(ActivityTaskManagerStatic.class, (Object) null, false);
    }

    public static ActivityTaskManagerContext getWithException(Object caller) {
        return (ActivityTaskManagerContext) BlackReflection.create(ActivityTaskManagerContext.class, caller, true);
    }

    public static ActivityTaskManagerContext get(Object caller) {
        return (ActivityTaskManagerContext) BlackReflection.create(ActivityTaskManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityTaskManagerContext.class);
    }
}
