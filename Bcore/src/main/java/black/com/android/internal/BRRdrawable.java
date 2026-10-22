package black.com.android.internal;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/BRRdrawable.class */
public class BRRdrawable {
    public static RdrawableStatic getWithException() {
        return (RdrawableStatic) BlackReflection.create(RdrawableStatic.class, (Object) null, true);
    }

    public static RdrawableStatic get() {
        return (RdrawableStatic) BlackReflection.create(RdrawableStatic.class, (Object) null, false);
    }

    public static RdrawableContext getWithException(Object caller) {
        return (RdrawableContext) BlackReflection.create(RdrawableContext.class, caller, true);
    }

    public static RdrawableContext get(Object caller) {
        return (RdrawableContext) BlackReflection.create(RdrawableContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RdrawableContext.class);
    }
}
