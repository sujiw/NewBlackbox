package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRContentProviderClient.class */
public class BRContentProviderClient {
    public static ContentProviderClientStatic getWithException() {
        return (ContentProviderClientStatic) BlackReflection.create(ContentProviderClientStatic.class, (Object) null, true);
    }

    public static ContentProviderClientStatic get() {
        return (ContentProviderClientStatic) BlackReflection.create(ContentProviderClientStatic.class, (Object) null, false);
    }

    public static ContentProviderClientContext getWithException(Object caller) {
        return (ContentProviderClientContext) BlackReflection.create(ContentProviderClientContext.class, caller, true);
    }

    public static ContentProviderClientContext get(Object caller) {
        return (ContentProviderClientContext) BlackReflection.create(ContentProviderClientContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContentProviderClientContext.class);
    }
}
