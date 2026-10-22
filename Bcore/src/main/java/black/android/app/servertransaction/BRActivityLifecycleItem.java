package black.android.app.servertransaction;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/BRActivityLifecycleItem.class */
public class BRActivityLifecycleItem {
    public static ActivityLifecycleItemStatic getWithException() {
        return (ActivityLifecycleItemStatic) BlackReflection.create(ActivityLifecycleItemStatic.class, (Object) null, true);
    }

    public static ActivityLifecycleItemStatic get() {
        return (ActivityLifecycleItemStatic) BlackReflection.create(ActivityLifecycleItemStatic.class, (Object) null, false);
    }

    public static ActivityLifecycleItemContext getWithException(Object caller) {
        return (ActivityLifecycleItemContext) BlackReflection.create(ActivityLifecycleItemContext.class, caller, true);
    }

    public static ActivityLifecycleItemContext get(Object caller) {
        return (ActivityLifecycleItemContext) BlackReflection.create(ActivityLifecycleItemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityLifecycleItemContext.class);
    }
}
