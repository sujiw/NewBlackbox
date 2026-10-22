package black.android.net.wifi;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/IWifiManager.class */
@BClassName("android.net.wifi.IWifiManager")
public interface IWifiManager {

    /* JADX INFO: loaded from: classes.jar:black/android/net/wifi/IWifiManager$Stub.class */
    @BClassName("android.net.wifi.IWifiManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
