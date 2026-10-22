package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRDisplayAdjustments.class */
public class BRDisplayAdjustments {
    public static DisplayAdjustmentsStatic getWithException() {
        return (DisplayAdjustmentsStatic) BlackReflection.create(DisplayAdjustmentsStatic.class, (Object) null, true);
    }

    public static DisplayAdjustmentsStatic get() {
        return (DisplayAdjustmentsStatic) BlackReflection.create(DisplayAdjustmentsStatic.class, (Object) null, false);
    }

    public static DisplayAdjustmentsContext getWithException(Object caller) {
        return (DisplayAdjustmentsContext) BlackReflection.create(DisplayAdjustmentsContext.class, caller, true);
    }

    public static DisplayAdjustmentsContext get(Object caller) {
        return (DisplayAdjustmentsContext) BlackReflection.create(DisplayAdjustmentsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(DisplayAdjustmentsContext.class);
    }
}
