package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRMediaRouterStatic.class */
public class BRMediaRouterStatic {
    public static MediaRouterStaticStatic getWithException() {
        return (MediaRouterStaticStatic) BlackReflection.create(MediaRouterStaticStatic.class, (Object) null, true);
    }

    public static MediaRouterStaticStatic get() {
        return (MediaRouterStaticStatic) BlackReflection.create(MediaRouterStaticStatic.class, (Object) null, false);
    }

    public static MediaRouterStaticContext getWithException(Object caller) {
        return (MediaRouterStaticContext) BlackReflection.create(MediaRouterStaticContext.class, caller, true);
    }

    public static MediaRouterStaticContext get(Object caller) {
        return (MediaRouterStaticContext) BlackReflection.create(MediaRouterStaticContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(MediaRouterStaticContext.class);
    }
}
