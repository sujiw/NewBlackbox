package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIClipboard.class */
public class BRIClipboard {
    public static IClipboardStatic getWithException() {
        return (IClipboardStatic) BlackReflection.create(IClipboardStatic.class, (Object) null, true);
    }

    public static IClipboardStatic get() {
        return (IClipboardStatic) BlackReflection.create(IClipboardStatic.class, (Object) null, false);
    }

    public static IClipboardContext getWithException(Object caller) {
        return (IClipboardContext) BlackReflection.create(IClipboardContext.class, caller, true);
    }

    public static IClipboardContext get(Object caller) {
        return (IClipboardContext) BlackReflection.create(IClipboardContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IClipboardContext.class);
    }
}
