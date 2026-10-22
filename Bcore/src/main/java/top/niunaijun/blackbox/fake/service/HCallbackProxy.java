package top.niunaijun.blackbox.fake.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import androidx.annotation.NonNull;
import black.android.app.ActivityThreadActivityClientRecordContext;
import black.android.app.BRActivityClient;
import black.android.app.BRActivityClientActivityClientControllerSingleton;
import black.android.app.BRActivityManagerNative;
import black.android.app.BRActivityThread;
import black.android.app.BRActivityThreadActivityClientRecord;
import black.android.app.BRActivityThreadCreateServiceData;
import black.android.app.BRActivityThreadH;
import black.android.app.BRIActivityManager;
import black.android.app.servertransaction.BRClientTransaction;
import black.android.app.servertransaction.BRLaunchActivityItem;
import black.android.app.servertransaction.LaunchActivityItemContext;
import black.android.os.BRHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.fake.hook.IInjectHook;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.proxy.record.ProxyActivityRecord;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/HCallbackProxy.class */
public class HCallbackProxy implements IInjectHook, Handler.Callback {
    public static final String TAG = "HCallbackStub";
    private Handler.Callback mOtherCallback;
    private AtomicBoolean mBeing = new AtomicBoolean(false);
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !HCallbackProxy.class.desiredAssertionStatus();
    }

    private Handler.Callback getHCallback() {
        return BRHandler.get(getH()).mCallback();
    }

    private Handler getH() {
        Object currentActivityThread = BlackBoxCore.mainThread();
        return BRActivityThread.get(currentActivityThread).mH();
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public void injectHook() {
        this.mOtherCallback = getHCallback();
        if (this.mOtherCallback != null && (this.mOtherCallback == this || this.mOtherCallback.getClass().getName().equals(getClass().getName()))) {
            this.mOtherCallback = null;
        }
        BRHandler.get(getH())._set_mCallback(this);
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        Handler.Callback hCallback = getHCallback();
        return (hCallback == null || hCallback == this) ? false : true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@NonNull Message msg) {
        if (this.mBeing.getAndSet(true)) {
            return false;
        }
        try {
            if (BuildCompat.isPie()) {
                if (msg.what == BRActivityThreadH.get().EXECUTE_TRANSACTION().intValue() && handleLaunchActivity(msg.obj)) {
                    getH().sendMessageAtFrontOfQueue(Message.obtain(msg));
                    return true;
                }
            } else if (msg.what == BRActivityThreadH.get().LAUNCH_ACTIVITY().intValue() && handleLaunchActivity(msg.obj)) {
                getH().sendMessageAtFrontOfQueue(Message.obtain(msg));
                return true;
            }
            if (msg.what == BRActivityThreadH.get().CREATE_SERVICE().intValue()) {
                return handleCreateService(msg.obj);
            }
            if (this.mOtherCallback != null) {
                return this.mOtherCallback.handleMessage(msg);
            }
            return false;
        } finally {
            this.mBeing.set(false);
        }
    }

    private Object getLaunchActivityItem(Object clientTransaction) {
        List<Object> mActivityCallbacks = BRClientTransaction.get(clientTransaction).mActivityCallbacks();
        if (mActivityCallbacks == null) {
            Slog.e(TAG, "mActivityCallbacks is null for clientTransaction: " + clientTransaction);
            return null;
        }
        for (Object obj : mActivityCallbacks) {
            if (BRLaunchActivityItem.getRealClass().getName().equals(obj.getClass().getCanonicalName())) {
                return obj;
            }
        }
        return null;
    }

    private boolean handleLaunchActivity(Object client) {
        Object r;
        Intent intent;
        IBinder token;
        ProxyActivityRecord stubRecord;
        ActivityInfo activityInfo;
        if (BuildCompat.isPie()) {
            r = getLaunchActivityItem(client);
        } else {
            r = client;
        }
        if (r == null) {
            return false;
        }
        if (BuildCompat.isPie()) {
            intent = BRLaunchActivityItem.get(r).mIntent();
            token = BRClientTransaction.get(client).mActivityToken();
        } else {
            ActivityThreadActivityClientRecordContext clientRecordContext = BRActivityThreadActivityClientRecord.get(r);
            intent = clientRecordContext.intent();
            token = clientRecordContext.token();
        }
        if (intent != null && (activityInfo = (stubRecord = ProxyActivityRecord.create(intent)).mActivityInfo) != null) {
            if (BActivityThread.getAppConfig() == null) {
                BlackBoxCore.getBActivityManager().restartProcess(activityInfo.packageName, activityInfo.processName, stubRecord.mUserId);
                Intent launchIntentForPackage = BlackBoxCore.getBPackageManager().getLaunchIntentForPackage(activityInfo.packageName, stubRecord.mUserId);
                intent.setExtrasClassLoader(getClass().getClassLoader());
                ProxyActivityRecord.saveStub(intent, launchIntentForPackage, stubRecord.mActivityInfo, stubRecord.mActivityRecord, stubRecord.mUserId);
                if (BuildCompat.isPie()) {
                    LaunchActivityItemContext launchActivityItemContext = BRLaunchActivityItem.get(r);
                    launchActivityItemContext._set_mIntent(intent);
                    launchActivityItemContext._set_mInfo(activityInfo);
                    return true;
                }
                ActivityThreadActivityClientRecordContext clientRecordContext2 = BRActivityThreadActivityClientRecord.get(r);
                clientRecordContext2._set_intent(intent);
                clientRecordContext2._set_activityInfo(activityInfo);
                return true;
            }
            if (!BActivityThread.currentActivityThread().isInit()) {
                BActivityThread.currentActivityThread().bindApplication(activityInfo.packageName, activityInfo.processName);
                return true;
            }
            int taskId = BRIActivityManager.get(BRActivityManagerNative.get().getDefault()).getTaskForActivity(token, false).intValue();
            BlackBoxCore.getBActivityManager().onActivityCreated(taskId, token, stubRecord.mActivityRecord);
            if (BuildCompat.isTiramisu()) {
                LaunchActivityItemContext launchActivityItemContext2 = BRLaunchActivityItem.get(r);
                launchActivityItemContext2._set_mIntent(stubRecord.mTarget);
                launchActivityItemContext2._set_mInfo(activityInfo);
                return false;
            }
            if (BuildCompat.isS()) {
                Object record = BRActivityThread.get(BlackBoxCore.mainThread()).getLaunchingActivity(token);
                ActivityThreadActivityClientRecordContext clientRecordContext3 = BRActivityThreadActivityClientRecord.get(record);
                clientRecordContext3._set_intent(stubRecord.mTarget);
                clientRecordContext3._set_activityInfo(activityInfo);
                clientRecordContext3._set_packageInfo(BActivityThread.currentActivityThread().getPackageInfo());
                checkActivityClient();
                return false;
            }
            if (BuildCompat.isPie()) {
                LaunchActivityItemContext launchActivityItemContext3 = BRLaunchActivityItem.get(r);
                launchActivityItemContext3._set_mIntent(stubRecord.mTarget);
                launchActivityItemContext3._set_mInfo(activityInfo);
                return false;
            }
            ActivityThreadActivityClientRecordContext clientRecordContext4 = BRActivityThreadActivityClientRecord.get(r);
            clientRecordContext4._set_intent(stubRecord.mTarget);
            clientRecordContext4._set_activityInfo(activityInfo);
            return false;
        }
        return false;
    }

    private boolean handleCreateService(Object data) {
        if (BActivityThread.getAppConfig() != null) {
            String appPackageName = BActivityThread.getAppPackageName();
            if (!$assertionsDisabled && appPackageName == null) {
                throw new AssertionError();
            }
            ServiceInfo serviceInfo = BRActivityThreadCreateServiceData.get(data).info();
            if (!serviceInfo.name.equals(ProxyManifest.getProxyService(BActivityThread.getAppPid())) && !serviceInfo.name.equals(ProxyManifest.getProxyJobService(BActivityThread.getAppPid()))) {
                Slog.d(TAG, "handleCreateService: " + data);
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(appPackageName, serviceInfo.name));
                BlackBoxCore.getBActivityManager().startService(intent, null, false, BActivityThread.getUserId());
                return true;
            }
            return false;
        }
        return false;
    }

    private void checkActivityClient() {
        try {
            Object activityClientController = BRActivityClient.get().getActivityClientController();
            if (!(activityClientController instanceof Proxy)) {
                IActivityClientProxy iActivityClientProxy = new IActivityClientProxy(activityClientController);
                iActivityClientProxy.onlyProxy(true);
                iActivityClientProxy.injectHook();
                Object instance = BRActivityClient.get().getInstance();
                Object o = BRActivityClient.get(instance).INTERFACE_SINGLETON();
                BRActivityClientActivityClientControllerSingleton.get(o)._set_mKnownInstance(iActivityClientProxy.getProxyInvocation());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
