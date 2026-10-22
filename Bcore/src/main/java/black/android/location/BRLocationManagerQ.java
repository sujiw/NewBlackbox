package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerQ.class */
public class BRLocationManagerQ {
    public static LocationManagerQStatic getWithException() {
        return (LocationManagerQStatic) BlackReflection.create(LocationManagerQStatic.class, (Object) null, true);
    }

    public static LocationManagerQStatic get() {
        return (LocationManagerQStatic) BlackReflection.create(LocationManagerQStatic.class, (Object) null, false);
    }

    public static LocationManagerQContext getWithException(Object caller) {
        return (LocationManagerQContext) BlackReflection.create(LocationManagerQContext.class, caller, true);
    }

    public static LocationManagerQContext get(Object caller) {
        return (LocationManagerQContext) BlackReflection.create(LocationManagerQContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerQContext.class);
    }
}
