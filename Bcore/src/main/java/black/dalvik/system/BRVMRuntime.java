package black.dalvik.system;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/dalvik/system/BRVMRuntime.class */
public class BRVMRuntime {
    public static VMRuntimeStatic getWithException() {
        return (VMRuntimeStatic) BlackReflection.create(VMRuntimeStatic.class, (Object) null, true);
    }

    public static VMRuntimeStatic get() {
        return (VMRuntimeStatic) BlackReflection.create(VMRuntimeStatic.class, (Object) null, false);
    }

    public static VMRuntimeContext getWithException(Object caller) {
        return (VMRuntimeContext) BlackReflection.create(VMRuntimeContext.class, caller, true);
    }

    public static VMRuntimeContext get(Object caller) {
        return (VMRuntimeContext) BlackReflection.create(VMRuntimeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(VMRuntimeContext.class);
    }
}
