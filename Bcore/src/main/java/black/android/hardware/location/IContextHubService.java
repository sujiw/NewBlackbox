package black.android.hardware.location;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/location/IContextHubService.class */
@BClassName("android.hardware.location.IContextHubService")
public interface IContextHubService {

    /* JADX INFO: loaded from: classes.jar:black/android/hardware/location/IContextHubService$Stub.class */
    @BClassName("android.hardware.location.IContextHubService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
