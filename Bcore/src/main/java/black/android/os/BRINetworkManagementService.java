package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRINetworkManagementService.class */
public class BRINetworkManagementService {
    public static INetworkManagementServiceStatic getWithException() {
        return (INetworkManagementServiceStatic) BlackReflection.create(INetworkManagementServiceStatic.class, (Object) null, true);
    }

    public static INetworkManagementServiceStatic get() {
        return (INetworkManagementServiceStatic) BlackReflection.create(INetworkManagementServiceStatic.class, (Object) null, false);
    }

    public static INetworkManagementServiceContext getWithException(Object caller) {
        return (INetworkManagementServiceContext) BlackReflection.create(INetworkManagementServiceContext.class, caller, true);
    }

    public static INetworkManagementServiceContext get(Object caller) {
        return (INetworkManagementServiceContext) BlackReflection.create(INetworkManagementServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(INetworkManagementServiceContext.class);
    }
}
