package black.com.android.internal.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/BRIDropBoxManagerServiceStub.class */
public class BRIDropBoxManagerServiceStub {
    public static IDropBoxManagerServiceStubStatic getWithException() {
        return (IDropBoxManagerServiceStubStatic) BlackReflection.create(IDropBoxManagerServiceStubStatic.class, (Object) null, true);
    }

    public static IDropBoxManagerServiceStubStatic get() {
        return (IDropBoxManagerServiceStubStatic) BlackReflection.create(IDropBoxManagerServiceStubStatic.class, (Object) null, false);
    }

    public static IDropBoxManagerServiceStubContext getWithException(Object caller) {
        return (IDropBoxManagerServiceStubContext) BlackReflection.create(IDropBoxManagerServiceStubContext.class, caller, true);
    }

    public static IDropBoxManagerServiceStubContext get(Object caller) {
        return (IDropBoxManagerServiceStubContext) BlackReflection.create(IDropBoxManagerServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IDropBoxManagerServiceStubContext.class);
    }
}
