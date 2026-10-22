package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRContentProviderHolderOreo.class */
public class BRContentProviderHolderOreo {
    public static ContentProviderHolderOreoStatic getWithException() {
        return (ContentProviderHolderOreoStatic) BlackReflection.create(ContentProviderHolderOreoStatic.class, (Object) null, true);
    }

    public static ContentProviderHolderOreoStatic get() {
        return (ContentProviderHolderOreoStatic) BlackReflection.create(ContentProviderHolderOreoStatic.class, (Object) null, false);
    }

    public static ContentProviderHolderOreoContext getWithException(Object caller) {
        return (ContentProviderHolderOreoContext) BlackReflection.create(ContentProviderHolderOreoContext.class, caller, true);
    }

    public static ContentProviderHolderOreoContext get(Object caller) {
        return (ContentProviderHolderOreoContext) BlackReflection.create(ContentProviderHolderOreoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContentProviderHolderOreoContext.class);
    }
}
