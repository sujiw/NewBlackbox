package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRILocationManagerStub.class */
public class BRILocationManagerStub {
    public static ILocationManagerStubStatic getWithException() {
        return (ILocationManagerStubStatic) BlackReflection.create(ILocationManagerStubStatic.class, (Object) null, true);
    }

    public static ILocationManagerStubStatic get() {
        return (ILocationManagerStubStatic) BlackReflection.create(ILocationManagerStubStatic.class, (Object) null, false);
    }

    public static ILocationManagerStubContext getWithException(Object caller) {
        return (ILocationManagerStubContext) BlackReflection.create(ILocationManagerStubContext.class, caller, true);
    }

    public static ILocationManagerStubContext get(Object caller) {
        return (ILocationManagerStubContext) BlackReflection.create(ILocationManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILocationManagerStubContext.class);
    }
}
