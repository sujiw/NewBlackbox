package black.android.os.mount;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/mount/BRIMountService.class */
public class BRIMountService {
    public static IMountServiceStatic getWithException() {
        return (IMountServiceStatic) BlackReflection.create(IMountServiceStatic.class, (Object) null, true);
    }

    public static IMountServiceStatic get() {
        return (IMountServiceStatic) BlackReflection.create(IMountServiceStatic.class, (Object) null, false);
    }

    public static IMountServiceContext getWithException(Object caller) {
        return (IMountServiceContext) BlackReflection.create(IMountServiceContext.class, caller, true);
    }

    public static IMountServiceContext get(Object caller) {
        return (IMountServiceContext) BlackReflection.create(IMountServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IMountServiceContext.class);
    }
}
