package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRMediaRouter.class */
public class BRMediaRouter {
    public static MediaRouterStatic getWithException() {
        return (MediaRouterStatic) BlackReflection.create(MediaRouterStatic.class, (Object) null, true);
    }

    public static MediaRouterStatic get() {
        return (MediaRouterStatic) BlackReflection.create(MediaRouterStatic.class, (Object) null, false);
    }

    public static MediaRouterContext getWithException(Object caller) {
        return (MediaRouterContext) BlackReflection.create(MediaRouterContext.class, caller, true);
    }

    public static MediaRouterContext get(Object caller) {
        return (MediaRouterContext) BlackReflection.create(MediaRouterContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(MediaRouterContext.class);
    }
}
