package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRHardwareRenderer.class */
public class BRHardwareRenderer {
    public static HardwareRendererStatic getWithException() {
        return (HardwareRendererStatic) BlackReflection.create(HardwareRendererStatic.class, (Object) null, true);
    }

    public static HardwareRendererStatic get() {
        return (HardwareRendererStatic) BlackReflection.create(HardwareRendererStatic.class, (Object) null, false);
    }

    public static HardwareRendererContext getWithException(Object caller) {
        return (HardwareRendererContext) BlackReflection.create(HardwareRendererContext.class, caller, true);
    }

    public static HardwareRendererContext get(Object caller) {
        return (HardwareRendererContext) BlackReflection.create(HardwareRendererContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(HardwareRendererContext.class);
    }
}
