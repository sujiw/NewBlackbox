package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationRequestL.class */
public class BRLocationRequestL {
    public static LocationRequestLStatic getWithException() {
        return (LocationRequestLStatic) BlackReflection.create(LocationRequestLStatic.class, (Object) null, true);
    }

    public static LocationRequestLStatic get() {
        return (LocationRequestLStatic) BlackReflection.create(LocationRequestLStatic.class, (Object) null, false);
    }

    public static LocationRequestLContext getWithException(Object caller) {
        return (LocationRequestLContext) BlackReflection.create(LocationRequestLContext.class, caller, true);
    }

    public static LocationRequestLContext get(Object caller) {
        return (LocationRequestLContext) BlackReflection.create(LocationRequestLContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationRequestLContext.class);
    }
}
