package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerGpsStatusListenerTransportSumsungS5.class */
public class BRLocationManagerGpsStatusListenerTransportSumsungS5 {
    public static LocationManagerGpsStatusListenerTransportSumsungS5Static getWithException() {
        return (LocationManagerGpsStatusListenerTransportSumsungS5Static) BlackReflection.create(LocationManagerGpsStatusListenerTransportSumsungS5Static.class, (Object) null, true);
    }

    public static LocationManagerGpsStatusListenerTransportSumsungS5Static get() {
        return (LocationManagerGpsStatusListenerTransportSumsungS5Static) BlackReflection.create(LocationManagerGpsStatusListenerTransportSumsungS5Static.class, (Object) null, false);
    }

    public static LocationManagerGpsStatusListenerTransportSumsungS5Context getWithException(Object caller) {
        return (LocationManagerGpsStatusListenerTransportSumsungS5Context) BlackReflection.create(LocationManagerGpsStatusListenerTransportSumsungS5Context.class, caller, true);
    }

    public static LocationManagerGpsStatusListenerTransportSumsungS5Context get(Object caller) {
        return (LocationManagerGpsStatusListenerTransportSumsungS5Context) BlackReflection.create(LocationManagerGpsStatusListenerTransportSumsungS5Context.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerGpsStatusListenerTransportSumsungS5Context.class);
    }
}
