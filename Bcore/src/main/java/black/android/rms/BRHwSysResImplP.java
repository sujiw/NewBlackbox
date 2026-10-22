package black.android.rms;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/rms/BRHwSysResImplP.class */
public class BRHwSysResImplP {
    public static HwSysResImplPStatic getWithException() {
        return (HwSysResImplPStatic) BlackReflection.create(HwSysResImplPStatic.class, (Object) null, true);
    }

    public static HwSysResImplPStatic get() {
        return (HwSysResImplPStatic) BlackReflection.create(HwSysResImplPStatic.class, (Object) null, false);
    }

    public static HwSysResImplPContext getWithException(Object caller) {
        return (HwSysResImplPContext) BlackReflection.create(HwSysResImplPContext.class, caller, true);
    }

    public static HwSysResImplPContext get(Object caller) {
        return (HwSysResImplPContext) BlackReflection.create(HwSysResImplPContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(HwSysResImplPContext.class);
    }
}
