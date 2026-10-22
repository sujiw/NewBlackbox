package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadNMR1.class */
public class BRActivityThreadNMR1 {
    public static ActivityThreadNMR1Static getWithException() {
        return (ActivityThreadNMR1Static) BlackReflection.create(ActivityThreadNMR1Static.class, (Object) null, true);
    }

    public static ActivityThreadNMR1Static get() {
        return (ActivityThreadNMR1Static) BlackReflection.create(ActivityThreadNMR1Static.class, (Object) null, false);
    }

    public static ActivityThreadNMR1Context getWithException(Object caller) {
        return (ActivityThreadNMR1Context) BlackReflection.create(ActivityThreadNMR1Context.class, caller, true);
    }

    public static ActivityThreadNMR1Context get(Object caller) {
        return (ActivityThreadNMR1Context) BlackReflection.create(ActivityThreadNMR1Context.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadNMR1Context.class);
    }
}
