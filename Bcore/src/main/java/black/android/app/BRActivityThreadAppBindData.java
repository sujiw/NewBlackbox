package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadAppBindData.class */
public class BRActivityThreadAppBindData {
    public static ActivityThreadAppBindDataStatic getWithException() {
        return (ActivityThreadAppBindDataStatic) BlackReflection.create(ActivityThreadAppBindDataStatic.class, (Object) null, true);
    }

    public static ActivityThreadAppBindDataStatic get() {
        return (ActivityThreadAppBindDataStatic) BlackReflection.create(ActivityThreadAppBindDataStatic.class, (Object) null, false);
    }

    public static ActivityThreadAppBindDataContext getWithException(Object caller) {
        return (ActivityThreadAppBindDataContext) BlackReflection.create(ActivityThreadAppBindDataContext.class, caller, true);
    }

    public static ActivityThreadAppBindDataContext get(Object caller) {
        return (ActivityThreadAppBindDataContext) BlackReflection.create(ActivityThreadAppBindDataContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadAppBindDataContext.class);
    }
}
