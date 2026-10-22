package top.niunaijun.blackbox.fake.service;

import android.app.ActivityManager;
import android.app.IServiceConnection;
import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import black.android.app.BRActivityManagerNative;
import black.android.app.BRActivityManagerOreo;
import black.android.app.BRLoadedApkReceiverDispatcher;
import black.android.app.BRLoadedApkReceiverDispatcherInnerReceiver;
import black.android.app.BRLoadedApkServiceDispatcher;
import black.android.app.BRLoadedApkServiceDispatcherInnerConnection;
import black.android.content.BRContentProviderNative;
import black.android.content.pm.BRUserInfo;
import black.android.util.BRSingleton;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.env.AppSystemEnv;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.am.RunningAppProcessInfo;
import top.niunaijun.blackbox.entity.am.RunningServiceInfo;
import top.niunaijun.blackbox.fake.delegate.ContentProviderDelegate;
import top.niunaijun.blackbox.fake.delegate.InnerReceiverDelegate;
import top.niunaijun.blackbox.fake.delegate.ServiceConnectionDelegate;
import top.niunaijun.blackbox.fake.frameworks.BActivityManager;
import top.niunaijun.blackbox.fake.frameworks.BPackageManager;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.fake.hook.ScanClass;
import top.niunaijun.blackbox.fake.service.base.PkgMethodProxy;
import top.niunaijun.blackbox.fake.service.context.providers.ContentProviderStub;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.proxy.record.ProxyBroadcastRecord;
import top.niunaijun.blackbox.proxy.record.ProxyPendingRecord;
import top.niunaijun.blackbox.utils.GaidManager;
import top.niunaijun.blackbox.utils.MethodParameterUtils;
import top.niunaijun.blackbox.utils.Reflector;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.BuildCompat;
import top.niunaijun.blackbox.utils.compat.ParceledListSliceCompat;
import top.niunaijun.blackbox.utils.compat.TaskDescriptionCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy.class */
@ScanClass({ActivityManagerCommonProxy.class})
public class IActivityManagerProxy extends ClassInvocationStub {
    public static final String TAG = "ActivityManagerStub";

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$BroadcastIntentWithFeature.class */
    @ProxyMethod("broadcastIntentWithFeature")
    public static class BroadcastIntentWithFeature extends BroadcastIntent {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetIntentSenderWithFeature.class */
    @ProxyMethod("getIntentSenderWithFeature")
    public static class GetIntentSenderWithFeature extends GetIntentSender {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetIntentSenderWithSourceToken.class */
    @ProxyMethod("getIntentSenderWithSourceToken")
    public static class GetIntentSenderWithSourceToken extends GetIntentSender {
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        Object iActivityManager = null;
        if (BuildCompat.isOreo()) {
            iActivityManager = BRActivityManagerOreo.get().IActivityManagerSingleton();
        } else if (BuildCompat.isL()) {
            iActivityManager = BRActivityManagerNative.get().gDefault();
        }
        return BRSingleton.get(iActivityManager).get();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object base, Object proxy) {
        Object iActivityManager = null;
        if (BuildCompat.isOreo()) {
            iActivityManager = BRActivityManagerOreo.get().IActivityManagerSingleton();
        } else if (BuildCompat.isL()) {
            iActivityManager = BRActivityManagerNative.get().gDefault();
        }
        BRSingleton.get(iActivityManager)._set_mInstance(proxy);
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return getProxyInvocation() != getWho();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new PkgMethodProxy("getAppStartMode"));
        addMethodHook(new PkgMethodProxy("setAppLockedVerifying"));
        addMethodHook(new PkgMethodProxy("reportJunkFromApp"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return super.invoke(proxy, method, args);
        } catch (SecurityException e) {
            String methodName = method.getName();
            Slog.w(TAG, "ActivityManager invoke: SecurityException in " + methodName + ", returning safe default", e);
            if (methodName.startsWith("set") || methodName.startsWith("update") || methodName.startsWith("get") || methodName.startsWith("query")) {
                return null;
            }
            if (methodName.startsWith("start") || methodName.startsWith("bind")) {
                return false;
            }
            if (methodName.startsWith("stop") || methodName.startsWith("unbind")) {
                return true;
            }
            return null;
        } catch (Exception e2) {
            Slog.e(TAG, "ActivityManager invoke: Unexpected error in " + method.getName(), e2);
            return super.invoke(proxy, method, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetContentProvider.class */
    @ProxyMethod("getContentProvider")
    public static class GetContentProvider extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            try {
                int authIndex = getAuthIndex();
                Object auth = args[authIndex];
                if (auth instanceof String) {
                    if (ProxyManifest.isProxy((String) auth)) {
                        return method.invoke(who, args);
                    }
                    if (BuildCompat.isQ()) {
                        args[1] = BlackBoxCore.getHostPkg();
                    }
                    if (auth.equals("settings") || auth.equals("media") || auth.equals("telephony")) {
                        Object content = method.invoke(who, args);
                        ContentProviderDelegate.update(content, (String) auth);
                        return content;
                    }
                    Log.d(IActivityManagerProxy.TAG, "hook getContentProvider: " + auth);
                    ProviderInfo providerInfo = BlackBoxCore.getBPackageManager().resolveContentProvider((String) auth, 128, BActivityThread.getUserId());
                    if (providerInfo == null) {
                        return null;
                    }
                    Log.d(IActivityManagerProxy.TAG, "hook app: " + auth);
                    IBinder providerBinder = null;
                    if (BActivityThread.getAppPid() != -1) {
                        AppConfig appConfig = BlackBoxCore.getBActivityManager().initProcess(providerInfo.packageName, providerInfo.processName, BActivityThread.getUserId());
                        if (appConfig != null) {
                            if (appConfig.bpid != BActivityThread.getAppPid()) {
                                providerBinder = BlackBoxCore.getBActivityManager().acquireContentProviderClient(providerInfo);
                            }
                            args[authIndex] = ProxyManifest.getProxyAuthorities(appConfig.bpid);
                            args[getUserIndex()] = Integer.valueOf(BlackBoxCore.getHostUserId());
                        } else {
                            Slog.w(IActivityManagerProxy.TAG, "AppConfig is null for package: " + providerInfo.packageName + ", process: " + providerInfo.processName);
                            return null;
                        }
                    }
                    if (providerBinder == null) {
                        return null;
                    }
                    Object content2 = method.invoke(who, args);
                    Reflector.with(content2).field("info").set(providerInfo);
                    Reflector.with(content2).field("provider").set(new ContentProviderStub().wrapper(BRContentProviderNative.get().asInterface(providerBinder), BActivityThread.getAppPackageName()));
                    return content2;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.e(IActivityManagerProxy.TAG, "GetContentProvider: Error in hook", e);
                return null;
            }
        }

        private int getAuthIndex() {
            if (BuildCompat.isQ()) {
                return 2;
            }
            return 1;
        }

        private int getUserIndex() {
            return getAuthIndex() + 1;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$StartService.class */
    @ProxyMethod("startService")
    public static class StartService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Intent intent = (Intent) args[1];
            String resolvedType = (String) args[2];
            ResolveInfo resolveInfo = BlackBoxCore.getBPackageManager().resolveService(intent, 0, resolvedType, BActivityThread.getUserId());
            if (resolveInfo == null) {
                return method.invoke(who, args);
            }
            int requireForegroundIndex = getRequireForeground();
            boolean requireForeground = false;
            if (requireForegroundIndex != -1) {
                requireForeground = ((Boolean) args[requireForegroundIndex]).booleanValue();
            }
            return BlackBoxCore.getBActivityManager().startService(intent, resolvedType, requireForeground, BActivityThread.getUserId());
        }

        public int getRequireForeground() {
            if (BuildCompat.isOreo()) {
                return 3;
            }
            return -1;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$StopService.class */
    @ProxyMethod("stopService")
    public static class StopService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Intent intent = (Intent) args[1];
                String resolvedType = (String) args[2];
                if (intent != null && intent.getComponent() != null) {
                    String servicePackage = intent.getComponent().getPackageName();
                    String currentPackage = BActivityThread.getAppPackageName();
                    if (!servicePackage.equals(currentPackage)) {
                        Slog.w(IActivityManagerProxy.TAG, "StopService: Attempting to stop service from different package: " + servicePackage + " (current: " + currentPackage + "), returning false");
                        return false;
                    }
                }
                return Integer.valueOf(BlackBoxCore.getBActivityManager().stopService(intent, resolvedType, BActivityThread.getUserId()));
            } catch (SecurityException e) {
                Slog.w(IActivityManagerProxy.TAG, "StopService: SecurityException caught, returning false", e);
                return false;
            } catch (Exception e2) {
                Slog.e(IActivityManagerProxy.TAG, "StopService: Error stopping service", e2);
                return false;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$StopServiceToken.class */
    @ProxyMethod("stopServiceToken")
    public static class StopServiceToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                ComponentName componentName = (ComponentName) args[0];
                IBinder token = (IBinder) args[1];
                if (componentName != null) {
                    String servicePackage = componentName.getPackageName();
                    String currentPackage = BActivityThread.getAppPackageName();
                    if (!servicePackage.equals(currentPackage)) {
                        Slog.w(IActivityManagerProxy.TAG, "StopServiceToken: Attempting to stop service from different package: " + servicePackage + " (current: " + currentPackage + "), returning true");
                        return true;
                    }
                }
                BlackBoxCore.getBActivityManager().stopServiceToken(componentName, token, BActivityThread.getUserId());
                return true;
            } catch (SecurityException e) {
                Slog.w(IActivityManagerProxy.TAG, "StopServiceToken: SecurityException caught, returning true", e);
                return true;
            } catch (Exception e2) {
                Slog.e(IActivityManagerProxy.TAG, "StopServiceToken: Error stopping service token", e2);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$SetActivityLocusContext.class */
    @ProxyMethod("setActivityLocusContext")
    public static class SetActivityLocusContext extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null) {
                try {
                    if (args.length >= 2) {
                        String targetPackage = (String) args[1];
                        String currentPackage = BActivityThread.getAppPackageName();
                        if (targetPackage != null && !targetPackage.equals(currentPackage)) {
                            Slog.w(IActivityManagerProxy.TAG, "SetActivityLocusContext: Attempting to set locus context for different package: " + targetPackage + " (current: " + currentPackage + "), returning success");
                            return null;
                        }
                    }
                } catch (SecurityException e) {
                    Slog.w(IActivityManagerProxy.TAG, "SetActivityLocusContext: SecurityException caught, returning success", e);
                    return null;
                } catch (Exception e2) {
                    Slog.e(IActivityManagerProxy.TAG, "SetActivityLocusContext: Error setting locus context", e2);
                    return null;
                }
            }
            return method.invoke(who, args);
        }
    }

    public static Object BindServiceCommon(Object who, Method method, Object[] args, int callingPackageIndex) throws Throwable {
        int flagsIndex;
        try {
            Intent intent = (Intent) args[2];
            String resolvedType = (String) args[3];
            IServiceConnection connection = (IServiceConnection) args[4];
            if (intent == null) {
                Slog.w(TAG, "BindServiceCommon: Intent is null, proceeding with original call");
                return method.invoke(who, args);
            }
            if (GaidManager.isAdvertisingIdIntent(intent)) {
                Slog.d(TAG, "BindServiceCommon: advertising id bind requested, configured="
                        + GaidManager.isConfigured());
            }
            if (GaidManager.isConfigured() && GaidManager.isAdvertisingIdIntent(intent)
                    && GaidManager.deliverFakeConnection(connection, intent)) {
                Slog.d(TAG, "BindServiceCommon: injected advertising id for " + intent.getAction());
                // bindService() returns boolean on API <= 28 but int (0 / 1 / -1)
                // from API 29 on; the generated proxy unboxes to the declared type.
                return method.getReturnType() == boolean.class
                        ? Boolean.TRUE : (Object) Integer.valueOf(1);
            }
            int userId = intent.getIntExtra("_B_|_UserId", -1);
            int userId2 = userId == -1 ? BActivityThread.getUserId() : userId;
            ResolveInfo resolveInfo = BlackBoxCore.getBPackageManager().resolveService(intent, 0, resolvedType, userId2);
            if (resolveInfo != null || AppSystemEnv.isOpenPackage(intent.getComponent())) {
                Intent proxyIntent = BlackBoxCore.getBActivityManager().bindService(intent, connection == null ? null : connection.asBinder(), resolvedType, userId2);
                if (connection != null) {
                    if (intent.getComponent() == null && resolveInfo != null) {
                        intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
                    }
                    IServiceConnection proxy = ServiceConnectionDelegate.createProxy(connection, intent);
                    args[4] = proxy;
                    WeakReference<?> weakReference = BRLoadedApkServiceDispatcherInnerConnection.get(connection).mDispatcher();
                    if (weakReference != null) {
                        BRLoadedApkServiceDispatcher.get(weakReference.get())._set_mConnection(proxy);
                    }
                }
                if (proxyIntent != null && proxyIntent.getComponent() != null && proxyIntent.getComponent().getPackageName().equals(BlackBoxCore.getHostPkg()) && (flagsIndex = getFlagsIndex(args)) >= 0) {
                    int flags = MethodParameterUtils.toInt(args[flagsIndex]);
                    args[flagsIndex] = Integer.valueOf(flags & Integer.MAX_VALUE);
                }
                args[callingPackageIndex] = BlackBoxCore.getHostPkg();
                if (proxyIntent != null) {
                    args[2] = proxyIntent;
                    return method.invoke(who, args);
                }
            }
            return method.invoke(who, args);
        } catch (Exception e) {
            Slog.e(TAG, "BindServiceCommon: Unexpected error", e);
            return method.invoke(who, args);
        }
    }

    private static int getFlagsIndex(Object[] args) {
        for (int i = 5; i < args.length; i++) {
            Object arg = args[i];
            if ((arg instanceof Integer) || (arg instanceof Long)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$BindService.class */
    @ProxyMethod("bindService")
    public static class BindService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return IActivityManagerProxy.BindServiceCommon(who, method, args, 6);
        }

        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected boolean isEnable() {
            return BlackBoxCore.get().isBlackProcess() || BlackBoxCore.get().isServerProcess();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$bindServiceInstance.class */
    @ProxyMethod("bindServiceInstance")
    public static class bindServiceInstance extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return IActivityManagerProxy.BindServiceCommon(who, method, args, 7);
        }

        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected boolean isEnable() {
            return BlackBoxCore.get().isBlackProcess() || BlackBoxCore.get().isServerProcess();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$BindIsolatedService.class */
    @ProxyMethod("bindIsolatedService")
    public static class BindIsolatedService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            args[6] = null;
            return IActivityManagerProxy.BindServiceCommon(who, method, args, 7);
        }

        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected boolean isEnable() {
            return BlackBoxCore.get().isBlackProcess() || BlackBoxCore.get().isServerProcess();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$UnbindService.class */
    @ProxyMethod("unbindService")
    public static class UnbindService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IServiceConnection iServiceConnection = (IServiceConnection) args[0];
            if (iServiceConnection == null) {
                return method.invoke(who, args);
            }
            if (GaidManager.consumeInjected(iServiceConnection)) {
                // The matching bindService() was answered with a fake advertising
                // id binder, so AMS never registered this connection. Forwarding
                // the unbind would throw "Service not registered" at the caller.
                Slog.d(TAG, "UnbindService: skipping fake advertising id unbind");
                return Boolean.TRUE;
            }
            BlackBoxCore.getBActivityManager().unbindService(iServiceConnection.asBinder(), BActivityThread.getUserId());
            ServiceConnectionDelegate delegate = ServiceConnectionDelegate.getDelegate(iServiceConnection.asBinder());
            if (delegate != null) {
                args[0] = delegate;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetRunningAppProcesses.class */
    @ProxyMethod("getRunningAppProcesses")
    public static class GetRunningAppProcesses extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            RunningAppProcessInfo runningAppProcesses = BActivityManager.get().getRunningAppProcesses(BActivityThread.getAppPackageName(), BActivityThread.getUserId());
            if (runningAppProcesses == null) {
                return new ArrayList();
            }
            return runningAppProcesses.mAppProcessInfoList;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetServices.class */
    @ProxyMethod("getServices")
    public static class GetServices extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            RunningServiceInfo runningServices = BActivityManager.get().getRunningServices(BActivityThread.getAppPackageName(), BActivityThread.getUserId());
            if (runningServices == null) {
                return new ArrayList();
            }
            return runningServices.mRunningServiceInfoList;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GetIntentSender.class */
    @ProxyMethod("getIntentSender")
    public static class GetIntentSender extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            int type = ((Integer) args[0]).intValue();
            Intent[] intents = (Intent[]) args[getIntentsIndex(args)];
            MethodParameterUtils.replaceFirstAppPkg(args);
            for (int i = 0; i < intents.length; i++) {
                Intent intent = intents[i];
                switch (type) {
                    case 2:
                        Intent shadow = new Intent();
                        shadow.setComponent(new ComponentName(BlackBoxCore.getHostPkg(), ProxyManifest.getProxyPendingActivity(BActivityThread.getAppPid())));
                        ProxyPendingRecord.saveStub(shadow, intent, BActivityThread.getUserId());
                        intents[i] = shadow;
                        break;
                }
            }
            IInterface invoke = (IInterface) method.invoke(who, args);
            if (invoke != null) {
                String[] packagesForUid = BPackageManager.get().getPackagesForUid(BActivityThread.getCallingBUid());
                if (packagesForUid.length < 1) {
                    packagesForUid = new String[]{BlackBoxCore.getHostPkg()};
                }
                BlackBoxCore.getBActivityManager().getIntentSender(invoke.asBinder(), packagesForUid[0], BActivityThread.getCallingBUid());
            }
            return invoke;
        }

        private int getIntentsIndex(Object[] args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent[]) {
                    return i;
                }
            }
            if (BuildCompat.isR()) {
                return 6;
            }
            return 5;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$getPackageForIntentSender.class */
    @ProxyMethod("getPackageForIntentSender")
    public static class getPackageForIntentSender extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IInterface invoke = (IInterface) args[0];
            return BlackBoxCore.getBActivityManager().getPackageForIntentSender(invoke.asBinder());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$getUidForIntentSender.class */
    @ProxyMethod("getUidForIntentSender")
    public static class getUidForIntentSender extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IInterface invoke = (IInterface) args[0];
            return Integer.valueOf(BlackBoxCore.getBActivityManager().getUidForIntentSender(invoke.asBinder()));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$BroadcastIntent.class */
    @ProxyMethod("broadcastIntent")
    public static class BroadcastIntent extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            int intentIndex = getIntentIndex(args);
            Intent intent = (Intent) args[intentIndex];
            String resolvedType = (String) args[intentIndex + 1];
            Intent proxyIntent = BlackBoxCore.getBActivityManager().sendBroadcast(intent, resolvedType, BActivityThread.getUserId());
            if (proxyIntent != null) {
                proxyIntent.setExtrasClassLoader(BActivityThread.getApplication().getClassLoader());
                ProxyBroadcastRecord.saveStub(proxyIntent, intent, BActivityThread.getUserId());
                args[intentIndex] = proxyIntent;
            }
            for (int i = 0; i < args.length; i++) {
                Object o = args[i];
                if (o instanceof String[]) {
                    args[i] = null;
                }
            }
            return method.invoke(who, args);
        }

        int getIntentIndex(Object[] args) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Intent) {
                    return i;
                }
            }
            return 1;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$unregisterReceiver.class */
    @ProxyMethod("unregisterReceiver")
    public static class unregisterReceiver extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$finishReceiver.class */
    @ProxyMethod("finishReceiver")
    public static class finishReceiver extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$PublishService.class */
    @ProxyMethod("publishService")
    public static class PublishService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$PeekService.class */
    @ProxyMethod("peekService")
    public static class PeekService extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceLastAppPkg(args);
            Intent intent = (Intent) args[0];
            String resolvedType = (String) args[1];
            IBinder peek = BlackBoxCore.getBActivityManager().peekService(intent, resolvedType, BActivityThread.getUserId());
            return peek;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$SendIntentSender.class */
    @ProxyMethod("sendIntentSender")
    public static class SendIntentSender extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$RegisterReceiverWithFeature.class */
    @ProxyMethod("registerReceiverWithFeature")
    public static class RegisterReceiverWithFeature extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            int receiverIndex = getReceiverIndex();
            if (args[receiverIndex] != null) {
                IIntentReceiver intentReceiver = (IIntentReceiver) args[receiverIndex];
                IIntentReceiver proxy = InnerReceiverDelegate.createProxy(intentReceiver);
                WeakReference<?> weakReference = BRLoadedApkReceiverDispatcherInnerReceiver.get(intentReceiver).mDispatcher();
                if (weakReference != null) {
                    BRLoadedApkReceiverDispatcher.get(weakReference.get())._set_mIIntentReceiver(proxy);
                }
                args[receiverIndex] = proxy;
            }
            if (args[getPermissionIndex()] != null) {
                args[getPermissionIndex()] = null;
            }
            if (BuildCompat.isU()) {
                int flagsIndex = args.length - 1;
                int flags = ((Integer) args[flagsIndex]).intValue();
                if ((flags & 4) == 0 && (flags & 2) == 0) {
                    flags |= 4;
                }
                args[flagsIndex] = Integer.valueOf(flags);
            }
            return applyBatteryOverride(method.invoke(who, args));
        }

        public int getReceiverIndex() {
            if (BuildCompat.isS()) {
                return 4;
            }
            return 3;
        }

        public int getPermissionIndex() {
            if (BuildCompat.isS()) {
                return 6;
            }
            return 5;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$RegisterReceiver.class */
    @ProxyMethod("registerReceiver")
    public static class RegisterReceiver extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            if (args[2] != null) {
                IIntentReceiver intentReceiver = (IIntentReceiver) args[2];
                IIntentReceiver proxy = InnerReceiverDelegate.createProxy(intentReceiver);
                WeakReference<?> weakReference = BRLoadedApkReceiverDispatcherInnerReceiver.get(intentReceiver).mDispatcher();
                if (weakReference != null) {
                    BRLoadedApkReceiverDispatcher.get(weakReference.get())._set_mIIntentReceiver(proxy);
                }
                args[2] = proxy;
            }
            if (args[4] != null) {
                args[4] = null;
            }
            return applyBatteryOverride(method.invoke(who, args));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$GrantUriPermission.class */
    @ProxyMethod("grantUriPermission")
    public static class GrantUriPermission extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceLastUid(args);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$setServiceForeground.class */
    @ProxyMethod("setServiceForeground")
    public static class setServiceForeground extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            for (int i = args.length - 1; i >= 0; i--) {
                if (args[i] instanceof Integer) {
                    args[i] = 0;
                    break;
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$getHistoricalProcessExitReasons.class */
    @ProxyMethod("getHistoricalProcessExitReasons")
    public static class getHistoricalProcessExitReasons extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return ParceledListSliceCompat.create(new ArrayList());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$getCurrentUser.class */
    @ProxyMethod("getCurrentUser")
    public static class getCurrentUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object blackBox = BRUserInfo.get()._new(BActivityThread.getUserId(), "BlackBox", BRUserInfo.get().FLAG_PRIMARY().intValue());
            return blackBox;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$getCurrentUserId.class */
    @ProxyMethod("getCurrentUserId")
    public static class getCurrentUserId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            // Single user container: always user 0, apps unbox this as an int.
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$checkPermission.class */
    @ProxyMethod("checkPermission")
    public static class checkPermission extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceLastUid(args);
            String permission = (String) args[0];
            if (permission.equals("android.permission.ACCOUNT_MANAGER") || permission.equals("android.permission.SEND_SMS")) {
                return 0;
            }
            if (IActivityManagerProxy.isAudioPermission(permission)) {
                Slog.d(IActivityManagerProxy.TAG, "ActivityManager checkPermission: Granting audio permission: " + permission);
                return 0;
            }
            if (IActivityManagerProxy.isStorageOrMediaPermission(permission)) {
                Slog.d(IActivityManagerProxy.TAG, "ActivityManager checkPermission: Granting storage/media permission: " + permission);
                return 0;
            }
            return method.invoke(who, args);
        }
    }

    private static boolean isAudioPermission(String permission) {
        if (permission == null) {
            return false;
        }
        return permission.equals("android.permission.RECORD_AUDIO") || permission.equals("android.permission.CAPTURE_AUDIO_OUTPUT") || permission.equals("android.permission.MODIFY_AUDIO_SETTINGS") || permission.equals("android.permission.FOREGROUND_SERVICE_MICROPHONE") || permission.equals("android.permission.FOREGROUND_SERVICE_MEDIA_PROJECTION") || permission.equals("android.permission.FOREGROUND_SERVICE_CAMERA") || permission.equals("android.permission.FOREGROUND_SERVICE_LOCATION") || permission.equals("android.permission.FOREGROUND_SERVICE_HEALTH") || permission.equals("android.permission.FOREGROUND_SERVICE_DATA_SYNC") || permission.equals("android.permission.FOREGROUND_SERVICE_SPECIAL_USE") || permission.equals("android.permission.FOREGROUND_SERVICE_SYSTEM_EXEMPTED") || permission.equals("android.permission.FOREGROUND_SERVICE_PHONE_CALL") || permission.equals("android.permission.FOREGROUND_SERVICE_CONNECTED_DEVICE");
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$checkUriPermission.class */
    @ProxyMethod("checkUriPermission")
    public static class checkUriPermission extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    private static boolean isStorageOrMediaPermission(String permission) {
        if (permission == null) {
            return false;
        }
        if (permission.equals("android.permission.READ_EXTERNAL_STORAGE") || permission.equals("android.permission.WRITE_EXTERNAL_STORAGE") || permission.equals("android.permission.READ_MEDIA_AUDIO") || permission.equals("android.permission.READ_MEDIA_VIDEO") || permission.equals("android.permission.READ_MEDIA_IMAGES") || permission.equals("android.permission.READ_MEDIA_VISUAL") || permission.equals("android.permission.READ_MEDIA_AURAL") || permission.equals("android.permission.ACCESS_MEDIA_LOCATION") || permission.equals("android.permission.READ_MEDIA_AUDIO_USER_SELECTED") || permission.equals("android.permission.READ_MEDIA_VIDEO_USER_SELECTED") || permission.equals("android.permission.READ_MEDIA_IMAGES_USER_SELECTED") || permission.equals("android.permission.READ_MEDIA_VISUAL_USER_SELECTED") || permission.equals("android.permission.READ_MEDIA_AURAL_USER_SELECTED")) {
            return true;
        }
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$SetTaskDescription.class */
    @ProxyMethod("setTaskDescription")
    public static class SetTaskDescription extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            ActivityManager.TaskDescription td = (ActivityManager.TaskDescription) args[1];
            args[1] = TaskDescriptionCompat.fix(td);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$setRequestedOrientation.class */
    @ProxyMethod("setRequestedOrientation")
    public static class setRequestedOrientation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(who, args);
            } catch (Throwable e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$registerUidObserver.class */
    @ProxyMethod("registerUidObserver")
    public static class registerUidObserver extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$unregisterUidObserver.class */
    @ProxyMethod("unregisterUidObserver")
    public static class unregisterUidObserver extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityManagerProxy$updateConfiguration.class */
    @ProxyMethod("updateConfiguration")
    public static class updateConfiguration extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* 内存：ActivityManager.getMemoryInfo 走 binder，返回前把 totalMem/availMem 换成档案值。 */
    @ProxyMethod("getMemoryInfo")
    public static class getMemoryInfo extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object result = method.invoke(who, args);
            if (args != null && args.length > 0 && args[0] instanceof android.app.ActivityManager.MemoryInfo) {
                android.app.ActivityManager.MemoryInfo info =
                        (android.app.ActivityManager.MemoryInfo) args[0];
                long total = DeviceInfoManager.totalMemory();
                long available = DeviceInfoManager.availableMemory();
                if (total > 0) {
                    info.totalMem = total;
                }
                if (available > 0) {
                    info.availMem = available;
                    info.lowMemory = available < total / 10;
                }
            }
            return result;
        }
    }

    /**
     * registerReceiver 返回的 sticky {@link Intent#ACTION_BATTERY_CHANGED} 按档案改写。
     *
     * <p>电量 / 充电状态没有可写的系统字段，进程内能拦到的唯一位置就是这条 sticky
     * 广播的返回值（BatteryManager 与各 SDK 都从这里读）。</p>
     */
    static Object applyBatteryOverride(Object result) {
        try {
            if (!(result instanceof Intent)) {
                return result;
            }
            Intent intent = (Intent) result;
            if (!Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                return result;
            }
            int level = DeviceInfoManager.batteryLevel();
            if (level >= 0) {
                intent.putExtra(android.os.BatteryManager.EXTRA_LEVEL, level);
                intent.putExtra(android.os.BatteryManager.EXTRA_SCALE, 100);
            }
            Boolean charging = DeviceInfoManager.isCharging();
            if (charging != null) {
                intent.putExtra(android.os.BatteryManager.EXTRA_STATUS,
                        charging ? android.os.BatteryManager.BATTERY_STATUS_CHARGING
                                : android.os.BatteryManager.BATTERY_STATUS_DISCHARGING);
                intent.putExtra(android.os.BatteryManager.EXTRA_PLUGGED,
                        charging ? android.os.BatteryManager.BATTERY_PLUGGED_AC : 0);
            }
            return intent;
        } catch (Throwable t) {
            return result;
        }
    }
}
