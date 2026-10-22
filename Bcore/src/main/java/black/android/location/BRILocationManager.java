package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRILocationManager.class */
public class BRILocationManager {
    public static ILocationManagerStatic getWithException() {
        return (ILocationManagerStatic) BlackReflection.create(ILocationManagerStatic.class, (Object) null, true);
    }

    public static ILocationManagerStatic get() {
        return (ILocationManagerStatic) BlackReflection.create(ILocationManagerStatic.class, (Object) null, false);
    }

    public static ILocationManagerContext getWithException(Object caller) {
        return (ILocationManagerContext) BlackReflection.create(ILocationManagerContext.class, caller, true);
    }

    public static ILocationManagerContext get(Object caller) {
        return (ILocationManagerContext) BlackReflection.create(ILocationManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILocationManagerContext.class);
    }
}
