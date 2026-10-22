package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIntentFilter.class */
public class BRIntentFilter {
    public static IntentFilterStatic getWithException() {
        return (IntentFilterStatic) BlackReflection.create(IntentFilterStatic.class, (Object) null, true);
    }

    public static IntentFilterStatic get() {
        return (IntentFilterStatic) BlackReflection.create(IntentFilterStatic.class, (Object) null, false);
    }

    public static IntentFilterContext getWithException(Object caller) {
        return (IntentFilterContext) BlackReflection.create(IntentFilterContext.class, caller, true);
    }

    public static IntentFilterContext get(Object caller) {
        return (IntentFilterContext) BlackReflection.create(IntentFilterContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IntentFilterContext.class);
    }
}
