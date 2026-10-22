package black.com.android.internal.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/BRNativeLibraryHelperHandle.class */
public class BRNativeLibraryHelperHandle {
    public static NativeLibraryHelperHandleStatic getWithException() {
        return (NativeLibraryHelperHandleStatic) BlackReflection.create(NativeLibraryHelperHandleStatic.class, (Object) null, true);
    }

    public static NativeLibraryHelperHandleStatic get() {
        return (NativeLibraryHelperHandleStatic) BlackReflection.create(NativeLibraryHelperHandleStatic.class, (Object) null, false);
    }

    public static NativeLibraryHelperHandleContext getWithException(Object caller) {
        return (NativeLibraryHelperHandleContext) BlackReflection.create(NativeLibraryHelperHandleContext.class, caller, true);
    }

    public static NativeLibraryHelperHandleContext get(Object caller) {
        return (NativeLibraryHelperHandleContext) BlackReflection.create(NativeLibraryHelperHandleContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NativeLibraryHelperHandleContext.class);
    }
}
