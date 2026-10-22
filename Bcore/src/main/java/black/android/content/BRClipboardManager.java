package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRClipboardManager.class */
public class BRClipboardManager {
    public static ClipboardManagerStatic getWithException() {
        return (ClipboardManagerStatic) BlackReflection.create(ClipboardManagerStatic.class, (Object) null, true);
    }

    public static ClipboardManagerStatic get() {
        return (ClipboardManagerStatic) BlackReflection.create(ClipboardManagerStatic.class, (Object) null, false);
    }

    public static ClipboardManagerContext getWithException(Object caller) {
        return (ClipboardManagerContext) BlackReflection.create(ClipboardManagerContext.class, caller, true);
    }

    public static ClipboardManagerContext get(Object caller) {
        return (ClipboardManagerContext) BlackReflection.create(ClipboardManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ClipboardManagerContext.class);
    }
}
