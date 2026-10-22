package black.android.location;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/location/ILocationManager.class */
@BClassName("android.location.ILocationManager")
public interface ILocationManager {

    /* JADX INFO: loaded from: classes.jar:black/android/location/ILocationManager$Stub.class */
    @BClassName("android.location.ILocationManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
