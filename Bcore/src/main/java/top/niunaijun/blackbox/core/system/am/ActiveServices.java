package top.niunaijun.blackbox.core.system.am;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.IEmpty;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.ProcessRecord;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.entity.UnbindRecord;
import top.niunaijun.blackbox.entity.am.RunningServiceInfo;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.proxy.record.ProxyServiceRecord;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/ActiveServices.class */
@SuppressLint({"NewApi"})
public class ActiveServices {
    public static final String TAG = "ActiveServices";
    private final Map<Intent.FilterComparison, RunningServiceRecord> mRunningServiceRecords = new HashMap();
    private final Map<IBinder, RunningServiceRecord> mRunningTokens = new HashMap();
    private final Map<IBinder, ConnectedServiceRecord> mConnectedServices = new HashMap();

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/ActiveServices$ConnectedServiceRecord.class */
    public static class ConnectedServiceRecord {
        private IBinder mIBinder;
        private Intent mIntent;
    }

    public void startService(Intent intent, String resolvedType, boolean requireForeground, int userId) {
        ResolveInfo resolveInfo = resolveService(intent, resolvedType, userId);
        if (resolveInfo == null) {
            return;
        }
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        ProcessRecord processRecord = BProcessManagerService.get().startProcessLocked(serviceInfo.packageName, serviceInfo.processName, userId, -1, Binder.getCallingPid());
        if (processRecord == null) {
            throw new RuntimeException("Unable to create " + serviceInfo.name);
        }
        RunningServiceRecord runningServiceRecord = getOrCreateRunningServiceRecord(intent);
        runningServiceRecord.mServiceInfo = serviceInfo;
        runningServiceRecord.getAndIncrementStartId();
        final Intent stubServiceIntent = createStubServiceIntent(intent, serviceInfo, processRecord, runningServiceRecord);
        new Thread(new Runnable() { // from class: top.niunaijun.blackbox.core.system.am.ActiveServices.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BlackBoxCore.getContext().startService(stubServiceIntent);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int stopService(Intent intent, String resolvedType, int userId) {
        synchronized (this.mRunningServiceRecords) {
            RunningServiceRecord runningServiceRecord = findRunningServiceRecord(intent);
            if (runningServiceRecord == null) {
                return 0;
            }
            if (runningServiceRecord.mBindCount.get() > 0) {
                Log.d(TAG, "There are also connections");
                return 0;
            }
            runningServiceRecord.mStartId.set(0);
            ResolveInfo resolveInfo = resolveService(intent, resolvedType, userId);
            if (resolveInfo == null) {
                return 0;
            }
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            ProcessRecord processRecord = BProcessManagerService.get().startProcessLocked(serviceInfo.packageName, serviceInfo.processName, userId, -1, Binder.getCallingPid());
            if (processRecord == null) {
                return 0;
            }
            try {
                processRecord.bActivityThread.stopService(intent);
            } catch (RemoteException e) {
            }
            return 0;
        }
    }

    public Intent bindService(Intent intent, final IBinder binder, String resolvedType, int userId) {
        RunningServiceRecord runningServiceRecord;
        ResolveInfo resolveInfo = resolveService(intent, resolvedType, userId);
        if (resolveInfo == null) {
            return intent;
        }
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        ProcessRecord processRecord = BProcessManagerService.get().startProcessLocked(serviceInfo.packageName, serviceInfo.processName, userId, -1, Binder.getCallingPid());
        if (processRecord == null) {
            throw new RuntimeException("Unable to create " + serviceInfo.name);
        }
        synchronized (this.mRunningServiceRecords) {
            runningServiceRecord = getOrCreateRunningServiceRecord(intent);
            runningServiceRecord.mServiceInfo = serviceInfo;
            if (binder != null) {
                ConnectedServiceRecord connectedService = this.mConnectedServices.get(binder);
                boolean isBound = false;
                if (connectedService != null) {
                    isBound = true;
                } else {
                    connectedService = new ConnectedServiceRecord();
                    try {
                        binder.linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.core.system.am.ActiveServices.2
                            @Override // android.os.IBinder.DeathRecipient
                            public void binderDied() {
                                binder.unlinkToDeath(this, 0);
                                ActiveServices.this.mConnectedServices.remove(binder);
                            }
                        }, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    connectedService.mIBinder = binder;
                    connectedService.mIntent = intent;
                    this.mConnectedServices.put(binder, connectedService);
                }
                if (!isBound) {
                    runningServiceRecord.incrementBindCountAndGet();
                }
                runningServiceRecord.mConnectedServiceRecord = connectedService;
            }
        }
        return createStubServiceIntent(intent, serviceInfo, processRecord, runningServiceRecord);
    }

    public void unbindService(IBinder binder, int userId) {
        ConnectedServiceRecord connectedService = this.mConnectedServices.get(binder);
        if (connectedService == null) {
            return;
        }
        RunningServiceRecord runningServiceRecord = getOrCreateRunningServiceRecord(connectedService.mIntent);
        runningServiceRecord.mConnectedServiceRecord = null;
        runningServiceRecord.mBindCount.decrementAndGet();
        this.mConnectedServices.remove(binder);
    }

    public void stopServiceToken(ComponentName className, IBinder token, int userId) {
        RunningServiceRecord runningServiceByToken = findRunningServiceByToken(token);
        if (runningServiceByToken != null) {
            stopService(runningServiceByToken.mIntent, null, userId);
        }
    }

    public void onStartCommand(Intent proxyIntent, int userId) {
    }

    public void onServiceDestroy(Intent proxyIntent, int userId) {
        if (proxyIntent == null) {
            return;
        }
        ProxyServiceRecord proxyServiceRecord = ProxyServiceRecord.create(proxyIntent);
        if (proxyServiceRecord.mServiceIntent != null) {
            proxyIntent = proxyServiceRecord.mServiceIntent;
        }
        RunningServiceRecord remove = this.mRunningServiceRecords.remove(new Intent.FilterComparison(proxyIntent));
        if (remove != null) {
            this.mRunningTokens.remove(remove);
        }
    }

    public UnbindRecord onServiceUnbind(Intent proxyIntent, int userId) throws RemoteException {
        if (proxyIntent == null) {
            return null;
        }
        ProxyServiceRecord proxyServiceRecord = ProxyServiceRecord.create(proxyIntent);
        ComponentName component = proxyServiceRecord.mServiceIntent.getComponent();
        RunningServiceRecord runningServiceRecord = findRunningServiceRecord(proxyServiceRecord.mServiceIntent);
        if (runningServiceRecord == null) {
            return null;
        }
        UnbindRecord record = new UnbindRecord();
        record.setComponentName(component);
        record.setBindCount(runningServiceRecord.mBindCount.get());
        record.setStartId(runningServiceRecord.mStartId.get());
        return record;
    }

    private Intent createStubServiceIntent(Intent targetIntent, ServiceInfo serviceInfo, ProcessRecord processRecord, RunningServiceRecord runningServiceRecord) {
        Intent stub = new Intent();
        ComponentName stubComp = new ComponentName(BlackBoxCore.getHostPkg(), ProxyManifest.getProxyService(processRecord.bpid));
        stub.setComponent(stubComp);
        stub.setAction(UUID.randomUUID().toString());
        ProxyServiceRecord.saveStub(stub, targetIntent, serviceInfo, runningServiceRecord, processRecord.userId, runningServiceRecord.mStartId.get());
        return stub;
    }

    private RunningServiceRecord getOrCreateRunningServiceRecord(Intent intent) {
        RunningServiceRecord runningServiceRecord = findRunningServiceRecord(intent);
        if (runningServiceRecord == null) {
            runningServiceRecord = new RunningServiceRecord();
            runningServiceRecord.mIntent = intent;
            this.mRunningServiceRecords.put(new Intent.FilterComparison(intent), runningServiceRecord);
            this.mRunningTokens.put(runningServiceRecord, runningServiceRecord);
        }
        return runningServiceRecord;
    }

    private RunningServiceRecord findRunningServiceRecord(Intent intent) {
        return this.mRunningServiceRecords.get(new Intent.FilterComparison(intent));
    }

    private RunningServiceRecord findRunningServiceByToken(IBinder token) {
        return this.mRunningTokens.get(token);
    }

    public RunningServiceInfo getRunningServiceInfo(String callerPackage, int userId) {
        ActivityManager.RunningServiceInfo runningServiceInfo;
        ActivityManager manager = (ActivityManager) BlackBoxCore.getContext().getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);
        Map<Integer, ActivityManager.RunningServiceInfo> serviceInfoMap = new HashMap<>();
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
            serviceInfoMap.put(Integer.valueOf(runningService.pid), runningService);
        }
        RunningServiceInfo info = new RunningServiceInfo();
        for (RunningServiceRecord value : this.mRunningServiceRecords.values()) {
            ServiceInfo serviceInfo = value.mServiceInfo;
            ProcessRecord processRecord = BProcessManagerService.get().findProcessRecord(callerPackage, serviceInfo.processName, userId);
            if (processRecord != null && (runningServiceInfo = serviceInfoMap.get(Integer.valueOf(processRecord.pid))) != null) {
                runningServiceInfo.process = processRecord.processName;
                runningServiceInfo.service = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                info.mRunningServiceInfoList.add(runningServiceInfo);
            }
        }
        return info;
    }

    public IBinder peekService(Intent intent, String resolvedType, int userId) {
        ProcessRecord processRecord;
        ResolveInfo resolveInfo = resolveService(intent, resolvedType, userId);
        if (resolveInfo == null || (processRecord = BProcessManagerService.get().findProcessRecord(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.processName, userId)) == null) {
            return null;
        }
        try {
            return processRecord.bActivityThread.peekService(intent);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResolveInfo resolveService(Intent intent, String resolvedType, int userId) {
        return BPackageManagerService.get().resolveService(intent, 0, resolvedType, userId);
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/ActiveServices$RunningServiceRecord.class */
    public static class RunningServiceRecord extends IEmpty.Stub {
        private final AtomicInteger mStartId = new AtomicInteger(1);
        private final AtomicInteger mBindCount = new AtomicInteger(0);
        private ConnectedServiceRecord mConnectedServiceRecord;
        private ServiceInfo mServiceInfo;
        private Intent mIntent;

        public int getAndIncrementStartId() {
            return this.mStartId.getAndIncrement();
        }

        public int decrementBindCountAndGet() {
            return this.mBindCount.decrementAndGet();
        }

        public int incrementBindCountAndGet() {
            return this.mBindCount.incrementAndGet();
        }
    }
}
