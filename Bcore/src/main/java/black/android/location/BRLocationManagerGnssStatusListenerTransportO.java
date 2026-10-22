package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerGnssStatusListenerTransportO.class */
public class BRLocationManagerGnssStatusListenerTransportO {
    public static LocationManagerGnssStatusListenerTransportOStatic getWithException() {
        return (LocationManagerGnssStatusListenerTransportOStatic) BlackReflection.create(LocationManagerGnssStatusListenerTransportOStatic.class, (Object) null, true);
    }

    public static LocationManagerGnssStatusListenerTransportOStatic get() {
        return (LocationManagerGnssStatusListenerTransportOStatic) BlackReflection.create(LocationManagerGnssStatusListenerTransportOStatic.class, (Object) null, false);
    }

    public static LocationManagerGnssStatusListenerTransportOContext getWithException(Object caller) {
        return (LocationManagerGnssStatusListenerTransportOContext) BlackReflection.create(LocationManagerGnssStatusListenerTransportOContext.class, caller, true);
    }

    public static LocationManagerGnssStatusListenerTransportOContext get(Object caller) {
        return (LocationManagerGnssStatusListenerTransportOContext) BlackReflection.create(LocationManagerGnssStatusListenerTransportOContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerGnssStatusListenerTransportOContext.class);
    }
}
