package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRAttributionSourceState.class */
public class BRAttributionSourceState {
    public static AttributionSourceStateStatic getWithException() {
        return (AttributionSourceStateStatic) BlackReflection.create(AttributionSourceStateStatic.class, (Object) null, true);
    }

    public static AttributionSourceStateStatic get() {
        return (AttributionSourceStateStatic) BlackReflection.create(AttributionSourceStateStatic.class, (Object) null, false);
    }

    public static AttributionSourceStateContext getWithException(Object caller) {
        return (AttributionSourceStateContext) BlackReflection.create(AttributionSourceStateContext.class, caller, true);
    }

    public static AttributionSourceStateContext get(Object caller) {
        return (AttributionSourceStateContext) BlackReflection.create(AttributionSourceStateContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AttributionSourceStateContext.class);
    }
}
