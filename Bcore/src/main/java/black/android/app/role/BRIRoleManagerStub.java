package black.android.app.role;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/role/BRIRoleManagerStub.class */
public class BRIRoleManagerStub {
    public static IRoleManagerStubStatic getWithException() {
        return (IRoleManagerStubStatic) BlackReflection.create(IRoleManagerStubStatic.class, (Object) null, true);
    }

    public static IRoleManagerStubStatic get() {
        return (IRoleManagerStubStatic) BlackReflection.create(IRoleManagerStubStatic.class, (Object) null, false);
    }

    public static IRoleManagerStubContext getWithException(Object caller) {
        return (IRoleManagerStubContext) BlackReflection.create(IRoleManagerStubContext.class, caller, true);
    }

    public static IRoleManagerStubContext get(Object caller) {
        return (IRoleManagerStubContext) BlackReflection.create(IRoleManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IRoleManagerStubContext.class);
    }
}
