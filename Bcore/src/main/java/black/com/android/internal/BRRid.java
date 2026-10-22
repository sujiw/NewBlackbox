package black.com.android.internal;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/BRRid.class */
public class BRRid {
    public static RidStatic getWithException() {
        return (RidStatic) BlackReflection.create(RidStatic.class, (Object) null, true);
    }

    public static RidStatic get() {
        return (RidStatic) BlackReflection.create(RidStatic.class, (Object) null, false);
    }

    public static RidContext getWithException(Object caller) {
        return (RidContext) BlackReflection.create(RidContext.class, caller, true);
    }

    public static RidContext get(Object caller) {
        return (RidContext) BlackReflection.create(RidContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RidContext.class);
    }
}
