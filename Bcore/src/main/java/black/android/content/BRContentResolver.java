package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRContentResolver.class */
public class BRContentResolver {
    public static ContentResolverStatic getWithException() {
        return (ContentResolverStatic) BlackReflection.create(ContentResolverStatic.class, (Object) null, true);
    }

    public static ContentResolverStatic get() {
        return (ContentResolverStatic) BlackReflection.create(ContentResolverStatic.class, (Object) null, false);
    }

    public static ContentResolverContext getWithException(Object caller) {
        return (ContentResolverContext) BlackReflection.create(ContentResolverContext.class, caller, true);
    }

    public static ContentResolverContext get(Object caller) {
        return (ContentResolverContext) BlackReflection.create(ContentResolverContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ContentResolverContext.class);
    }
}
