package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityThreadProviderKeyJBMR1.class */
public class BRActivityThreadProviderKeyJBMR1 {
    public static ActivityThreadProviderKeyJBMR1Static getWithException() {
        return (ActivityThreadProviderKeyJBMR1Static) BlackReflection.create(ActivityThreadProviderKeyJBMR1Static.class, (Object) null, true);
    }

    public static ActivityThreadProviderKeyJBMR1Static get() {
        return (ActivityThreadProviderKeyJBMR1Static) BlackReflection.create(ActivityThreadProviderKeyJBMR1Static.class, (Object) null, false);
    }

    public static ActivityThreadProviderKeyJBMR1Context getWithException(Object caller) {
        return (ActivityThreadProviderKeyJBMR1Context) BlackReflection.create(ActivityThreadProviderKeyJBMR1Context.class, caller, true);
    }

    public static ActivityThreadProviderKeyJBMR1Context get(Object caller) {
        return (ActivityThreadProviderKeyJBMR1Context) BlackReflection.create(ActivityThreadProviderKeyJBMR1Context.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityThreadProviderKeyJBMR1Context.class);
    }
}
