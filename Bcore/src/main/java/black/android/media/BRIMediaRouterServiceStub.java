package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRIMediaRouterServiceStub.class */
public class BRIMediaRouterServiceStub {
    public static IMediaRouterServiceStubStatic getWithException() {
        return (IMediaRouterServiceStubStatic) BlackReflection.create(IMediaRouterServiceStubStatic.class, (Object) null, true);
    }

    public static IMediaRouterServiceStubStatic get() {
        return (IMediaRouterServiceStubStatic) BlackReflection.create(IMediaRouterServiceStubStatic.class, (Object) null, false);
    }

    public static IMediaRouterServiceStubContext getWithException(Object caller) {
        return (IMediaRouterServiceStubContext) BlackReflection.create(IMediaRouterServiceStubContext.class, caller, true);
    }

    public static IMediaRouterServiceStubContext get(Object caller) {
        return (IMediaRouterServiceStubContext) BlackReflection.create(IMediaRouterServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMediaRouterServiceStubContext.class);
    }
}
