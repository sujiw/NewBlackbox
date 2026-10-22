package black.android.hardware.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/location/BRIContextHubServiceStub.class */
public class BRIContextHubServiceStub {
    public static IContextHubServiceStubStatic getWithException() {
        return (IContextHubServiceStubStatic) BlackReflection.create(IContextHubServiceStubStatic.class, (Object) null, true);
    }

    public static IContextHubServiceStubStatic get() {
        return (IContextHubServiceStubStatic) BlackReflection.create(IContextHubServiceStubStatic.class, (Object) null, false);
    }

    public static IContextHubServiceStubContext getWithException(Object caller) {
        return (IContextHubServiceStubContext) BlackReflection.create(IContextHubServiceStubContext.class, caller, true);
    }

    public static IContextHubServiceStubContext get(Object caller) {
        return (IContextHubServiceStubContext) BlackReflection.create(IContextHubServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IContextHubServiceStubContext.class);
    }
}
