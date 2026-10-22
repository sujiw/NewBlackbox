package top.niunaijun.blackbox.app.dispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.entity.ServiceRecord;
import top.niunaijun.blackbox.entity.UnbindRecord;
import top.niunaijun.blackbox.proxy.record.ProxyServiceRecord;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/dispatcher/AppServiceDispatcher.class */
public class AppServiceDispatcher {
    public static final String TAG = "AppServiceDispatcher";
    private static final AppServiceDispatcher sServiceDispatcher = new AppServiceDispatcher();
    private Map<Intent.FilterComparison, ServiceRecord> mService = new HashMap();
    private final Handler mHandler = BlackBoxCore.get().getHandler();

    public static AppServiceDispatcher get() {
        return sServiceDispatcher;
    }

    public IBinder onBind(Intent proxyIntent) {
        Service service;
        ProxyServiceRecord serviceRecord = ProxyServiceRecord.create(proxyIntent);
        Intent intent = serviceRecord.mServiceIntent;
        ServiceInfo serviceInfo = serviceRecord.mServiceInfo;
        if (intent == null || serviceInfo == null || (service = getOrCreateService(serviceRecord)) == null) {
            return null;
        }
        intent.setExtrasClassLoader(service.getClassLoader());
        ServiceRecord record = findRecord(intent);
        record.incrementAndGetBindCount(intent);
        if (record.hasBinder(intent)) {
            if (record.isRebind()) {
                service.onRebind(intent);
                record.setRebind(false);
            }
            return record.getBinder(intent);
        }
        try {
            IBinder iBinder = service.onBind(intent);
            record.addBinder(intent, iBinder);
            return iBinder;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public int onStartCommand(Intent proxyIntent, int flags, int startId) {
        Service service;
        ProxyServiceRecord stubRecord = ProxyServiceRecord.create(proxyIntent);
        if (stubRecord.mServiceIntent == null || stubRecord.mServiceInfo == null || (service = getOrCreateService(stubRecord)) == null) {
            return 2;
        }
        stubRecord.mServiceIntent.setExtrasClassLoader(service.getClassLoader());
        ServiceRecord record = findRecord(stubRecord.mServiceIntent);
        record.setStartId(stubRecord.mStartId);
        try {
            int i = service.onStartCommand(stubRecord.mServiceIntent, flags, stubRecord.mStartId);
            BlackBoxCore.getBActivityManager().onStartCommand(proxyIntent, stubRecord.mUserId);
            return i;
        } catch (Throwable e) {
            e.printStackTrace();
            return 2;
        }
    }

    public void onDestroy() {
        if (this.mService.size() > 0) {
            for (ServiceRecord record : this.mService.values()) {
                try {
                    record.getService().onDestroy();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        this.mService.clear();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mService.size() > 0) {
            for (ServiceRecord record : this.mService.values()) {
                try {
                    record.getService().onConfigurationChanged(newConfig);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onLowMemory() {
        if (this.mService.size() > 0) {
            for (ServiceRecord record : this.mService.values()) {
                try {
                    record.getService().onLowMemory();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onTrimMemory(int level) {
        if (this.mService.size() > 0) {
            for (ServiceRecord record : this.mService.values()) {
                try {
                    record.getService().onTrimMemory(level);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean onUnbind(Intent proxyIntent) {
        Service service;
        ProxyServiceRecord stubRecord = ProxyServiceRecord.create(proxyIntent);
        if (stubRecord.mServiceIntent == null || stubRecord.mServiceInfo == null) {
            return false;
        }
        Intent intent = stubRecord.mServiceIntent;
        try {
            UnbindRecord unbindRecord = BlackBoxCore.getBActivityManager().onServiceUnbind(proxyIntent, BlackBoxCore.getUserId());
            if (unbindRecord == null || (service = getOrCreateService(stubRecord)) == null) {
                return false;
            }
            stubRecord.mServiceIntent.setExtrasClassLoader(service.getClassLoader());
            ServiceRecord record = findRecord(intent);
            boolean destroy = unbindRecord.getStartId() == 0;
            if (destroy || record.decreaseConnectionCount(intent)) {
                service.onUnbind(intent);
                if (destroy) {
                    service.onDestroy();
                    BlackBoxCore.getBActivityManager().onServiceDestroy(proxyIntent, BlackBoxCore.getUserId());
                    this.mService.remove(new Intent.FilterComparison(intent));
                }
                record.setRebind(true);
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public IBinder peekService(Intent intent) {
        ServiceRecord record = findRecord(intent);
        if (record == null) {
            return null;
        }
        return record.getBinder(intent);
    }

    public void stopService(Intent intent) {
        ServiceRecord record;
        if (intent != null && (record = findRecord(intent)) != null && record.getService() != null) {
            boolean destroy = record.getStartId() > 0;
            if (destroy) {
                try {
                    this.mHandler.post(() -> {
                        record.getService().onDestroy();
                    });
                    BlackBoxCore.getBActivityManager().onServiceDestroy(intent, BlackBoxCore.getUserId());
                    this.mService.remove(new Intent.FilterComparison(intent));
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ServiceRecord findRecord(Intent intent) {
        return this.mService.get(new Intent.FilterComparison(intent));
    }

    private Service getOrCreateService(ProxyServiceRecord proxyServiceRecord) {
        Intent intent = proxyServiceRecord.mServiceIntent;
        ServiceInfo serviceInfo = proxyServiceRecord.mServiceInfo;
        IBinder token = proxyServiceRecord.mToken;
        ServiceRecord record = findRecord(intent);
        if (record != null && record.getService() != null) {
            return record.getService();
        }
        Service service = BlackBoxCore.currentActivityThread().createService(serviceInfo, token);
        if (service == null) {
            return null;
        }
        ServiceRecord record2 = new ServiceRecord();
        record2.setService(service);
        this.mService.put(new Intent.FilterComparison(intent), record2);
        return service;
    }
}
