package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRILocationListener.class */
public class BRILocationListener {
    public static ILocationListenerStatic getWithException() {
        return (ILocationListenerStatic) BlackReflection.create(ILocationListenerStatic.class, (Object) null, true);
    }

    public static ILocationListenerStatic get() {
        return (ILocationListenerStatic) BlackReflection.create(ILocationListenerStatic.class, (Object) null, false);
    }

    public static ILocationListenerContext getWithException(Object caller) {
        return (ILocationListenerContext) BlackReflection.create(ILocationListenerContext.class, caller, true);
    }

    public static ILocationListenerContext get(Object caller) {
        return (ILocationListenerContext) BlackReflection.create(ILocationListenerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILocationListenerContext.class);
    }
}
