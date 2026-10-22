package black.android.net;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/net/IConnectivityManager.class */
@BClassName(android.net.IConnectivityManager.Stub.DESCRIPTOR)
public interface IConnectivityManager {

    /* JADX INFO: loaded from: classes.jar:black/android/net/IConnectivityManager$Stub.class */
    @BClassName("android.net.IConnectivityManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
