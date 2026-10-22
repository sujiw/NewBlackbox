package top.niunaijun.blackbox.core.system.am;

import android.content.BroadcastReceiver;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.pm.BPackage;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.core.system.pm.BPackageSettings;
import top.niunaijun.blackbox.core.system.pm.PackageMonitor;
import top.niunaijun.blackbox.entity.am.PendingResultData;
import top.niunaijun.blackbox.proxy.ProxyBroadcastReceiver;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/BroadcastManager.class */
public class BroadcastManager implements PackageMonitor {
    public static final String TAG = "BroadcastManager";
    public static final int TIMEOUT = 9000;
    public static final int MSG_TIME_OUT = 1;
    private static BroadcastManager sBroadcastManager;
    private final BActivityManagerService mAms;
    private final BPackageManagerService mPms;
    private final Map<String, List<BroadcastReceiver>> mReceivers = new HashMap();
    private final Map<String, PendingResultData> mReceiversData = new HashMap();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: top.niunaijun.blackbox.core.system.am.BroadcastManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    try {
                        PendingResultData data = (PendingResultData) msg.obj;
                        data.build().finish();
                        Slog.d(BroadcastManager.TAG, "Timeout Receiver: " + data);
                        break;
                    } catch (Throwable th) {
                    }
                    break;
            }
        }
    };

    public static BroadcastManager startSystem(BActivityManagerService ams, BPackageManagerService pms) {
        if (sBroadcastManager == null) {
            synchronized (BroadcastManager.class) {
                if (sBroadcastManager == null) {
                    sBroadcastManager = new BroadcastManager(ams, pms);
                }
            }
        }
        return sBroadcastManager;
    }

    public BroadcastManager(BActivityManagerService ams, BPackageManagerService pms) {
        this.mAms = ams;
        this.mPms = pms;
    }

    public void startup() {
        this.mPms.addPackageMonitor(this);
        List<BPackageSettings> bPackageSettings = this.mPms.getBPackageSettings();
        for (BPackageSettings bPackageSetting : bPackageSettings) {
            BPackage bPackage = bPackageSetting.pkg;
            registerPackage(bPackage);
        }
    }

    private void registerPackage(BPackage bPackage) {
        synchronized (this.mReceivers) {
            Slog.d(TAG, "register: " + bPackage.packageName + ", size: " + bPackage.receivers.size());
            for (BPackage.Activity receiver : bPackage.receivers) {
                List<BPackage.ActivityIntentInfo> intents = receiver.intents;
                for (BPackage.ActivityIntentInfo intent : intents) {
                    ProxyBroadcastReceiver proxyBroadcastReceiver = new ProxyBroadcastReceiver();
                    if (Build.VERSION.SDK_INT >= 26) {
                        BlackBoxCore.getContext().registerReceiver(proxyBroadcastReceiver, intent.intentFilter, 2);
                    } else {
                        BlackBoxCore.getContext().registerReceiver(proxyBroadcastReceiver, intent.intentFilter);
                    }
                    addReceiver(bPackage.packageName, proxyBroadcastReceiver);
                }
            }
        }
    }

    private void addReceiver(String packageName, BroadcastReceiver receiver) {
        List<BroadcastReceiver> broadcastReceivers = this.mReceivers.get(packageName);
        if (broadcastReceivers == null) {
            broadcastReceivers = new ArrayList();
            this.mReceivers.put(packageName, broadcastReceivers);
        }
        broadcastReceivers.add(receiver);
    }

    public void sendBroadcast(PendingResultData pendingResultData) {
        synchronized (this.mReceiversData) {
            this.mReceiversData.put(pendingResultData.mBToken, pendingResultData);
            Message obtain = Message.obtain(this.mHandler, 1, pendingResultData);
            this.mHandler.sendMessageDelayed(obtain, 9000L);
        }
    }

    public void finishBroadcast(PendingResultData data) {
        synchronized (this.mReceiversData) {
            this.mHandler.removeMessages(1, this.mReceiversData.get(data.mBToken));
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageUninstalled(String packageName, boolean removeApp, int userId) {
        if (removeApp) {
            synchronized (this.mReceivers) {
                List<BroadcastReceiver> broadcastReceivers = this.mReceivers.get(packageName);
                if (broadcastReceivers != null) {
                    Slog.d(TAG, "unregisterReceiver Package: " + packageName + ", size: " + broadcastReceivers.size());
                    for (BroadcastReceiver broadcastReceiver : broadcastReceivers) {
                        try {
                            BlackBoxCore.getContext().unregisterReceiver(broadcastReceiver);
                        } catch (Throwable th) {
                        }
                    }
                }
                this.mReceivers.remove(packageName);
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageInstalled(String packageName, int userId) {
        synchronized (this.mReceivers) {
            this.mReceivers.remove(packageName);
            BPackageSettings bPackageSetting = this.mPms.getBPackageSetting(packageName);
            if (bPackageSetting != null) {
                registerPackage(bPackageSetting.pkg);
            }
        }
    }
}
