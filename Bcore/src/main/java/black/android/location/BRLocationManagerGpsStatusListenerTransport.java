package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerGpsStatusListenerTransport.class */
public class BRLocationManagerGpsStatusListenerTransport {
    public static LocationManagerGpsStatusListenerTransportStatic getWithException() {
        return (LocationManagerGpsStatusListenerTransportStatic) BlackReflection.create(LocationManagerGpsStatusListenerTransportStatic.class, (Object) null, true);
    }

    public static LocationManagerGpsStatusListenerTransportStatic get() {
        return (LocationManagerGpsStatusListenerTransportStatic) BlackReflection.create(LocationManagerGpsStatusListenerTransportStatic.class, (Object) null, false);
    }

    public static LocationManagerGpsStatusListenerTransportContext getWithException(Object caller) {
        return (LocationManagerGpsStatusListenerTransportContext) BlackReflection.create(LocationManagerGpsStatusListenerTransportContext.class, caller, true);
    }

    public static LocationManagerGpsStatusListenerTransportContext get(Object caller) {
        return (LocationManagerGpsStatusListenerTransportContext) BlackReflection.create(LocationManagerGpsStatusListenerTransportContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerGpsStatusListenerTransportContext.class);
    }
}
