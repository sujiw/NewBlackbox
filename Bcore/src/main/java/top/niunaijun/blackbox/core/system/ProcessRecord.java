package top.niunaijun.blackbox.core.system;

import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.ConditionVariable;
import android.os.IInterface;
import android.os.Process;
import java.io.File;
import java.util.Arrays;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.IBActivityThread;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.utils.AppProxyManager;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.GaidManager;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/ProcessRecord.class */
public class ProcessRecord extends Binder {
    public final ApplicationInfo info;
    public final String processName;
    public IBActivityThread bActivityThread;
    public IInterface appThread;
    public int uid;
    public int pid;
    public int buid;
    public int bpid;
    public int callingBUid;
    public int userId;
    public ConditionVariable initLock = new ConditionVariable();

    public ProcessRecord(ApplicationInfo info, String processName) {
        this.info = info;
        this.processName = processName;
    }

    public int getCallingBUid() {
        return this.callingBUid;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.processName, Integer.valueOf(this.pid), Integer.valueOf(this.buid), Integer.valueOf(this.bpid), Integer.valueOf(this.uid), Integer.valueOf(this.pid), Integer.valueOf(this.userId)});
    }

    public String getProviderAuthority() {
        return ProxyManifest.getProxyAuthorities(this.bpid);
    }

    public AppConfig getClientConfig() {
        AppConfig config = new AppConfig();
        config.packageName = this.info.packageName;
        config.processName = this.processName;
        config.bpid = this.bpid;
        config.buid = this.buid;
        config.uid = this.uid;
        config.callingBUid = this.callingBUid;
        config.userId = this.userId;
        config.token = this;
        File hostFilesDir = BlackBoxCore.getContext() != null
                ? BlackBoxCore.getContext().getFilesDir() : null;
        config.proxyConfig = AppProxyManager.readStoredConfig(
                hostFilesDir, this.info.packageName);
        config.gaidConfig = GaidManager.readStoredConfig(
                hostFilesDir, this.info.packageName);
        config.deviceConfig = DeviceInfoManager.readStoredConfig(
                hostFilesDir, this.info.packageName);
        return config;
    }

    public void kill() {
        if (this.pid > 0) {
            try {
                Process.killProcess(this.pid);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public String getPackageName() {
        return this.info.packageName;
    }
}
