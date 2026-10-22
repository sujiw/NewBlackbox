package top.niunaijun.blackbox.fake.service;

import android.net.wifi.WifiInfo;
import black.android.net.wifi.BRIWifiManagerStub;
import black.android.net.wifi.BRWifiInfo;
import black.android.net.wifi.BRWifiSsid;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.DeviceInfoManager;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IWifiManagerProxy.class */
public class IWifiManagerProxy extends BinderInvocationStub {
    public static final String TAG = "IWifiManagerProxy";

    public IWifiManagerProxy() {
        super(BRServiceManager.get().getService("wifi"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRIWifiManagerStub.get().asInterface(BRServiceManager.get().getService("wifi"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("wifi");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IWifiManagerProxy$GetConnectionInfo.class */
    @ProxyMethod("getConnectionInfo")
    public static class GetConnectionInfo extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            WifiInfo wifiInfo = (WifiInfo) method.invoke(who, args);
            if (wifiInfo == null) {
                return null;
            }
            String mac = DeviceInfoManager.macAddress();
            if (mac == null) {
                mac = "ac:62:5a:82:65:c4";
            } else if (mac.isEmpty()) {
                // 档案里显式写了空 mac：用系统"不可用"值，而不是所有档案共用一个固定 MAC
                mac = "02:00:00:00:00:00";
            }
            String bssid = DeviceInfoManager.wifiBssid();
            String ssid = DeviceInfoManager.wifiSsid();
            BRWifiInfo.get(wifiInfo)._set_mBSSID(bssid != null ? bssid : mac);
            BRWifiInfo.get(wifiInfo)._set_mMacAddress(mac);
            BRWifiInfo.get(wifiInfo)._set_mWifiSsid(
                    BRWifiSsid.get().createFromAsciiEncoded(ssid != null ? ssid : "BlackBox_Wifi"));
            int ip = DeviceInfoManager.ipAddressInt();
            if (ip != 0) {
                try {
                    java.lang.reflect.Field field = WifiInfo.class.getDeclaredField("mIpAddress");
                    field.setAccessible(true);
                    field.setInt(wifiInfo, ip);
                } catch (Throwable ignored) {
                }
            }
            return wifiInfo;
        }

        public static String intIP2StringIP(int ip) {
            return (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
        }

        public static int ip2Int(String ipString) {
            String[] ipSlices = ipString.split("\\.");
            int rs = 0;
            for (int i = 0; i < ipSlices.length; i++) {
                int intSlice = Integer.parseInt(ipSlices[i]) << (8 * i);
                rs |= intSlice;
            }
            return rs;
        }
    }
}
