package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityClient.class */
public class BRActivityClient {
    public static ActivityClientStatic getWithException() {
        return (ActivityClientStatic) BlackReflection.create(ActivityClientStatic.class, (Object) null, true);
    }

    public static ActivityClientStatic get() {
        return (ActivityClientStatic) BlackReflection.create(ActivityClientStatic.class, (Object) null, false);
    }

    public static ActivityClientContext getWithException(Object caller) {
        return (ActivityClientContext) BlackReflection.create(ActivityClientContext.class, caller, true);
    }

    public static ActivityClientContext get(Object caller) {
        return (ActivityClientContext) BlackReflection.create(ActivityClientContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityClientContext.class);
    }
}
