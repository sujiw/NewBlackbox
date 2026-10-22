package black.android.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/location/ILocationListener.class */
@BClassName(android.location.ILocationListener.Stub.DESCRIPTOR)
public interface ILocationListener {

    /* JADX INFO: loaded from: classes.jar:black/android/location/ILocationListener$Stub.class */
    @BClassName("android.location.ILocationListener$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }

    @BMethod
    void onLocationChanged(Location location);
}
