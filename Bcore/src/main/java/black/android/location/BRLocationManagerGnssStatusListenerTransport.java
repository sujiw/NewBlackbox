package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerGnssStatusListenerTransport.class */
public class BRLocationManagerGnssStatusListenerTransport {
    public static LocationManagerGnssStatusListenerTransportStatic getWithException() {
        return (LocationManagerGnssStatusListenerTransportStatic) BlackReflection.create(LocationManagerGnssStatusListenerTransportStatic.class, (Object) null, true);
    }

    public static LocationManagerGnssStatusListenerTransportStatic get() {
        return (LocationManagerGnssStatusListenerTransportStatic) BlackReflection.create(LocationManagerGnssStatusListenerTransportStatic.class, (Object) null, false);
    }

    public static LocationManagerGnssStatusListenerTransportContext getWithException(Object caller) {
        return (LocationManagerGnssStatusListenerTransportContext) BlackReflection.create(LocationManagerGnssStatusListenerTransportContext.class, caller, true);
    }

    public static LocationManagerGnssStatusListenerTransportContext get(Object caller) {
        return (LocationManagerGnssStatusListenerTransportContext) BlackReflection.create(LocationManagerGnssStatusListenerTransportContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerGnssStatusListenerTransportContext.class);
    }
}
