package black.android.ddm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/ddm/BRDdmHandleAppName.class */
public class BRDdmHandleAppName {
    public static DdmHandleAppNameStatic getWithException() {
        return (DdmHandleAppNameStatic) BlackReflection.create(DdmHandleAppNameStatic.class, (Object) null, true);
    }

    public static DdmHandleAppNameStatic get() {
        return (DdmHandleAppNameStatic) BlackReflection.create(DdmHandleAppNameStatic.class, (Object) null, false);
    }

    public static DdmHandleAppNameContext getWithException(Object caller) {
        return (DdmHandleAppNameContext) BlackReflection.create(DdmHandleAppNameContext.class, caller, true);
    }

    public static DdmHandleAppNameContext get(Object caller) {
        return (DdmHandleAppNameContext) BlackReflection.create(DdmHandleAppNameContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(DdmHandleAppNameContext.class);
    }
}
