package top.niunaijun.blackbox.core.system.am;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.core.system.ProcessRecord;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.UnbindRecord;
import top.niunaijun.blackbox.entity.am.PendingResultData;
import top.niunaijun.blackbox.entity.am.ReceiverData;
import top.niunaijun.blackbox.entity.am.RunningAppProcessInfo;
import top.niunaijun.blackbox.entity.am.RunningServiceInfo;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/BActivityManagerService.class */
public class BActivityManagerService extends IBActivityManagerService.Stub implements ISystemService {
    public static final String TAG = "BActivityManagerService";
    private static final BActivityManagerService sService = new BActivityManagerService();
    private final Map<Integer, UserSpace> mUserSpace = new HashMap();
    private final BPackageManagerService mPms = BPackageManagerService.get();
    private final BroadcastManager mBroadcastManager = BroadcastManager.startSystem(this, this.mPms);

    public static BActivityManagerService get() {
        return sService;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public ComponentName startService(Intent intent, String resolvedType, boolean requireForeground, int userId) {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            userSpace.mActiveServices.startService(intent, resolvedType, requireForeground, userId);
        }
        return null;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        ProcessRecord processRecord = BProcessManagerService.get().startProcessLocked(providerInfo.packageName, providerInfo.processName, BProcessManagerService.get().getUserIdByCallingPid(callingPid), -1, Binder.getCallingPid());
        if (processRecord == null) {
            throw new RuntimeException("Unable to create process " + providerInfo.name);
        }
        try {
            return processRecord.bActivityThread.acquireContentProviderClient(providerInfo);
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public Intent sendBroadcast(Intent intent, String resolvedType, int userId) throws RemoteException {
        List<ResolveInfo> resolves = BPackageManagerService.get().queryBroadcastReceivers(intent, 128, resolvedType, userId);
        for (ResolveInfo resolve : resolves) {
            ProcessRecord processRecord = BProcessManagerService.get().findProcessRecord(resolve.activityInfo.packageName, resolve.activityInfo.processName, userId);
            if (processRecord != null) {
                try {
                    processRecord.bActivityThread.bindApplication();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent shadow = new Intent();
        shadow.setPackage(BlackBoxCore.getHostPkg());
        shadow.setComponent(null);
        shadow.setAction(intent.getAction());
        return shadow;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public IBinder peekService(Intent intent, String resolvedType, int userId) throws RemoteException {
        IBinder iBinderPeekService;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            iBinderPeekService = userSpace.mActiveServices.peekService(intent, resolvedType, userId);
        }
        return iBinderPeekService;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onActivityCreated(int taskId, IBinder token, IBinder activityRecord) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        ProcessRecord process = BProcessManagerService.get().findProcessByPid(callingPid);
        if (process == null) {
            return;
        }
        ActivityRecord record = (ActivityRecord) activityRecord;
        UserSpace userSpace = getOrCreateSpaceLocked(process.userId);
        synchronized (userSpace.mStack) {
            userSpace.mStack.onActivityCreated(process, taskId, token, record);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onActivityResumed(IBinder token) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        ProcessRecord process = BProcessManagerService.get().findProcessByPid(callingPid);
        if (process == null) {
            return;
        }
        UserSpace userSpace = getOrCreateSpaceLocked(process.userId);
        synchronized (userSpace.mStack) {
            userSpace.mStack.onActivityResumed(process.userId, token);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onActivityDestroyed(IBinder token) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        ProcessRecord process = BProcessManagerService.get().findProcessByPid(callingPid);
        if (process == null) {
            return;
        }
        UserSpace userSpace = getOrCreateSpaceLocked(process.userId);
        synchronized (userSpace.mStack) {
            userSpace.mStack.onActivityDestroyed(process.userId, token);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onFinishActivity(IBinder token) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        ProcessRecord process = BProcessManagerService.get().findProcessByPid(callingPid);
        if (process == null) {
            return;
        }
        UserSpace userSpace = getOrCreateSpaceLocked(process.userId);
        synchronized (userSpace.mStack) {
            userSpace.mStack.onFinishActivity(process.userId, token);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public RunningAppProcessInfo getRunningAppProcesses(String callerPackage, int userId) throws RemoteException {
        ActivityManager manager = (ActivityManager) BlackBoxCore.getContext().getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
        Map<Integer, ActivityManager.RunningAppProcessInfo> runningProcessMap = new HashMap<>();
        for (ActivityManager.RunningAppProcessInfo runningProcess : runningAppProcesses) {
            runningProcessMap.put(Integer.valueOf(runningProcess.pid), runningProcess);
        }
        List<ProcessRecord> packageProcessAsUser = BProcessManagerService.get().getPackageProcessAsUser(callerPackage, userId);
        RunningAppProcessInfo appProcessInfo = new RunningAppProcessInfo();
        for (ProcessRecord processRecord : packageProcessAsUser) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningProcessMap.get(Integer.valueOf(processRecord.pid));
            if (runningAppProcessInfo != null) {
                runningAppProcessInfo.processName = processRecord.processName;
                appProcessInfo.mAppProcessInfoList.add(runningAppProcessInfo);
            }
        }
        return appProcessInfo;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public RunningServiceInfo getRunningServices(String callerPackage, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        return userSpace.mActiveServices.getRunningServiceInfo(callerPackage, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void scheduleBroadcastReceiver(Intent intent, PendingResultData pendingResultData, int userId) throws RemoteException {
        List<ResolveInfo> resolves = BPackageManagerService.get().queryBroadcastReceivers(intent, 128, null, userId);
        if (resolves.isEmpty()) {
            pendingResultData.build().finish();
            Slog.d(TAG, "scheduleBroadcastReceiver empty");
            return;
        }
        this.mBroadcastManager.sendBroadcast(pendingResultData);
        for (ResolveInfo resolve : resolves) {
            ProcessRecord processRecord = BProcessManagerService.get().findProcessRecord(resolve.activityInfo.packageName, resolve.activityInfo.processName, userId);
            if (processRecord != null) {
                ReceiverData data = new ReceiverData();
                data.intent = intent;
                data.activityInfo = resolve.activityInfo;
                data.data = pendingResultData;
                processRecord.bActivityThread.scheduleReceiver(data);
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void finishBroadcast(PendingResultData data) throws RemoteException {
        this.mBroadcastManager.finishBroadcast(data);
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public String getCallingPackage(IBinder token, int userId) throws RemoteException {
        String callingPackage;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mStack) {
            callingPackage = userSpace.mStack.getCallingPackage(token, userId);
        }
        return callingPackage;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public ComponentName getCallingActivity(IBinder token, int userId) throws RemoteException {
        ComponentName callingActivity;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mStack) {
            callingActivity = userSpace.mStack.getCallingActivity(token, userId);
        }
        return callingActivity;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void getIntentSender(IBinder target, String packageName, int uid, int userId) {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mIntentSenderRecords) {
            PendingIntentRecord record = new PendingIntentRecord();
            record.uid = uid;
            record.packageName = packageName;
            userSpace.mIntentSenderRecords.put(target, record);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public String getPackageForIntentSender(IBinder target, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mIntentSenderRecords) {
            PendingIntentRecord record = userSpace.mIntentSenderRecords.get(target);
            if (record != null) {
                return record.packageName;
            }
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public int getUidForIntentSender(IBinder target, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mIntentSenderRecords) {
            PendingIntentRecord record = userSpace.mIntentSenderRecords.get(target);
            if (record != null) {
                return record.uid;
            }
            return -1;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onStartCommand(Intent intent, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            userSpace.mActiveServices.onStartCommand(intent, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public UnbindRecord onServiceUnbind(Intent proxyIntent, int userId) throws RemoteException {
        UnbindRecord unbindRecordOnServiceUnbind;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            unbindRecordOnServiceUnbind = userSpace.mActiveServices.onServiceUnbind(proxyIntent, userId);
        }
        return unbindRecordOnServiceUnbind;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void onServiceDestroy(Intent proxyIntent, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            userSpace.mActiveServices.onServiceDestroy(proxyIntent, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public int stopService(Intent intent, String resolvedType, int userId) {
        int iStopService;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            iStopService = userSpace.mActiveServices.stopService(intent, resolvedType, userId);
        }
        return iStopService;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public Intent bindService(Intent service, IBinder binder, String resolvedType, int userId) throws RemoteException {
        Intent intentBindService;
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            intentBindService = userSpace.mActiveServices.bindService(service, binder, resolvedType, userId);
        }
        return intentBindService;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void unbindService(IBinder binder, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            userSpace.mActiveServices.unbindService(binder, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void stopServiceToken(ComponentName className, IBinder token, int userId) throws RemoteException {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mActiveServices) {
            userSpace.mActiveServices.stopServiceToken(className, token, userId);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public AppConfig initProcess(String packageName, String processName, int userId) throws RemoteException {
        ProcessRecord processRecord = BProcessManagerService.get().startProcessLocked(packageName, processName, userId, -1, Binder.getCallingPid());
        if (processRecord == null) {
            return null;
        }
        return processRecord.getClientConfig();
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void restartProcess(String packageName, String processName, int userId) throws RemoteException {
        BProcessManagerService.get().restartAppProcess(packageName, processName, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public void startActivity(Intent intent, int userId) {
        UserSpace userSpace = getOrCreateSpaceLocked(userId);
        synchronized (userSpace.mStack) {
            userSpace.mStack.startActivityLocked(userId, intent, null, null, null, -1, -1, null);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public int startActivityAms(int userId, Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode, int flags, Bundle options) throws RemoteException {
        int iStartActivityLocked;
        UserSpace space = getOrCreateSpaceLocked(userId);
        synchronized (space.mStack) {
            iStartActivityLocked = space.mStack.startActivityLocked(userId, intent, resolvedType, resultTo, resultWho, requestCode, flags, options);
        }
        return iStartActivityLocked;
    }

    @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
    public int startActivities(int userId, Intent[] intent, String[] resolvedType, IBinder resultTo, Bundle options) throws RemoteException {
        int iStartActivitiesLocked;
        UserSpace space = getOrCreateSpaceLocked(userId);
        synchronized (space.mStack) {
            iStartActivitiesLocked = space.mStack.startActivitiesLocked(userId, intent, resolvedType, resultTo, options);
        }
        return iStartActivitiesLocked;
    }

    private UserSpace getOrCreateSpaceLocked(int userId) {
        synchronized (this.mUserSpace) {
            UserSpace userSpace = this.mUserSpace.get(Integer.valueOf(userId));
            if (userSpace != null) {
                return userSpace;
            }
            UserSpace userSpace2 = new UserSpace();
            this.mUserSpace.put(Integer.valueOf(userId), userSpace2);
            return userSpace2;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        this.mBroadcastManager.startup();
    }
}
