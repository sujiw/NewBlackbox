package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRContentProviderNative.class */
public class BRContentProviderNative {
    public static ContentProviderNativeStatic getWithException() {
        return (ContentProviderNativeStatic) BlackReflection.create(ContentProviderNativeStatic.class, (Object) null, true);
    }

    public static ContentProviderNativeStatic get() {
        return (ContentProviderNativeStatic) BlackReflection.create(ContentProviderNativeStatic.class, (Object) null, false);
    }

    public static ContentProviderNativeContext getWithException(Object caller) {
        return (ContentProviderNativeContext) BlackReflection.create(ContentProviderNativeContext.class, caller, true);
    }

    public static ContentProviderNativeContext get(Object caller) {
        return (ContentProviderNativeContext) BlackReflection.create(ContentProviderNativeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContentProviderNativeContext.class);
    }
}
