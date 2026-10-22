package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/IDeviceIdentifiersPolicyService.class */
@BClassName("android.os.IDeviceIdentifiersPolicyService")
public interface IDeviceIdentifiersPolicyService {

    /* JADX INFO: loaded from: classes.jar:black/android/os/IDeviceIdentifiersPolicyService$Stub.class */
    @BClassName("android.os.IDeviceIdentifiersPolicyService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
