package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRMediaRouterStaticKitkat.class */
public class BRMediaRouterStaticKitkat {
    public static MediaRouterStaticKitkatStatic getWithException() {
        return (MediaRouterStaticKitkatStatic) BlackReflection.create(MediaRouterStaticKitkatStatic.class, (Object) null, true);
    }

    public static MediaRouterStaticKitkatStatic get() {
        return (MediaRouterStaticKitkatStatic) BlackReflection.create(MediaRouterStaticKitkatStatic.class, (Object) null, false);
    }

    public static MediaRouterStaticKitkatContext getWithException(Object caller) {
        return (MediaRouterStaticKitkatContext) BlackReflection.create(MediaRouterStaticKitkatContext.class, caller, true);
    }

    public static MediaRouterStaticKitkatContext get(Object caller) {
        return (MediaRouterStaticKitkatContext) BlackReflection.create(MediaRouterStaticKitkatContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(MediaRouterStaticKitkatContext.class);
    }
}
