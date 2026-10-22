package black.android.permission;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/permission/BRIPermissionManager.class */
public class BRIPermissionManager {
    public static IPermissionManagerStatic getWithException() {
        return (IPermissionManagerStatic) BlackReflection.create(IPermissionManagerStatic.class, (Object) null, true);
    }

    public static IPermissionManagerStatic get() {
        return (IPermissionManagerStatic) BlackReflection.create(IPermissionManagerStatic.class, (Object) null, false);
    }

    public static IPermissionManagerContext getWithException(Object caller) {
        return (IPermissionManagerContext) BlackReflection.create(IPermissionManagerContext.class, caller, true);
    }

    public static IPermissionManagerContext get(Object caller) {
        return (IPermissionManagerContext) BlackReflection.create(IPermissionManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPermissionManagerContext.class);
    }
}
