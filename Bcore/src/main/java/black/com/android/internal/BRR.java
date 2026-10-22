package black.com.android.internal;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/BRR.class */
public class BRR {
    public static RStatic getWithException() {
        return (RStatic) BlackReflection.create(RStatic.class, (Object) null, true);
    }

    public static RStatic get() {
        return (RStatic) BlackReflection.create(RStatic.class, (Object) null, false);
    }

    public static RContext getWithException(Object caller) {
        return (RContext) BlackReflection.create(RContext.class, caller, true);
    }

    public static RContext get(Object caller) {
        return (RContext) BlackReflection.create(RContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RContext.class);
    }
}
