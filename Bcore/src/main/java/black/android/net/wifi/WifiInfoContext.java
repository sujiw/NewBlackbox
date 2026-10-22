package black.android.net.wifi;

import android.net.wifi.SupplicantState;
import java.lang.reflect.Field;
import java.net.InetAddress;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/net/wifi/WifiInfoContext.class */
@BClassNameNotProcess("android.net.wifi.WifiInfo")
public interface WifiInfoContext {
    @BFieldSetNotProcess
    void _set_mBSSID(Object obj);

    @BFieldCheckNotProcess
    Field _check_mBSSID();

    @BFieldNotProcess
    String mBSSID();

    @BFieldSetNotProcess
    void _set_mFrequency(Object obj);

    @BFieldCheckNotProcess
    Field _check_mFrequency();

    @BFieldNotProcess
    Integer mFrequency();

    @BFieldSetNotProcess
    void _set_mIpAddress(Object obj);

    @BFieldCheckNotProcess
    Field _check_mIpAddress();

    @BFieldNotProcess
    InetAddress mIpAddress();

    @BFieldSetNotProcess
    void _set_mLinkSpeed(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLinkSpeed();

    @BFieldNotProcess
    Integer mLinkSpeed();

    @BFieldSetNotProcess
    void _set_mMacAddress(Object obj);

    @BFieldCheckNotProcess
    Field _check_mMacAddress();

    @BFieldNotProcess
    String mMacAddress();

    @BFieldSetNotProcess
    void _set_mNetworkId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNetworkId();

    @BFieldNotProcess
    Integer mNetworkId();

    @BFieldSetNotProcess
    void _set_mRssi(Object obj);

    @BFieldCheckNotProcess
    Field _check_mRssi();

    @BFieldNotProcess
    Integer mRssi();

    @BFieldSetNotProcess
    void _set_mSSID(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSSID();

    @BFieldNotProcess
    String mSSID();

    @BFieldSetNotProcess
    void _set_mSupplicantState(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSupplicantState();

    @BFieldNotProcess
    SupplicantState mSupplicantState();

    @BFieldSetNotProcess
    void _set_mWifiSsid(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWifiSsid();

    @BFieldNotProcess
    Object mWifiSsid();
}
