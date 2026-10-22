package black.android.hardware.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/location/BRIContextHubService.class */
public class BRIContextHubService {
    public static IContextHubServiceStatic getWithException() {
        return (IContextHubServiceStatic) BlackReflection.create(IContextHubServiceStatic.class, (Object) null, true);
    }

    public static IContextHubServiceStatic get() {
        return (IContextHubServiceStatic) BlackReflection.create(IContextHubServiceStatic.class, (Object) null, false);
    }

    public static IContextHubServiceContext getWithException(Object caller) {
        return (IContextHubServiceContext) BlackReflection.create(IContextHubServiceContext.class, caller, true);
    }

    public static IContextHubServiceContext get(Object caller) {
        return (IContextHubServiceContext) BlackReflection.create(IContextHubServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IContextHubServiceContext.class);
    }
}
