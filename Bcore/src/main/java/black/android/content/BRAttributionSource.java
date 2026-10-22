package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRAttributionSource.class */
public class BRAttributionSource {
    public static AttributionSourceStatic getWithException() {
        return (AttributionSourceStatic) BlackReflection.create(AttributionSourceStatic.class, (Object) null, true);
    }

    public static AttributionSourceStatic get() {
        return (AttributionSourceStatic) BlackReflection.create(AttributionSourceStatic.class, (Object) null, false);
    }

    public static AttributionSourceContext getWithException(Object caller) {
        return (AttributionSourceContext) BlackReflection.create(AttributionSourceContext.class, caller, true);
    }

    public static AttributionSourceContext get(Object caller) {
        return (AttributionSourceContext) BlackReflection.create(AttributionSourceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AttributionSourceContext.class);
    }
}
