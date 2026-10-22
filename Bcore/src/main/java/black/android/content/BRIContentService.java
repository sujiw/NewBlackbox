package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRIContentService.class */
public class BRIContentService {
    public static IContentServiceStatic getWithException() {
        return (IContentServiceStatic) BlackReflection.create(IContentServiceStatic.class, (Object) null, true);
    }

    public static IContentServiceStatic get() {
        return (IContentServiceStatic) BlackReflection.create(IContentServiceStatic.class, (Object) null, false);
    }

    public static IContentServiceContext getWithException(Object caller) {
        return (IContentServiceContext) BlackReflection.create(IContentServiceContext.class, caller, true);
    }

    public static IContentServiceContext get(Object caller) {
        return (IContentServiceContext) BlackReflection.create(IContentServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IContentServiceContext.class);
    }
}
