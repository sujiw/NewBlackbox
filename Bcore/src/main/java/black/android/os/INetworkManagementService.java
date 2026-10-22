package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/INetworkManagementService.class */
@BClassName("android.os.INetworkManagementService")
public interface INetworkManagementService {

    /* JADX INFO: loaded from: classes.jar:black/android/os/INetworkManagementService$Stub.class */
    @BClassName("android.os.INetworkManagementService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
