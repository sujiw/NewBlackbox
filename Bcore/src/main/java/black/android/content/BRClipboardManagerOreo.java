package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRClipboardManagerOreo.class */
public class BRClipboardManagerOreo {
    public static ClipboardManagerOreoStatic getWithException() {
        return (ClipboardManagerOreoStatic) BlackReflection.create(ClipboardManagerOreoStatic.class, (Object) null, true);
    }

    public static ClipboardManagerOreoStatic get() {
        return (ClipboardManagerOreoStatic) BlackReflection.create(ClipboardManagerOreoStatic.class, (Object) null, false);
    }

    public static ClipboardManagerOreoContext getWithException(Object caller) {
        return (ClipboardManagerOreoContext) BlackReflection.create(ClipboardManagerOreoContext.class, caller, true);
    }

    public static ClipboardManagerOreoContext get(Object caller) {
        return (ClipboardManagerOreoContext) BlackReflection.create(ClipboardManagerOreoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ClipboardManagerOreoContext.class);
    }
}
