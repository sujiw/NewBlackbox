package black.com.android.internal.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/BRReferrerIntent.class */
public class BRReferrerIntent {
    public static ReferrerIntentStatic getWithException() {
        return (ReferrerIntentStatic) BlackReflection.create(ReferrerIntentStatic.class, (Object) null, true);
    }

    public static ReferrerIntentStatic get() {
        return (ReferrerIntentStatic) BlackReflection.create(ReferrerIntentStatic.class, (Object) null, false);
    }

    public static ReferrerIntentContext getWithException(Object caller) {
        return (ReferrerIntentContext) BlackReflection.create(ReferrerIntentContext.class, caller, true);
    }

    public static ReferrerIntentContext get(Object caller) {
        return (ReferrerIntentContext) BlackReflection.create(ReferrerIntentContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ReferrerIntentContext.class);
    }
}
