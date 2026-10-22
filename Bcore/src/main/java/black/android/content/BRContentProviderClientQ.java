package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRContentProviderClientQ.class */
public class BRContentProviderClientQ {
    public static ContentProviderClientQStatic getWithException() {
        return (ContentProviderClientQStatic) BlackReflection.create(ContentProviderClientQStatic.class, (Object) null, true);
    }

    public static ContentProviderClientQStatic get() {
        return (ContentProviderClientQStatic) BlackReflection.create(ContentProviderClientQStatic.class, (Object) null, false);
    }

    public static ContentProviderClientQContext getWithException(Object caller) {
        return (ContentProviderClientQContext) BlackReflection.create(ContentProviderClientQContext.class, caller, true);
    }

    public static ContentProviderClientQContext get(Object caller) {
        return (ContentProviderClientQContext) BlackReflection.create(ContentProviderClientQContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContentProviderClientQContext.class);
    }
}
