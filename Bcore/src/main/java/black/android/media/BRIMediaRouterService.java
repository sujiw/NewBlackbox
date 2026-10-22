package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRIMediaRouterService.class */
public class BRIMediaRouterService {
    public static IMediaRouterServiceStatic getWithException() {
        return (IMediaRouterServiceStatic) BlackReflection.create(IMediaRouterServiceStatic.class, (Object) null, true);
    }

    public static IMediaRouterServiceStatic get() {
        return (IMediaRouterServiceStatic) BlackReflection.create(IMediaRouterServiceStatic.class, (Object) null, false);
    }

    public static IMediaRouterServiceContext getWithException(Object caller) {
        return (IMediaRouterServiceContext) BlackReflection.create(IMediaRouterServiceContext.class, caller, true);
    }

    public static IMediaRouterServiceContext get(Object caller) {
        return (IMediaRouterServiceContext) BlackReflection.create(IMediaRouterServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMediaRouterServiceContext.class);
    }
}
