package black.com.android.internal;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/BRRstyleable.class */
public class BRRstyleable {
    public static RstyleableStatic getWithException() {
        return (RstyleableStatic) BlackReflection.create(RstyleableStatic.class, (Object) null, true);
    }

    public static RstyleableStatic get() {
        return (RstyleableStatic) BlackReflection.create(RstyleableStatic.class, (Object) null, false);
    }

    public static RstyleableContext getWithException(Object caller) {
        return (RstyleableContext) BlackReflection.create(RstyleableContext.class, caller, true);
    }

    public static RstyleableContext get(Object caller) {
        return (RstyleableContext) BlackReflection.create(RstyleableContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RstyleableContext.class);
    }
}
