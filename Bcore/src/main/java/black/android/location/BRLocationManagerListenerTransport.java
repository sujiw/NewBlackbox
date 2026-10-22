package black.android.location;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/location/BRLocationManagerListenerTransport.class */
public class BRLocationManagerListenerTransport {
    public static LocationManagerListenerTransportStatic getWithException() {
        return (LocationManagerListenerTransportStatic) BlackReflection.create(LocationManagerListenerTransportStatic.class, (Object) null, true);
    }

    public static LocationManagerListenerTransportStatic get() {
        return (LocationManagerListenerTransportStatic) BlackReflection.create(LocationManagerListenerTransportStatic.class, (Object) null, false);
    }

    public static LocationManagerListenerTransportContext getWithException(Object caller) {
        return (LocationManagerListenerTransportContext) BlackReflection.create(LocationManagerListenerTransportContext.class, caller, true);
    }

    public static LocationManagerListenerTransportContext get(Object caller) {
        return (LocationManagerListenerTransportContext) BlackReflection.create(LocationManagerListenerTransportContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LocationManagerListenerTransportContext.class);
    }
}
