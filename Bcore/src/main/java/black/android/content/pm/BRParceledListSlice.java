package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRParceledListSlice.class */
public class BRParceledListSlice {
    public static ParceledListSliceStatic getWithException() {
        return (ParceledListSliceStatic) BlackReflection.create(ParceledListSliceStatic.class, (Object) null, true);
    }

    public static ParceledListSliceStatic get() {
        return (ParceledListSliceStatic) BlackReflection.create(ParceledListSliceStatic.class, (Object) null, false);
    }

    public static ParceledListSliceContext getWithException(Object caller) {
        return (ParceledListSliceContext) BlackReflection.create(ParceledListSliceContext.class, caller, true);
    }

    public static ParceledListSliceContext get(Object caller) {
        return (ParceledListSliceContext) BlackReflection.create(ParceledListSliceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ParceledListSliceContext.class);
    }
}
