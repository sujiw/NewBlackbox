package black.android.permission;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/permission/BRIPermissionManagerStub.class */
public class BRIPermissionManagerStub {
    public static IPermissionManagerStubStatic getWithException() {
        return (IPermissionManagerStubStatic) BlackReflection.create(IPermissionManagerStubStatic.class, (Object) null, true);
    }

    public static IPermissionManagerStubStatic get() {
        return (IPermissionManagerStubStatic) BlackReflection.create(IPermissionManagerStubStatic.class, (Object) null, false);
    }

    public static IPermissionManagerStubContext getWithException(Object caller) {
        return (IPermissionManagerStubContext) BlackReflection.create(IPermissionManagerStubContext.class, caller, true);
    }

    public static IPermissionManagerStubContext get(Object caller) {
        return (IPermissionManagerStubContext) BlackReflection.create(IPermissionManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IPermissionManagerStubContext.class);
    }
}
