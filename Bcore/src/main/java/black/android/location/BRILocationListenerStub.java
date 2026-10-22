package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRILocationListenerStub.class */
public class BRILocationListenerStub {
    public static ILocationListenerStubStatic getWithException() {
        return (ILocationListenerStubStatic) BlackReflection.create(ILocationListenerStubStatic.class, (Object) null, true);
    }

    public static ILocationListenerStubStatic get() {
        return (ILocationListenerStubStatic) BlackReflection.create(ILocationListenerStubStatic.class, (Object) null, false);
    }

    public static ILocationListenerStubContext getWithException(Object caller) {
        return (ILocationListenerStubContext) BlackReflection.create(ILocationListenerStubContext.class, caller, true);
    }

    public static ILocationListenerStubContext get(Object caller) {
        return (ILocationListenerStubContext) BlackReflection.create(ILocationListenerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILocationListenerStubContext.class);
    }
}
