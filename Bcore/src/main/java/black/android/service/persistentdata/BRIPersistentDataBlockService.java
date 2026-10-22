package black.android.service.persistentdata;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/service/persistentdata/BRIPersistentDataBlockService.class */
public class BRIPersistentDataBlockService {
    public static IPersistentDataBlockServiceStatic getWithException() {
        return (IPersistentDataBlockServiceStatic) BlackReflection.create(IPersistentDataBlockServiceStatic.class, (Object) null, true);
    }

    public static IPersistentDataBlockServiceStatic get() {
        return (IPersistentDataBlockServiceStatic) BlackReflection.create(IPersistentDataBlockServiceStatic.class, (Object) null, false);
    }

    public static IPersistentDataBlockServiceContext getWithException(Object caller) {
        return (IPersistentDataBlockServiceContext) BlackReflection.create(IPersistentDataBlockServiceContext.class, caller, true);
    }

    public static IPersistentDataBlockServiceContext get(Object caller) {
        return (IPersistentDataBlockServiceContext) BlackReflection.create(IPersistentDataBlockServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPersistentDataBlockServiceContext.class);
    }
}
