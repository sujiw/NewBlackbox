package top.niunaijun.blackbox.fake.frameworks;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.system.ServiceManager;
import top.niunaijun.blackbox.core.system.am.IBActivityManagerService;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.UnbindRecord;
import top.niunaijun.blackbox.entity.am.PendingResultData;
import top.niunaijun.blackbox.entity.am.RunningAppProcessInfo;
import top.niunaijun.blackbox.entity.am.RunningServiceInfo;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/frameworks/BActivityManager.class */
public class BActivityManager extends BlackManager<IBActivityManagerService> {
    private static final String TAG = "BActivityManager";
    private static final BActivityManager sActivityManager = new BActivityManager();

    public static BActivityManager get() {
        return sActivityManager;
    }

    @Override // top.niunaijun.blackbox.fake.frameworks.BlackManager
    protected String getServiceName() {
        return ServiceManager.ACTIVITY_MANAGER;
    }

    public AppConfig initProcess(String packageName, String processName, int userId) {
        for (int retryCount = 0; retryCount < 3; retryCount++) {
            try {
                IBActivityManagerService service = getService();
                if (service != null) {
                    AppConfig result = service.initProcess(packageName, processName, userId);
                    if (result != null) {
                        return result;
                    }
                    Slog.w(TAG, "initProcess returned null for package: " + packageName + ", process: " + processName + ", retry " + (retryCount + 1) + "/3");
                } else {
                    Slog.w(TAG, "ActivityManager service is null for initProcess, retry " + (retryCount + 1) + "/3");
                }
            } catch (DeadObjectException e) {
                Slog.w(TAG, "ActivityManager service died during initProcess, clearing cache and retrying " + (retryCount + 1) + "/3", e);
                clearServiceCache();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                }
            } catch (RemoteException e3) {
                Slog.e(TAG, "RemoteException in initProcess", e3);
            } catch (Exception e4) {
                Slog.e(TAG, "Unexpected error in initProcess", e4);
            }
        }
        Slog.e(TAG, "Failed to initProcess after 3 retries for package: " + packageName + ", process: " + processName);
        return null;
    }

    public void restartProcess(String packageName, String processName, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.restartProcess(packageName, processName, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startActivity(Intent intent, int userId) {
        for (int retryCount = 0; retryCount < 3; retryCount++) {
            try {
                IBActivityManagerService service = getService();
                if (service != null) {
                    service.startActivity(intent, userId);
                    return;
                }
                Slog.w(TAG, "ActivityManager service is null, retry " + (retryCount + 1) + "/3");
                try {
                    Thread.sleep(200 * (retryCount + 1));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } catch (DeadObjectException e2) {
                Slog.w(TAG, "ActivityManager service died, clearing cache and retrying " + (retryCount + 1) + "/3");
                clearServiceCache();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e3) {
                    Thread.currentThread().interrupt();
                }
            } catch (RemoteException e4) {
                Slog.e(TAG, "RemoteException in startActivity", e4);
            } catch (Exception e5) {
                Slog.e(TAG, "Unexpected error in startActivity", e5);
            }
        }
        Slog.e(TAG, "Failed to start activity after 3 retries");
    }

    public int startActivityAms(int userId, Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode, int flags, Bundle options) {
        for (int retryCount = 0; retryCount < 3; retryCount++) {
            try {
                IBActivityManagerService service = getService();
                if (service != null) {
                    return service.startActivityAms(userId, intent, resolvedType, resultTo, resultWho, requestCode, flags, options);
                }
                Slog.w(TAG, "ActivityManager service is null, retry " + (retryCount + 1) + "/3");
            } catch (DeadObjectException e) {
                Slog.w(TAG, "ActivityManager service died, clearing cache and retrying " + (retryCount + 1) + "/3");
                clearServiceCache();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                }
            } catch (RemoteException e3) {
                Slog.e(TAG, "RemoteException in startActivityAms", e3);
            } catch (Exception e4) {
                Slog.e(TAG, "Unexpected error in startActivityAms", e4);
            }
        }
        Slog.e(TAG, "Failed to start activity AMS after 3 retries");
        return -1;
    }

    public int startActivities(int userId, Intent[] intent, String[] resolvedType, IBinder resultTo, Bundle options) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.startActivities(userId, intent, resolvedType, resultTo, options);
            }
            return -1;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ComponentName startService(Intent intent, String resolvedType, boolean requireForeground, int userId) {
        for (int retryCount = 0; retryCount < 3; retryCount++) {
            try {
                IBActivityManagerService service = getService();
                if (service != null) {
                    return service.startService(intent, resolvedType, requireForeground, userId);
                }
                Slog.w(TAG, "ActivityManager service is null, retry " + (retryCount + 1) + "/3");
            } catch (DeadObjectException e) {
                Slog.w(TAG, "ActivityManager service died, clearing cache and retrying " + (retryCount + 1) + "/3");
                clearServiceCache();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                }
            } catch (RemoteException e3) {
                Slog.e(TAG, "RemoteException in startService", e3);
            } catch (Exception e4) {
                Slog.e(TAG, "Unexpected error in startService", e4);
            }
        }
        Slog.e(TAG, "Failed to start service after 3 retries");
        return null;
    }

    public int stopService(Intent intent, String resolvedType, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.stopService(intent, resolvedType, userId);
            }
            return -1;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Intent bindService(Intent service, IBinder binder, String resolvedType, int userId) {
        try {
            IBActivityManagerService serviceManager = getService();
            if (serviceManager != null) {
                return serviceManager.bindService(service, binder, resolvedType, userId);
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void unbindService(IBinder binder, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.unbindService(binder, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void stopServiceToken(ComponentName componentName, IBinder token, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.stopServiceToken(componentName, token, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onStartCommand(Intent proxyIntent, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onStartCommand(proxyIntent, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public UnbindRecord onServiceUnbind(Intent proxyIntent, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.onServiceUnbind(proxyIntent, userId);
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onServiceDestroy(Intent proxyIntent, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onServiceDestroy(proxyIntent, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public IBinder acquireContentProviderClient(ProviderInfo providerInfo) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.acquireContentProviderClient(providerInfo);
            }
            Slog.w(TAG, "ActivityManager service is null for acquireContentProviderClient");
            return null;
        } catch (DeadObjectException e) {
            Slog.w(TAG, "ActivityManager service died during acquireContentProviderClient, clearing cache", e);
            clearServiceCache();
            return null;
        } catch (RemoteException e2) {
            Slog.e(TAG, "RemoteException in acquireContentProviderClient", e2);
            return null;
        } catch (Exception e3) {
            Slog.e(TAG, "Unexpected error in acquireContentProviderClient", e3);
            return null;
        }
    }

    public Intent sendBroadcast(Intent intent, String resolvedType, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.sendBroadcast(intent, resolvedType, userId);
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IBinder peekService(Intent intent, String resolvedType, int userId) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                return service.peekService(intent, resolvedType, userId);
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onActivityCreated(int taskId, IBinder token, IBinder activityRecord) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onActivityCreated(taskId, token, activityRecord);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onActivityResumed(IBinder token) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onActivityResumed(token);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onActivityDestroyed(IBinder token) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onActivityDestroyed(token);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onFinishActivity(IBinder token) {
        try {
            IBActivityManagerService service = getService();
            if (service != null) {
                service.onFinishActivity(token);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public RunningAppProcessInfo getRunningAppProcesses(String callerPackage, int userId) throws RemoteException {
        try {
            return getService().getRunningAppProcesses(callerPackage, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RunningServiceInfo getRunningServices(String callerPackage, int userId) throws RemoteException {
        try {
            return getService().getRunningServices(callerPackage, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void scheduleBroadcastReceiver(Intent intent, PendingResultData pendingResultData, int userId) throws RemoteException {
        getService().scheduleBroadcastReceiver(intent, pendingResultData, userId);
    }

    public void finishBroadcast(PendingResultData data) {
        try {
            getService().finishBroadcast(data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getCallingPackage(IBinder token, int userId) {
        try {
            return getService().getCallingPackage(token, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ComponentName getCallingActivity(IBinder token, int userId) {
        try {
            return getService().getCallingActivity(token, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getIntentSender(IBinder target, String packageName, int uid) {
        try {
            getService().getIntentSender(target, packageName, uid, BActivityThread.getUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getPackageForIntentSender(IBinder target) {
        try {
            return getService().getPackageForIntentSender(target, BActivityThread.getUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUidForIntentSender(IBinder target) {
        try {
            return getService().getUidForIntentSender(target, BActivityThread.getUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
