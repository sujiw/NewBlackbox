package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRINetworkManagementServiceStub.class */
public class BRINetworkManagementServiceStub {
    public static INetworkManagementServiceStubStatic getWithException() {
        return (INetworkManagementServiceStubStatic) BlackReflection.create(INetworkManagementServiceStubStatic.class, (Object) null, true);
    }

    public static INetworkManagementServiceStubStatic get() {
        return (INetworkManagementServiceStubStatic) BlackReflection.create(INetworkManagementServiceStubStatic.class, (Object) null, false);
    }

    public static INetworkManagementServiceStubContext getWithException(Object caller) {
        return (INetworkManagementServiceStubContext) BlackReflection.create(INetworkManagementServiceStubContext.class, caller, true);
    }

    public static INetworkManagementServiceStubContext get(Object caller) {
        return (INetworkManagementServiceStubContext) BlackReflection.create(INetworkManagementServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(INetworkManagementServiceStubContext.class);
    }
}
