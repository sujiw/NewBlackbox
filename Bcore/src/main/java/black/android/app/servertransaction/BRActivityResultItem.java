package black.android.app.servertransaction;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/BRActivityResultItem.class */
public class BRActivityResultItem {
    public static ActivityResultItemStatic getWithException() {
        return (ActivityResultItemStatic) BlackReflection.create(ActivityResultItemStatic.class, (Object) null, true);
    }

    public static ActivityResultItemStatic get() {
        return (ActivityResultItemStatic) BlackReflection.create(ActivityResultItemStatic.class, (Object) null, false);
    }

    public static ActivityResultItemContext getWithException(Object caller) {
        return (ActivityResultItemContext) BlackReflection.create(ActivityResultItemContext.class, caller, true);
    }

    public static ActivityResultItemContext get(Object caller) {
        return (ActivityResultItemContext) BlackReflection.create(ActivityResultItemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityResultItemContext.class);
    }
}
