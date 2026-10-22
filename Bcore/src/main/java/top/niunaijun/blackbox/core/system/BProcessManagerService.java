package top.niunaijun.blackbox.core.system;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.IBActivityThread;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.notification.BNotificationManagerService;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.core.system.user.BUserHandle;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.ApplicationThreadCompat;
import top.niunaijun.blackbox.utils.compat.BundleCompat;
import top.niunaijun.blackbox.utils.provider.ProviderCall;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/BProcessManagerService.class */
public class BProcessManagerService implements ISystemService {
    public static final String TAG = "BProcessManager";
    public static BProcessManagerService sBProcessManagerService = new BProcessManagerService();
    private final Map<Integer, Map<String, ProcessRecord>> mProcessMap = new HashMap();
    private final List<ProcessRecord> mPidsSelfLocked = new ArrayList();
    private final Object mProcessLock = new Object();

    public static BProcessManagerService get() {
        return sBProcessManagerService;
    }

    public ProcessRecord startProcessLocked(String packageName, String processName, int userId, int bpid, int callingPid) {
        ApplicationInfo info = BPackageManagerService.get().getApplicationInfo(packageName, 0, userId);
        if (info == null) {
            return null;
        }
        // Single user container: the sandbox uid is the bare app id.
        int buid = BPackageManagerService.get().getAppId(packageName);
        synchronized (this.mProcessLock) {
            Map<String, ProcessRecord> bProcess = this.mProcessMap.get(Integer.valueOf(buid));
            if (bProcess == null) {
                bProcess = new HashMap();
            }
            if (bpid == -1) {
                ProcessRecord app = bProcess.get(processName);
                if (app != null) {
                    if (app.initLock != null) {
                        app.initLock.block();
                    }
                    if (app.bActivityThread != null) {
                        return app;
                    }
                }
                bpid = getUsingBPidL();
                Slog.d(TAG, "init bUid = " + buid + ", bPid = " + bpid);
            }
            if (bpid == -1) {
                throw new RuntimeException("No processes available");
            }
            ProcessRecord app2 = new ProcessRecord(info, processName);
            app2.uid = Process.myUid();
            app2.bpid = bpid;
            app2.buid = BPackageManagerService.get().getAppId(packageName);
            app2.callingBUid = getBUidByPidOrPackageName(callingPid, packageName);
            app2.userId = userId;
            bProcess.put(processName, app2);
            this.mPidsSelfLocked.add(app2);
            synchronized (this.mProcessMap) {
                this.mProcessMap.put(Integer.valueOf(buid), bProcess);
            }
            if (!initAppProcessL(app2)) {
                bProcess.remove(processName);
                this.mPidsSelfLocked.remove(app2);
                app2 = null;
            } else {
                app2.pid = getPid(BlackBoxCore.getContext(), ProxyManifest.getProcessName(app2.bpid));
            }
            return app2;
        }
    }

    private int getUsingBPidL() {
        ActivityManager manager = (ActivityManager) BlackBoxCore.getContext().getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
        Set<Integer> usingPs = new HashSet<>();
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            int i = parseBPid(runningAppProcess.processName);
            usingPs.add(Integer.valueOf(i));
        }
        for (int i2 = 0; i2 < 50; i2++) {
            if (!usingPs.contains(Integer.valueOf(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public void restartAppProcess(String packageName, String processName, int userId) {
        synchronized (this.mProcessLock) {
            Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            ProcessRecord app = findProcessByPid(callingPid);
            if (app == null) {
                String stubProcessName = getProcessName(BlackBoxCore.getContext(), callingPid);
                int bpid = parseBPid(stubProcessName);
                startProcessLocked(packageName, processName, userId, bpid, callingPid);
            }
        }
    }

    private int parseBPid(String stubProcessName) {
        if (stubProcessName == null) {
            return -1;
        }
        String prefix = BlackBoxCore.getHostPkg() + ":p";
        if (stubProcessName.startsWith(prefix)) {
            try {
                return Integer.parseInt(stubProcessName.substring(prefix.length()));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    private boolean initAppProcessL(ProcessRecord record) {
        Log.d(TAG, "initProcess: " + record.processName);
        AppConfig appConfig = record.getClientConfig();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConfig.KEY, appConfig);
        Bundle init = ProviderCall.callSafely(record.getProviderAuthority(), "_Black_|_init_process_", null, bundle);
        IBinder appThread = BundleCompat.getBinder(init, "_Black_|_client_");
        if (appThread == null || !appThread.isBinderAlive()) {
            return false;
        }
        attachClientL(record, appThread);
        createProc(record);
        return true;
    }

    private void attachClientL(final ProcessRecord app, final IBinder appThread) {
        IBActivityThread activityThread = IBActivityThread.Stub.asInterface(appThread);
        if (activityThread == null) {
            app.kill();
            return;
        }
        try {
            appThread.linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.core.system.BProcessManagerService.1
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    Log.d(BProcessManagerService.TAG, "App Died: " + app.processName);
                    appThread.unlinkToDeath(this, 0);
                    BProcessManagerService.this.onProcessDie(app);
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        app.bActivityThread = activityThread;
        try {
            app.appThread = ApplicationThreadCompat.asInterface(activityThread.getActivityThread());
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        app.initLock.open();
    }

    public void onProcessDie(ProcessRecord record) {
        synchronized (this.mProcessLock) {
            record.kill();
            Map<String, ProcessRecord> process = this.mProcessMap.get(Integer.valueOf(record.buid));
            if (process != null) {
                process.remove(record.processName);
                if (process.isEmpty()) {
                    this.mProcessMap.remove(Integer.valueOf(record.buid));
                }
            }
            this.mPidsSelfLocked.remove(record);
            removeProc(record);
            BNotificationManagerService.get().deletePackageNotification(record.getPackageName(), record.userId);
        }
    }

    public ProcessRecord findProcessRecord(String packageName, String processName, int userId) {
        synchronized (this.mProcessMap) {
            int buid = BPackageManagerService.get().getAppId(packageName);
            Map<String, ProcessRecord> processRecordMap = this.mProcessMap.get(Integer.valueOf(buid));
            if (processRecordMap == null) {
                return null;
            }
            return processRecordMap.get(processName);
        }
    }

    public void killAllByPackageName(String str) {
        synchronized (this.mProcessLock) {
            synchronized (this.mPidsSelfLocked) {
                ArrayList arrayList = new ArrayList(this.mPidsSelfLocked);
                int appId = BPackageManagerService.get().getAppId(str);
                for (ProcessRecord processRecord : this.mPidsSelfLocked) {
                    if (appId == BUserHandle.getAppId(processRecord.buid)) {
                        this.mProcessMap.remove(Integer.valueOf(processRecord.buid));
                        arrayList.remove(processRecord);
                        processRecord.kill();
                    }
                }
                this.mPidsSelfLocked.clear();
                this.mPidsSelfLocked.addAll(arrayList);
            }
        }
    }

    public void killPackageAsUser(String packageName, int userId) {
        synchronized (this.mProcessLock) {
            int buid = BPackageManagerService.get().getAppId(packageName);
            Map<String, ProcessRecord> process = this.mProcessMap.get(Integer.valueOf(buid));
            if (process == null) {
                return;
            }
            for (ProcessRecord value : process.values()) {
                value.kill();
                this.mPidsSelfLocked.remove(value);
            }
            this.mProcessMap.remove(Integer.valueOf(buid));
        }
    }

    public List<ProcessRecord> getPackageProcessAsUser(String packageName, int userId) {
        synchronized (this.mProcessMap) {
            int buid = BPackageManagerService.get().getAppId(packageName);
            Map<String, ProcessRecord> process = this.mProcessMap.get(Integer.valueOf(buid));
            if (process == null) {
                return new ArrayList();
            }
            return new ArrayList(process.values());
        }
    }

    public int getBUidByPidOrPackageName(int pid, String packageName) {
        ProcessRecord callingProcess = findProcessByPid(pid);
        if (callingProcess == null) {
            return BPackageManagerService.get().getAppId(packageName);
        }
        return BUserHandle.getAppId(callingProcess.buid);
    }

    public int getUserIdByCallingPid(int callingPid) {
        ProcessRecord callingProcess = findProcessByPid(callingPid);
        if (callingProcess == null) {
            return 0;
        }
        return callingProcess.userId;
    }

    public ProcessRecord findProcessByPid(int pid) {
        synchronized (this.mPidsSelfLocked) {
            for (ProcessRecord processRecord : this.mPidsSelfLocked) {
                if (processRecord.pid == pid) {
                    return processRecord;
                }
            }
            return null;
        }
    }

    private static String getProcessName(Context context, int pid) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
            if (info.pid == pid) {
                processName = info.processName;
                break;
            }
        }
        if (processName == null) {
            throw new RuntimeException("processName = null");
        }
        return processName;
    }

    public static int getPid(Context context, String processName) {
        try {
            ActivityManager manager = (ActivityManager) context.getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
                if (runningAppProcess.processName.equals(processName)) {
                    return runningAppProcess.pid;
                }
            }
            return -1;
        } catch (Throwable e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void createProc(ProcessRecord record) {
        File cmdline = new File(BEnvironment.getProcDir(record.bpid), "cmdline");
        try {
            FileUtils.writeToFile(record.processName.getBytes(), cmdline);
        } catch (IOException e) {
        }
    }

    private static void removeProc(ProcessRecord record) {
        FileUtils.deleteDir(BEnvironment.getProcDir(record.bpid));
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        FileUtils.deleteDir(BEnvironment.getProcDir());
    }
}
