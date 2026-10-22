package black.android.app.role;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/role/BRIRoleManager.class */
public class BRIRoleManager {
    public static IRoleManagerStatic getWithException() {
        return (IRoleManagerStatic) BlackReflection.create(IRoleManagerStatic.class, (Object) null, true);
    }

    public static IRoleManagerStatic get() {
        return (IRoleManagerStatic) BlackReflection.create(IRoleManagerStatic.class, (Object) null, false);
    }

    public static IRoleManagerContext getWithException(Object caller) {
        return (IRoleManagerContext) BlackReflection.create(IRoleManagerContext.class, caller, true);
    }

    public static IRoleManagerContext get(Object caller) {
        return (IRoleManagerContext) BlackReflection.create(IRoleManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IRoleManagerContext.class);
    }
}
