package black.android.service.persistentdata;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/service/persistentdata/BRIPersistentDataBlockServiceStub.class */
public class BRIPersistentDataBlockServiceStub {
    public static IPersistentDataBlockServiceStubStatic getWithException() {
        return (IPersistentDataBlockServiceStubStatic) BlackReflection.create(IPersistentDataBlockServiceStubStatic.class, (Object) null, true);
    }

    public static IPersistentDataBlockServiceStubStatic get() {
        return (IPersistentDataBlockServiceStubStatic) BlackReflection.create(IPersistentDataBlockServiceStubStatic.class, (Object) null, false);
    }

    public static IPersistentDataBlockServiceStubContext getWithException(Object caller) {
        return (IPersistentDataBlockServiceStubContext) BlackReflection.create(IPersistentDataBlockServiceStubContext.class, caller, true);
    }

    public static IPersistentDataBlockServiceStubContext get(Object caller) {
        return (IPersistentDataBlockServiceStubContext) BlackReflection.create(IPersistentDataBlockServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPersistentDataBlockServiceStubContext.class);
    }
}
