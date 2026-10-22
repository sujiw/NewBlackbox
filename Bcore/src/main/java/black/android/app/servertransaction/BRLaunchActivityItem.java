package black.android.app.servertransaction;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/BRLaunchActivityItem.class */
public class BRLaunchActivityItem {
    public static LaunchActivityItemStatic getWithException() {
        return (LaunchActivityItemStatic) BlackReflection.create(LaunchActivityItemStatic.class, (Object) null, true);
    }

    public static LaunchActivityItemStatic get() {
        return (LaunchActivityItemStatic) BlackReflection.create(LaunchActivityItemStatic.class, (Object) null, false);
    }

    public static LaunchActivityItemContext getWithException(Object caller) {
        return (LaunchActivityItemContext) BlackReflection.create(LaunchActivityItemContext.class, caller, true);
    }

    public static LaunchActivityItemContext get(Object caller) {
        return (LaunchActivityItemContext) BlackReflection.create(LaunchActivityItemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LaunchActivityItemContext.class);
    }
}
