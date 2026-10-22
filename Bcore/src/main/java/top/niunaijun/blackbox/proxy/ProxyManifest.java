package top.niunaijun.blackbox.proxy;

import java.util.Locale;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyManifest.class */
public class ProxyManifest {
    public static final int FREE_COUNT = 50;

    public static boolean isProxy(String msg) {
        return getBindProvider().equals(msg) || msg.contains("proxy_content_provider_");
    }

    public static String getBindProvider() {
        return BlackBoxCore.getHostPkg() + ".blackbox.SystemCallProvider";
    }

    public static String getProxyAuthorities(int index) {
        return String.format(Locale.CHINA, "%s.proxy_content_provider_%d", BlackBoxCore.getHostPkg(), Integer.valueOf(index));
    }

    public static String getProxyPendingActivity(int index) {
        return String.format(Locale.CHINA, "top.niunaijun.blackbox.proxy.ProxyPendingActivity$P%d", Integer.valueOf(index));
    }

    public static String getProxyActivity(int index) {
        return String.format(Locale.CHINA, "top.niunaijun.blackbox.proxy.ProxyActivity$P%d", Integer.valueOf(index));
    }

    public static String TransparentProxyActivity(int index) {
        return String.format(Locale.CHINA, "top.niunaijun.blackbox.proxy.TransparentProxyActivity$P%d", Integer.valueOf(index));
    }

    public static String getProxyService(int index) {
        return String.format(Locale.CHINA, "top.niunaijun.blackbox.proxy.ProxyService$P%d", Integer.valueOf(index));
    }

    public static String getProxyJobService(int index) {
        return String.format(Locale.CHINA, "top.niunaijun.blackbox.proxy.ProxyJobService$P%d", Integer.valueOf(index));
    }

    public static String getProxyFileProvider() {
        return BlackBoxCore.getHostPkg() + ".blackbox.FileProvider";
    }

    public static String getProxyReceiver() {
        return BlackBoxCore.getHostPkg() + ".stub_receiver";
    }

    public static String getProcessName(int bPid) {
        return BlackBoxCore.getHostPkg() + ":p" + bPid;
    }
}
