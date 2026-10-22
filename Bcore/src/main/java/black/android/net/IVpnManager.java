package black.android.net;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/net/IVpnManager.class */
@BClassName("android.net.IVpnManager")
public interface IVpnManager {

    /* JADX INFO: loaded from: classes.jar:black/android/net/IVpnManager$Stub.class */
    @BClassName("android.net.IVpnManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
