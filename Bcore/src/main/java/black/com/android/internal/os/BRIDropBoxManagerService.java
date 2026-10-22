package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRIDropBoxManagerService.class */
public class BRIDropBoxManagerService {
    public static IDropBoxManagerServiceStatic getWithException() {
        return (IDropBoxManagerServiceStatic) BlackReflection.create(IDropBoxManagerServiceStatic.class, (Object) null, true);
    }

    public static IDropBoxManagerServiceStatic get() {
        return (IDropBoxManagerServiceStatic) BlackReflection.create(IDropBoxManagerServiceStatic.class, (Object) null, false);
    }

    public static IDropBoxManagerServiceContext getWithException(Object caller) {
        return (IDropBoxManagerServiceContext) BlackReflection.create(IDropBoxManagerServiceContext.class, caller, true);
    }

    public static IDropBoxManagerServiceContext get(Object caller) {
        return (IDropBoxManagerServiceContext) BlackReflection.create(IDropBoxManagerServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDropBoxManagerServiceContext.class);
    }
}
