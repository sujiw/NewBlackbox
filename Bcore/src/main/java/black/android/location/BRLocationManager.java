package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManager.class */
public class BRLocationManager {
    public static LocationManagerStatic getWithException() {
        return (LocationManagerStatic) BlackReflection.create(LocationManagerStatic.class, (Object) null, true);
    }

    public static LocationManagerStatic get() {
        return (LocationManagerStatic) BlackReflection.create(LocationManagerStatic.class, (Object) null, false);
    }

    public static LocationManagerContext getWithException(Object caller) {
        return (LocationManagerContext) BlackReflection.create(LocationManagerContext.class, caller, true);
    }

    public static LocationManagerContext get(Object caller) {
        return (LocationManagerContext) BlackReflection.create(LocationManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerContext.class);
    }
}
