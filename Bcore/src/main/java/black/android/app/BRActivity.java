package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivity.class */
public class BRActivity {
    public static ActivityStatic getWithException() {
        return (ActivityStatic) BlackReflection.create(ActivityStatic.class, (Object) null, true);
    }

    public static ActivityStatic get() {
        return (ActivityStatic) BlackReflection.create(ActivityStatic.class, (Object) null, false);
    }

    public static ActivityContext getWithException(Object caller) {
        return (ActivityContext) BlackReflection.create(ActivityContext.class, caller, true);
    }

    public static ActivityContext get(Object caller) {
        return (ActivityContext) BlackReflection.create(ActivityContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityContext.class);
    }
}
