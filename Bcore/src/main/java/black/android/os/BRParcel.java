package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRParcel.class */
public class BRParcel {
    public static ParcelStatic getWithException() {
        return (ParcelStatic) BlackReflection.create(ParcelStatic.class, (Object) null, true);
    }

    public static ParcelStatic get() {
        return (ParcelStatic) BlackReflection.create(ParcelStatic.class, (Object) null, false);
    }

    public static ParcelContext getWithException(Object caller) {
        return (ParcelContext) BlackReflection.create(ParcelContext.class, caller, true);
    }

    public static ParcelContext get(Object caller) {
        return (ParcelContext) BlackReflection.create(ParcelContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ParcelContext.class);
    }
}
