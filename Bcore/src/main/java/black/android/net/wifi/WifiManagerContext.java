package black.android.net.wifi;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/WifiManagerContext.class */
@BClassNameNotProcess("android.net.wifi.WifiManager")
public interface WifiManagerContext {
    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    IInterface mService();
}
