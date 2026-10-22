package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadCreateServiceData.class */
public class BRActivityThreadCreateServiceData {
    public static ActivityThreadCreateServiceDataStatic getWithException() {
        return (ActivityThreadCreateServiceDataStatic) BlackReflection.create(ActivityThreadCreateServiceDataStatic.class, (Object) null, true);
    }

    public static ActivityThreadCreateServiceDataStatic get() {
        return (ActivityThreadCreateServiceDataStatic) BlackReflection.create(ActivityThreadCreateServiceDataStatic.class, (Object) null, false);
    }

    public static ActivityThreadCreateServiceDataContext getWithException(Object caller) {
        return (ActivityThreadCreateServiceDataContext) BlackReflection.create(ActivityThreadCreateServiceDataContext.class, caller, true);
    }

    public static ActivityThreadCreateServiceDataContext get(Object caller) {
        return (ActivityThreadCreateServiceDataContext) BlackReflection.create(ActivityThreadCreateServiceDataContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadCreateServiceDataContext.class);
    }
}
