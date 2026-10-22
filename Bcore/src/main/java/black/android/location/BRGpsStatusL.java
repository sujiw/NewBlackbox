package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRGpsStatusL.class */
public class BRGpsStatusL {
    public static GpsStatusLStatic getWithException() {
        return (GpsStatusLStatic) BlackReflection.create(GpsStatusLStatic.class, (Object) null, true);
    }

    public static GpsStatusLStatic get() {
        return (GpsStatusLStatic) BlackReflection.create(GpsStatusLStatic.class, (Object) null, false);
    }

    public static GpsStatusLContext getWithException(Object caller) {
        return (GpsStatusLContext) BlackReflection.create(GpsStatusLContext.class, caller, true);
    }

    public static GpsStatusLContext get(Object caller) {
        return (GpsStatusLContext) BlackReflection.create(GpsStatusLContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(GpsStatusLContext.class);
    }
}
