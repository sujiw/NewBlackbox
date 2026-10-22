package black.android.net.wifi;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/WifiSsidStatic.class */
@BClassNameNotProcess("android.net.wifi.WifiSsid")
public interface WifiSsidStatic {
    @BMethodCheckNotProcess
    Method _check_createFromAsciiEncoded(String str);

    Object createFromAsciiEncoded(String str);
}
