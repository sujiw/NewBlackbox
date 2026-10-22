package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIContentProvider.class */
public class BRIContentProvider {
    public static IContentProviderStatic getWithException() {
        return (IContentProviderStatic) BlackReflection.create(IContentProviderStatic.class, (Object) null, true);
    }

    public static IContentProviderStatic get() {
        return (IContentProviderStatic) BlackReflection.create(IContentProviderStatic.class, (Object) null, false);
    }

    public static IContentProviderContext getWithException(Object caller) {
        return (IContentProviderContext) BlackReflection.create(IContentProviderContext.class, caller, true);
    }

    public static IContentProviderContext get(Object caller) {
        return (IContentProviderContext) BlackReflection.create(IContentProviderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IContentProviderContext.class);
    }
}
