package top.niunaijun.blackbox.fake.service;

import android.app.job.JobInfo;
import android.os.IBinder;
import android.os.RemoteException;
import black.android.app.job.BRIJobSchedulerStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IJobServiceProxy.class */
public class IJobServiceProxy extends BinderInvocationStub {
    public static final String TAG = "JobServiceStub";

    public IJobServiceProxy() {
        super(BRServiceManager.get().getService("jobscheduler"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        IBinder jobScheduler = BRServiceManager.get().getService("jobscheduler");
        return BRIJobSchedulerStub.get().asInterface(jobScheduler);
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("jobscheduler");
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IJobServiceProxy$Schedule.class */
    @ProxyMethod("schedule")
    public static class Schedule extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String message;
            if (args != null) {
                try {
                    if (args.length != 0) {
                        if (args[0] == null) {
                            Slog.w(IJobServiceProxy.TAG, "Schedule: args[0] is null, returning RESULT_FAILURE");
                            return 0;
                        }
                        if (!(args[0] instanceof JobInfo)) {
                            Slog.w(IJobServiceProxy.TAG, "Schedule: args[0] is not JobInfo: " + args[0].getClass().getSimpleName());
                            return method.invoke(who, args);
                        }
                        JobInfo jobInfo = (JobInfo) args[0];
                        JobInfo proxyJobInfo = BlackBoxCore.getBJobManager().schedule(jobInfo);
                        if (proxyJobInfo != null) {
                            args[0] = proxyJobInfo;
                            return method.invoke(who, args);
                        }
                        Slog.w(IJobServiceProxy.TAG, "Schedule: Failed to create proxy JobInfo, returning RESULT_FAILURE");
                        return 0;
                    }
                } catch (Exception e) {
                    Slog.e(IJobServiceProxy.TAG, "Schedule: Error processing job", e);
                    if ((e.getCause() instanceof RemoteException) && (message = e.getCause().getMessage()) != null && message.contains("cannot schedule job")) {
                        Slog.w(IJobServiceProxy.TAG, "UID validation failed for job scheduling, returning RESULT_FAILURE: " + message);
                        return 0;
                    }
                    try {
                        return method.invoke(who, args);
                    } catch (Exception fallbackException) {
                        Slog.e(IJobServiceProxy.TAG, "Schedule: Fallback also failed", fallbackException);
                        return 0;
                    }
                }
            }
            Slog.w(IJobServiceProxy.TAG, "Schedule: No arguments provided, returning RESULT_FAILURE");
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IJobServiceProxy$Cancel.class */
    @ProxyMethod("cancel")
    public static class Cancel extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Integer jobId = (Integer) args[0];
                if (jobId == null) {
                    Slog.w(IJobServiceProxy.TAG, "Cancel: JobId is null");
                    return method.invoke(who, args);
                }
                args[0] = Integer.valueOf(BlackBoxCore.getBJobManager().cancel(BActivityThread.getAppConfig().processName, jobId.intValue()));
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.e(IJobServiceProxy.TAG, "Cancel: Error canceling job", e);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IJobServiceProxy$CancelAll.class */
    @ProxyMethod("cancelAll")
    public static class CancelAll extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                BlackBoxCore.getBJobManager().cancelAll(BActivityThread.getAppConfig().processName);
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.e(IJobServiceProxy.TAG, "CancelAll: Error canceling all jobs", e);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IJobServiceProxy$Enqueue.class */
    @ProxyMethod("enqueue")
    public static class Enqueue extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String message;
            if (args != null) {
                try {
                    if (args.length != 0) {
                        if (args[0] == null) {
                            Slog.w(IJobServiceProxy.TAG, "Enqueue: args[0] is null, returning RESULT_FAILURE");
                            return 0;
                        }
                        if (!(args[0] instanceof JobInfo)) {
                            Slog.w(IJobServiceProxy.TAG, "Enqueue: args[0] is not JobInfo: " + args[0].getClass().getSimpleName());
                            return method.invoke(who, args);
                        }
                        JobInfo jobInfo = (JobInfo) args[0];
                        JobInfo proxyJobInfo = BlackBoxCore.getBJobManager().schedule(jobInfo);
                        if (proxyJobInfo != null) {
                            args[0] = proxyJobInfo;
                            return method.invoke(who, args);
                        }
                        Slog.w(IJobServiceProxy.TAG, "Enqueue: Failed to create proxy JobInfo, returning RESULT_FAILURE");
                        return 0;
                    }
                } catch (Exception e) {
                    Slog.e(IJobServiceProxy.TAG, "Enqueue: Error processing job", e);
                    if ((e.getCause() instanceof RemoteException) && (message = e.getCause().getMessage()) != null && message.contains("cannot schedule job")) {
                        Slog.w(IJobServiceProxy.TAG, "UID validation failed for job enqueuing, returning RESULT_FAILURE: " + message);
                        return 0;
                    }
                    try {
                        return method.invoke(who, args);
                    } catch (Exception fallbackException) {
                        Slog.e(IJobServiceProxy.TAG, "Enqueue: Fallback also failed", fallbackException);
                        return 0;
                    }
                }
            }
            Slog.w(IJobServiceProxy.TAG, "Enqueue: No arguments provided, returning RESULT_FAILURE");
            return 0;
        }
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }
}
