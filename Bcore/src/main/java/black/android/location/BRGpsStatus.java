package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRGpsStatus.class */
public class BRGpsStatus {
    public static GpsStatusStatic getWithException() {
        return (GpsStatusStatic) BlackReflection.create(GpsStatusStatic.class, (Object) null, true);
    }

    public static GpsStatusStatic get() {
        return (GpsStatusStatic) BlackReflection.create(GpsStatusStatic.class, (Object) null, false);
    }

    public static GpsStatusContext getWithException(Object caller) {
        return (GpsStatusContext) BlackReflection.create(GpsStatusContext.class, caller, true);
    }

    public static GpsStatusContext get(Object caller) {
        return (GpsStatusContext) BlackReflection.create(GpsStatusContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(GpsStatusContext.class);
    }
}
