package black.com.android.internal.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/BRNativeLibraryHelper.class */
public class BRNativeLibraryHelper {
    public static NativeLibraryHelperStatic getWithException() {
        return (NativeLibraryHelperStatic) BlackReflection.create(NativeLibraryHelperStatic.class, (Object) null, true);
    }

    public static NativeLibraryHelperStatic get() {
        return (NativeLibraryHelperStatic) BlackReflection.create(NativeLibraryHelperStatic.class, (Object) null, false);
    }

    public static NativeLibraryHelperContext getWithException(Object caller) {
        return (NativeLibraryHelperContext) BlackReflection.create(NativeLibraryHelperContext.class, caller, true);
    }

    public static NativeLibraryHelperContext get(Object caller) {
        return (NativeLibraryHelperContext) BlackReflection.create(NativeLibraryHelperContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NativeLibraryHelperContext.class);
    }
}
