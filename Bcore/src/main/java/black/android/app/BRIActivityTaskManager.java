package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityTaskManager.class */
public class BRIActivityTaskManager {
    public static IActivityTaskManagerStatic getWithException() {
        return (IActivityTaskManagerStatic) BlackReflection.create(IActivityTaskManagerStatic.class, (Object) null, true);
    }

    public static IActivityTaskManagerStatic get() {
        return (IActivityTaskManagerStatic) BlackReflection.create(IActivityTaskManagerStatic.class, (Object) null, false);
    }

    public static IActivityTaskManagerContext getWithException(Object caller) {
        return (IActivityTaskManagerContext) BlackReflection.create(IActivityTaskManagerContext.class, caller, true);
    }

    public static IActivityTaskManagerContext get(Object caller) {
        return (IActivityTaskManagerContext) BlackReflection.create(IActivityTaskManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityTaskManagerContext.class);
    }
}
