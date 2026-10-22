package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerGpsStatusListenerTransportVIVO.class */
public class BRLocationManagerGpsStatusListenerTransportVIVO {
    public static LocationManagerGpsStatusListenerTransportVIVOStatic getWithException() {
        return (LocationManagerGpsStatusListenerTransportVIVOStatic) BlackReflection.create(LocationManagerGpsStatusListenerTransportVIVOStatic.class, (Object) null, true);
    }

    public static LocationManagerGpsStatusListenerTransportVIVOStatic get() {
        return (LocationManagerGpsStatusListenerTransportVIVOStatic) BlackReflection.create(LocationManagerGpsStatusListenerTransportVIVOStatic.class, (Object) null, false);
    }

    public static LocationManagerGpsStatusListenerTransportVIVOContext getWithException(Object caller) {
        return (LocationManagerGpsStatusListenerTransportVIVOContext) BlackReflection.create(LocationManagerGpsStatusListenerTransportVIVOContext.class, caller, true);
    }

    public static LocationManagerGpsStatusListenerTransportVIVOContext get(Object caller) {
        return (LocationManagerGpsStatusListenerTransportVIVOContext) BlackReflection.create(LocationManagerGpsStatusListenerTransportVIVOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerGpsStatusListenerTransportVIVOContext.class);
    }
}
