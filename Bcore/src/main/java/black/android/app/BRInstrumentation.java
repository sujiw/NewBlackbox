package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRInstrumentation.class */
public class BRInstrumentation {
    public static InstrumentationStatic getWithException() {
        return (InstrumentationStatic) BlackReflection.create(InstrumentationStatic.class, (Object) null, true);
    }

    public static InstrumentationStatic get() {
        return (InstrumentationStatic) BlackReflection.create(InstrumentationStatic.class, (Object) null, false);
    }

    public static InstrumentationContext getWithException(Object caller) {
        return (InstrumentationContext) BlackReflection.create(InstrumentationContext.class, caller, true);
    }

    public static InstrumentationContext get(Object caller) {
        return (InstrumentationContext) BlackReflection.create(InstrumentationContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(InstrumentationContext.class);
    }
}
