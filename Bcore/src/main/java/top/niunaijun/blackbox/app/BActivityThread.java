package top.niunaijun.blackbox.app;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.app.Service;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.os.StrictMode;
import android.webkit.WebView;
import black.android.app.ActivityThreadAppBindDataContext;
import black.android.app.BRActivity;
import black.android.app.BRActivityManagerNative;
import black.android.app.BRActivityThread;
import black.android.app.BRActivityThreadActivityClientRecord;
import black.android.app.BRActivityThreadAppBindData;
import black.android.app.BRActivityThreadNMR1;
import black.android.app.BRActivityThreadQ;
import black.android.app.BRContextImpl;
import black.android.app.BRLoadedApk;
import black.android.app.BRService;
import black.android.content.BRBroadcastReceiver;
import black.android.content.BRContentProviderClient;
import black.android.graphics.BRCompatibility;
import black.android.security.net.config.BRNetworkSecurityConfigProvider;
import black.com.android.internal.content.BRReferrerIntent;
import black.dalvik.system.BRVMRuntime;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Method;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.configuration.AppLifecycleCallback;
import top.niunaijun.blackbox.app.dispatcher.AppServiceDispatcher;
import top.niunaijun.blackbox.core.CrashHandler;
import top.niunaijun.blackbox.core.IBActivityThread;
import top.niunaijun.blackbox.core.IOCore;
import top.niunaijun.blackbox.core.NativeCore;
import top.niunaijun.blackbox.core.env.VirtualRuntime;
import top.niunaijun.blackbox.core.system.JarManager;
import top.niunaijun.blackbox.core.system.user.BUserHandle;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.am.ReceiverData;
import top.niunaijun.blackbox.fake.delegate.AppInstrumentation;
import top.niunaijun.blackbox.fake.delegate.ContentProviderDelegate;
import top.niunaijun.blackbox.fake.hook.HookManager;
import top.niunaijun.blackbox.fake.service.HCallbackProxy;
import top.niunaijun.blackbox.utils.AppProxyManager;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.GaidManager;
import top.niunaijun.blackbox.utils.Reflector;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.ActivityManagerCompat;
import top.niunaijun.blackbox.utils.compat.BuildCompat;
import top.niunaijun.blackbox.utils.compat.ContextCompat;
import top.niunaijun.blackbox.utils.compat.StrictModeCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/BActivityThread.class */
public class BActivityThread extends IBActivityThread.Stub {
    public static final String TAG = "BActivityThread";
    private static BActivityThread sBActivityThread;
    private AppBindData mBoundApplication;
    private Application mInitialApplication;
    private AppConfig mAppConfig;
    private final List<ProviderInfo> mProviders = new ArrayList();
    private final Handler mH = BlackBoxCore.get().getHandler();
    private static final Object mConfigLock;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/BActivityThread$AppBindData.class */
    public static class AppBindData {
        String processName;
        ApplicationInfo appInfo;
        List<ProviderInfo> providers;
        Object info;
    }

    static {
        $assertionsDisabled = !BActivityThread.class.desiredAssertionStatus();
        mConfigLock = new Object();
    }

    public static boolean isThreadInit() {
        return sBActivityThread != null;
    }

    public static BActivityThread currentActivityThread() {
        if (sBActivityThread == null) {
            synchronized (BActivityThread.class) {
                if (sBActivityThread == null) {
                    sBActivityThread = new BActivityThread();
                }
            }
        }
        return sBActivityThread;
    }

    public static AppConfig getAppConfig() {
        AppConfig appConfig;
        synchronized (mConfigLock) {
            appConfig = currentActivityThread().mAppConfig;
        }
        return appConfig;
    }

    public static List<ProviderInfo> getProviders() {
        return currentActivityThread().mProviders;
    }

    public static String getAppProcessName() {
        if (getAppConfig() != null) {
            return getAppConfig().processName;
        }
        if (currentActivityThread().mBoundApplication != null) {
            return currentActivityThread().mBoundApplication.processName;
        }
        return null;
    }

    public static String getAppPackageName() {
        if (getAppConfig() != null) {
            return getAppConfig().packageName;
        }
        if (currentActivityThread().mInitialApplication != null) {
            return currentActivityThread().mInitialApplication.getPackageName();
        }
        return null;
    }

    public static Application getApplication() {
        return currentActivityThread().mInitialApplication;
    }

    public static int getAppPid() {
        if (getAppConfig() == null) {
            return -1;
        }
        return getAppConfig().bpid;
    }

    public static int getBUid() {
        return getAppConfig() == null ? BUserHandle.AID_APP_START : getAppConfig().buid;
    }

    public static int getBAppId() {
        return BUserHandle.getAppId(getBUid());
    }

    public static int getCallingBUid() {
        return getAppConfig() == null ? BlackBoxCore.getHostUid() : getAppConfig().callingBUid;
    }

    public static int getUid() {
        if (getAppConfig() == null) {
            return -1;
        }
        return getAppConfig().uid;
    }

    /** The container is single user. */
    public static int getUserId() {
        return 0;
    }

    public void initProcess(AppConfig appConfig) {
        synchronized (mConfigLock) {
            if (this.mAppConfig != null && !this.mAppConfig.packageName.equals(appConfig.packageName)) {
                throw new RuntimeException("reject init process: " + appConfig.processName + ", this process is : " + this.mAppConfig.processName);
            }
            this.mAppConfig = appConfig;
            final IBinder iBinder = asBinder();
            try {
                iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.app.BActivityThread.1
                    @Override // android.os.IBinder.DeathRecipient
                    public void binderDied() {
                        synchronized (BActivityThread.mConfigLock) {
                            try {
                                iBinder.linkToDeath(this, 0);
                            } catch (RemoteException e) {
                            }
                            BActivityThread.this.mAppConfig = null;
                        }
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isInit() {
        return this.mBoundApplication != null;
    }

    public Service createService(ServiceInfo serviceInfo, IBinder token) {
        if (!currentActivityThread().isInit()) {
            currentActivityThread().bindApplication(serviceInfo.packageName, serviceInfo.processName);
        }
        ClassLoader classLoader = BRLoadedApk.get(this.mBoundApplication.info).getClassLoader();
        try {
            Service service = (Service) classLoader.loadClass(serviceInfo.name).newInstance();
            try {
                Context context = BlackBoxCore.getContext().createPackageContext(serviceInfo.packageName, 3);
                BRContextImpl.get(context).setOuterContext(service);
                BRService.get(service).attach(context, BlackBoxCore.mainThread(), serviceInfo.name, token, this.mInitialApplication, BRActivityManagerNative.get().getDefault());
                ContextCompat.fix(context);
                DeviceInfoManager.applyToMetrics(context.getResources());
                service.onCreate();
                return service;
            } catch (Exception e) {
                if (serviceInfo.name.contains("google.android.gms") || serviceInfo.name.contains("google.android.location")) {
                    Slog.w(TAG, "Google Play Services service creation failed, skipping: " + serviceInfo.name);
                    return null;
                }
                Slog.w(TAG, "Service creation failed, but continuing: " + serviceInfo.name + " - " + e.getMessage());
                return null;
            }
        } catch (ClassNotFoundException e2) {
            if (serviceInfo.name.contains("google.android.gms") || serviceInfo.name.contains("google.android.location")) {
                Slog.w(TAG, "Google Play Services class not found, skipping: " + serviceInfo.name);
                return null;
            }
            e2.printStackTrace();
            Slog.e(TAG, "Unable to instantiate service " + serviceInfo.name + ": " + e2.toString());
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            Slog.e(TAG, "Unable to instantiate service " + serviceInfo.name + ": " + e3.toString());
            return null;
        }
    }

    public JobService createJobService(ServiceInfo serviceInfo) {
        if (!currentActivityThread().isInit()) {
            currentActivityThread().bindApplication(serviceInfo.packageName, serviceInfo.processName);
        }
        ClassLoader classLoader = BRLoadedApk.get(this.mBoundApplication.info).getClassLoader();
        try {
            JobService service = (JobService) classLoader.loadClass(serviceInfo.name).newInstance();
            try {
                Context context = BlackBoxCore.getContext().createPackageContext(serviceInfo.packageName, 3);
                BRContextImpl.get(context).setOuterContext(service);
                BRService.get(service).attach(context, BlackBoxCore.mainThread(), serviceInfo.name, currentActivityThread().getActivityThread(), this.mInitialApplication, BRActivityManagerNative.get().getDefault());
                ContextCompat.fix(context);
                DeviceInfoManager.applyToMetrics(context.getResources());
                service.onCreate();
                service.onBind(null);
                return service;
            } catch (Exception e) {
                if (serviceInfo.name.contains("google.android.gms") || serviceInfo.name.contains("google.android.location")) {
                    Slog.w(TAG, "Google Play Services JobService creation failed, skipping: " + serviceInfo.name);
                    return null;
                }
                Slog.w(TAG, "JobService creation failed, but continuing: " + serviceInfo.name + " - " + e.getMessage());
                return null;
            }
        } catch (ClassNotFoundException e2) {
            if (serviceInfo.name.contains("google.android.gms") || serviceInfo.name.contains("google.android.location")) {
                Slog.w(TAG, "Google Play Services JobService class not found, skipping: " + serviceInfo.name);
                return null;
            }
            e2.printStackTrace();
            Slog.e(TAG, "Unable to create JobService " + serviceInfo.name + ": " + e2.toString());
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            Slog.e(TAG, "Unable to create JobService " + serviceInfo.name + ": " + e3.toString());
            return null;
        }
    }

    public void bindApplication(String packageName, String processName) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            ConditionVariable conditionVariable = new ConditionVariable();
            BlackBoxCore.get().getHandler().post(() -> {
                createBindApplicationData(packageName, processName);
                handleBindApplication(packageName, processName);
                conditionVariable.open();
            });
            conditionVariable.block();
        } else {
            createBindApplicationData(packageName, processName);
            handleBindApplication(packageName, processName);
        }
    }

    private Object createBindApplicationData(String packageName, String processName) {
        try {
            final PackageInfo packageInfo = BlackBoxCore.getBPackageManager().getPackageInfo(packageName, 8, getUserId());
            final ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            return new Object() { // from class: top.niunaijun.blackbox.app.BActivityThread.2
                public ApplicationInfo getInfo() {
                    return applicationInfo;
                }

                public List<ProviderInfo> getProviders() {
                    return packageInfo.providers != null ? Arrays.asList(packageInfo.providers) : new ArrayList();
                }
            };
        } catch (Exception e) {
            Slog.e(TAG, "Error creating bind application data", e);
            return new Object() { // from class: top.niunaijun.blackbox.app.BActivityThread.3
                public ApplicationInfo getInfo() {
                    return null;
                }

                public List<ProviderInfo> getProviders() {
                    return new ArrayList();
                }
            };
        }
    }

    public synchronized void handleBindApplication(String packageName, String processName) {
        Application application;
        if (isInit()) {
            return;
        }

        try {
            CrashHandler.create();
        } catch (Throwable th) {
        }
        PackageInfo packageInfo = BlackBoxCore.getBPackageManager().getPackageInfo(packageName, 8, getUserId());
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (packageInfo.providers == null) {
            packageInfo.providers = new ProviderInfo[0];
        }
        this.mProviders.addAll(Arrays.asList(packageInfo.providers));
        Object boundApplication = BRActivityThread.get(BlackBoxCore.mainThread()).mBoundApplication();
        Context packageContext = createPackageContext(applicationInfo);
        Object loadedApk = BRContextImpl.get(packageContext).mPackageInfo();
        Slog.d(TAG, "handleBindApplication: packageContext=" + packageContext + ", loadedApk=" + loadedApk);
        BRLoadedApk.get(loadedApk)._set_mSecurityViolation(false);
        BRLoadedApk.get(loadedApk)._set_mApplicationInfo(applicationInfo);
        if (applicationInfo.sourceDir == null) {
            Slog.w(TAG, "sourceDir is null for " + packageName + ", using sandbox apk path");
            File baseApkDir = top.niunaijun.blackbox.core.env.BEnvironment.getBaseApkDir(packageName);
            Slog.w(TAG, "sourceDir is null, falling back to " + baseApkDir.getAbsolutePath());
            applicationInfo.sourceDir = baseApkDir.getAbsolutePath();
        }
        // Android 15's LoadedApk.makePaths() dereferences the data dir files,
        // they are left null for virtual packages by the system context.
        File virtualDataDir = top.niunaijun.blackbox.core.env.BEnvironment.getDataDir(packageName, getUserId());
        if (!virtualDataDir.exists()) {
            virtualDataDir.mkdirs();
        }
        BRLoadedApk.get(loadedApk)._set_mDataDirFile(virtualDataDir);
        BRLoadedApk.get(loadedApk)._set_mDataDir(virtualDataDir.getAbsolutePath());
        BRLoadedApk.get(loadedApk)._set_mCredentialProtectedDataDirFile(virtualDataDir);
        BRLoadedApk.get(loadedApk)._set_mDeviceProtectedDataDirFile(virtualDataDir);
        int targetSdkVersion = applicationInfo.targetSdkVersion;
        if (targetSdkVersion < 9) {
            StrictMode.ThreadPolicy newPolicy = new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy()).permitNetwork().build();
            StrictMode.setThreadPolicy(newPolicy);
        }
        if (Build.VERSION.SDK_INT >= 24 && targetSdkVersion < 24) {
            StrictModeCompat.disableDeathOnFileUriExposure();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WebView.setDataDirectorySuffix(getUserId() + ":" + packageName + ":" + processName);
        }
        VirtualRuntime.setupRuntime(processName, applicationInfo);
        BRVMRuntime.get(BRVMRuntime.get().getRuntime()).setTargetSdkVersion(applicationInfo.targetSdkVersion);
        if (BuildCompat.isS()) {
            BRCompatibility.get().setTargetSdkVersion(applicationInfo.targetSdkVersion);
        }
        NativeCore.init(Build.VERSION.SDK_INT);
        if (!$assertionsDisabled && packageContext == null) {
            throw new AssertionError();
        }
        IOCore.get().enableRedirect(packageContext);
        AppBindData bindData = new AppBindData();
        bindData.appInfo = applicationInfo;
        bindData.processName = processName;
        bindData.info = loadedApk;
        bindData.providers = this.mProviders;
        ActivityThreadAppBindDataContext activityThreadAppBindData = BRActivityThreadAppBindData.get(boundApplication);
        activityThreadAppBindData._set_instrumentationName(new ComponentName(bindData.appInfo.packageName, Instrumentation.class.getName()));
        activityThreadAppBindData._set_appInfo(bindData.appInfo);
        activityThreadAppBindData._set_info(bindData.info);
        activityThreadAppBindData._set_processName(bindData.processName);
        activityThreadAppBindData._set_providers(bindData.providers);
        this.mBoundApplication = bindData;
        if (BRNetworkSecurityConfigProvider.getRealClass() != null) {
            Security.removeProvider("AndroidNSSP");
            BRNetworkSecurityConfigProvider.get().install(packageContext);
        }
        try {
            onBeforeCreateApplication(packageName, processName, packageContext);
            try {
                application = BRLoadedApk.getWithException(loadedApk).makeApplication(false, null);
                Slog.d(TAG, "makeApplication(false) -> " + application);
            } catch (Throwable makeAppException) {
                Throwable root = makeAppException;
                while (root.getCause() != null && root.getCause() != root) {
                    root = root.getCause();
                }
                Slog.e(TAG, "Failed to makeApplication, trying fallback approach", root);
                application = null;
            }
            if (application == null) {
                // Android 15 removed LoadedApk.makeApplication(), so build the
                // Application through Instrumentation instead of casting the
                // package ContextImpl (which used to raise ClassCastException).
                Slog.w(TAG, "makeApplication unavailable, using Instrumentation.newApplication");
                try {
                    application = newApplicationByInstrumentation(packageContext, applicationInfo);
                } catch (Throwable t) {
                    Slog.e(TAG, "Instrumentation.newApplication failed", t);
                }
            }
            if (application == null) {
                Slog.e(TAG, "makeApplication application Error! All attempts failed");
                throw new RuntimeException("Unable to create application - all creation methods failed");
            }
            this.mInitialApplication = application;
            BRActivityThread.get(BlackBoxCore.mainThread())._set_mInitialApplication(this.mInitialApplication);
            Context systemContext = (Context) BRActivityThread.get(BlackBoxCore.mainThread()).getSystemContext();
            ContextCompat.fix(systemContext);
            ContextCompat.fix(this.mInitialApplication);
            // Install per-app configs before anything runs in the process: the
            // ContentProviders below are created before Application.onCreate and
            // they read Build / settings values too.
            AppProxyManager.installFromJson(getAppConfig() != null ? getAppConfig().proxyConfig : null);
            GaidManager.setConfig(getAppConfig() != null ? getAppConfig().gaidConfig : null);
            DeviceInfoManager.setConfig(getAppConfig() != null ? getAppConfig().deviceConfig : null);
            DeviceInfoManager.applyToMetrics(application.getResources());
            if (systemContext != null) {
                DeviceInfoManager.applyToMetrics(systemContext.getResources());
            }
            installProviders(this.mInitialApplication, bindData.processName, bindData.providers);
            onBeforeApplicationOnCreate(packageName, processName, application);
            AppInstrumentation.get().callApplicationOnCreate(application);
            onAfterApplicationOnCreate(packageName, processName, application);
            HookManager.get().checkEnv(HCallbackProxy.class);
        } catch (Exception e2) {
            Slog.e(TAG, "Critical error in handleBindApplication", e2);
            throw new RuntimeException("Unable to makeApplication", e2);
        }
    }

    private Application newApplicationByInstrumentation(Context packageContext, ApplicationInfo appInfo) throws Exception {
        String className = appInfo != null ? appInfo.className : null;
        if (className == null || className.isEmpty()) {
            className = Application.class.getName();
        }
        Method method = Instrumentation.class.getDeclaredMethod("newApplication",
                ClassLoader.class, String.class, Context.class);
        method.setAccessible(true);
        return (Application) method.invoke(AppInstrumentation.get(),
                packageContext.getClassLoader(), className, packageContext);
    }

    private void initializeJarEnvironment() {
        try {
            Slog.d(TAG, "Initializing JAR environment for DEX loading");
            JarManager jarManager = JarManager.getInstance();
            if (!jarManager.isReady()) {
                Slog.d(TAG, "JarManager not ready, initializing synchronously");
                jarManager.initializeSync();
            }
            File emptyJar = jarManager.getEmptyJar();
            if (emptyJar == null || !emptyJar.exists()) {
                Slog.w(TAG, "Empty JAR not available, attempting to recreate");
                jarManager.clearCache();
                jarManager.initializeSync();
                emptyJar = jarManager.getEmptyJar();
            }
            if (emptyJar != null && emptyJar.exists()) {
                Slog.d(TAG, "Empty JAR verified: " + emptyJar.getAbsolutePath());
            } else {
                Slog.w(TAG, "Empty JAR still not available after retry");
            }
        } catch (Exception e) {
            Slog.e(TAG, "Error initializing JAR environment", e);
        }
    }

    private Application createApplicationWithFallback(ApplicationInfo appInfo) {
        try {
            Application application = createApplication(appInfo);
            if (application != null) {
                Slog.d(TAG, "Application created successfully: " + appInfo.className);
                return application;
            }
        } catch (Exception e) {
            Slog.w(TAG, "Failed to create application normally: " + e.getMessage());
        }
        try {
            Slog.d(TAG, "Attempting fallback application creation");
            ClassLoader classLoader = getClassLoader(appInfo);
            if (classLoader == null) {
                Slog.w(TAG, "ClassLoader is null, using system class loader");
                classLoader = ClassLoader.getSystemClassLoader();
            }
            Class<?> appClass = classLoader.loadClass(appInfo.className);
            Application application2 = (Application) appClass.newInstance();
            ensureApplicationBaseContext(application2, appInfo);
            Slog.d(TAG, "Fallback application creation successful");
            return application2;
        } catch (Exception e2) {
            Slog.e(TAG, "Fallback application creation failed: " + e2.getMessage());
            try {
                Slog.d(TAG, "Creating minimal application wrapper");
                Application wrapper = new Application() { // from class: top.niunaijun.blackbox.app.BActivityThread.4
                    @Override // android.app.Application
                    public void onCreate() {
                        super.onCreate();
                        Slog.d(BActivityThread.TAG, "Minimal application wrapper onCreate called");
                    }
                };
                ensureApplicationBaseContext(wrapper, appInfo);
                return wrapper;
            } catch (Exception wrapperException) {
                Slog.e(TAG, "Failed to create minimal application wrapper", wrapperException);
                return null;
            }
        }
    }

    private void installContentProvidersWithFallback(Application application, Object data) {
        try {
            List<ProviderInfo> providers = getProviderInfoList(data);
            if (providers == null || providers.isEmpty()) {
                Slog.d(TAG, "No content providers to install");
                return;
            }
            Slog.d(TAG, "Installing " + providers.size() + " content providers");
            for (ProviderInfo providerInfo : providers) {
                try {
                    installContentProvider(application, providerInfo);
                    Slog.d(TAG, "Successfully installed provider: " + providerInfo.name);
                } catch (Exception e) {
                    Slog.w(TAG, "Failed to install provider " + providerInfo.name + ": " + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Slog.e(TAG, "Error installing content providers", e2);
        }
    }

    private ApplicationInfo getApplicationInfo(Object data) {
        if (data != null) {
            try {
                try {
                    Method getInfoMethod = data.getClass().getMethod("getInfo", new Class[0]);
                    ApplicationInfo appInfo = (ApplicationInfo) getInfoMethod.invoke(data, new Object[0]);
                    if (appInfo != null) {
                        return appInfo;
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Error getting info from custom data object: " + e.getMessage());
                }
            } catch (Exception e2) {
                Slog.e(TAG, "Error getting application info", e2);
                return null;
            }
        }
        String packageName = BlackBoxCore.getAppPackageName();
        if (packageName != null) {
            PackageInfo packageInfo = BlackBoxCore.getBPackageManager().getPackageInfo(packageName, 0, getUserId());
            return packageInfo.applicationInfo;
        }
        return null;
    }

    private ClassLoader getClassLoader(ApplicationInfo appInfo) {
        try {
            String sourceDir = appInfo.sourceDir;
            if (sourceDir != null) {
                return new PathClassLoader(sourceDir, ClassLoader.getSystemClassLoader());
            }
            return ClassLoader.getSystemClassLoader();
        } catch (Exception e) {
            Slog.w(TAG, "Error getting class loader: " + e.getMessage());
            return ClassLoader.getSystemClassLoader();
        }
    }

    private Application createApplication(ApplicationInfo appInfo) {
        try {
            ClassLoader classLoader = getClassLoader(appInfo);
            Class<?> appClass = classLoader.loadClass(appInfo.className);
            Application application = (Application) appClass.newInstance();
            ensureApplicationBaseContext(application, appInfo);
            return application;
        } catch (Exception e) {
            Slog.e(TAG, "Error creating application: " + e.getMessage());
            return null;
        }
    }

    private void ensureApplicationBaseContext(Application application, ApplicationInfo appInfo) {
        try {
            if (application.getBaseContext() != null) {
                Slog.d(TAG, "Application already has base context: " + appInfo.className);
                return;
            }
            Context packageContext = createPackageContext(appInfo);
            if (packageContext == null) {
                Slog.w(TAG, "Could not create package context for application: " + appInfo.className + ", using fallback");
                packageContext = createFallbackContext(appInfo.packageName);
            }
            if (packageContext == null) {
                Slog.e(TAG, "Failed to create any context for application: " + appInfo.className);
                return;
            }
            try {
                Method attachBaseContext = Application.class.getDeclaredMethod("attachBaseContext", Context.class);
                attachBaseContext.setAccessible(true);
                attachBaseContext.invoke(application, packageContext);
                Slog.d(TAG, "Successfully attached base context to application: " + appInfo.className);
            } catch (Exception e) {
                Slog.w(TAG, "Could not attach base context to application: " + e.getMessage());
            }
        } catch (Exception e2) {
            Slog.e(TAG, "Error ensuring application base context: " + e2.getMessage());
        }
    }

    private Context createFallbackContext(final String packageName) {
        try {
            final Context baseContext = BlackBoxCore.getContext();
            if (baseContext == null) {
                Slog.e(TAG, "BlackBoxCore.getContext() is null, cannot create fallback context");
                return null;
            }
            return new ContextWrapper(baseContext) { // from class: top.niunaijun.blackbox.app.BActivityThread.5
                @Override // android.content.ContextWrapper, android.content.Context
                public String getPackageName() {
                    return packageName;
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public PackageManager getPackageManager() {
                    try {
                        return baseContext.getPackageManager();
                    } catch (Exception e) {
                        Slog.w(BActivityThread.TAG, "Error getting package manager from base context: " + e.getMessage());
                        return null;
                    }
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Resources getResources() {
                    try {
                        return baseContext.getResources();
                    } catch (Exception e) {
                        Slog.w(BActivityThread.TAG, "Error getting resources from base context: " + e.getMessage());
                        try {
                            return Resources.getSystem();
                        } catch (Exception e2) {
                            Slog.e(BActivityThread.TAG, "Error getting system resources: " + e2.getMessage());
                            return null;
                        }
                    }
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public ClassLoader getClassLoader() {
                    try {
                        return baseContext.getClassLoader();
                    } catch (Exception e) {
                        Slog.w(BActivityThread.TAG, "Error getting class loader from base context: " + e.getMessage());
                        try {
                            return ClassLoader.getSystemClassLoader();
                        } catch (Exception e2) {
                            Slog.e(BActivityThread.TAG, "Error getting system class loader: " + e2.getMessage());
                            return null;
                        }
                    }
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Context getApplicationContext() {
                    try {
                        return baseContext.getApplicationContext();
                    } catch (Exception e) {
                        Slog.w(BActivityThread.TAG, "Error getting application context from base context: " + e.getMessage());
                        return this;
                    }
                }
            };
        } catch (Exception e) {
            Slog.e(TAG, "Failed to create fallback context for " + packageName + ": " + e.getMessage());
            return null;
        }
    }

    private List<ProviderInfo> getProviderInfoList(Object data) {
        try {
            if (data != null) {
                try {
                    Method getProvidersMethod = data.getClass().getMethod("getProviders", new Class[0]);
                    List<ProviderInfo> providers = (List) getProvidersMethod.invoke(data, new Object[0]);
                    if (providers != null) {
                        return providers;
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Error getting providers from custom data object: " + e.getMessage());
                }
            }
            return new ArrayList();
        } catch (Exception e2) {
            Slog.e(TAG, "Error getting provider info list", e2);
            return new ArrayList();
        }
    }

    private void installContentProvider(Application application, ProviderInfo providerInfo) {
        try {
            if (application == null) {
                Slog.w(TAG, "Application is null, cannot install content provider: " + providerInfo.name);
                return;
            }
            ClassLoader classLoader = application.getClassLoader();
            if (classLoader == null) {
                Slog.w(TAG, "Application class loader is null, using system class loader for: " + providerInfo.name);
                classLoader = ClassLoader.getSystemClassLoader();
            }
            ContentProvider provider = (ContentProvider) classLoader.loadClass(providerInfo.name).newInstance();
            provider.attachInfo(application, providerInfo);
            Slog.d(TAG, "Content provider installed: " + providerInfo.name);
        } catch (Exception e) {
            Slog.e(TAG, "Error installing content provider " + providerInfo.name, e);
        }
    }

    private void setApplication(Application application) {
        try {
            this.mInitialApplication = application;
            BRActivityThread.get(BlackBoxCore.mainThread())._set_mInitialApplication(application);
            Slog.d(TAG, "Application set in ActivityThread successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Error setting application in ActivityThread", e);
        }
    }

    private void handleSecurityException(SecurityException se, String packageName, String processName, Context packageContext) {
        Slog.w(TAG, "Handling SecurityException for " + packageName);
        try {
            Application basicApp = createMinimalApplication(packageContext, packageName);
            if (basicApp != null) {
                this.mInitialApplication = basicApp;
                BRActivityThread.get(BlackBoxCore.mainThread())._set_mInitialApplication(this.mInitialApplication);
                ContextCompat.fix(this.mInitialApplication);
                Slog.w(TAG, "Created basic application, skipping problematic operations");
                return;
            }
        } catch (Exception e) {
            Slog.e(TAG, "Failed to create basic application after SecurityException: " + e.getMessage());
        }
        throw new RuntimeException("Unable to handle SecurityException", se);
    }

    private void installProvidersWithErrorHandling(Context context, String processName, List<ProviderInfo> providers) {
        long origId = Binder.clearCallingIdentity();
        try {
            for (ProviderInfo providerInfo : providers) {
                try {
                    try {
                        if (processName.equals(providerInfo.processName) || providerInfo.processName.equals(context.getPackageName()) || providerInfo.multiprocess) {
                            installProvider(BlackBoxCore.mainThread(), context, providerInfo, null);
                        }
                    } catch (SecurityException se) {
                        Slog.w(TAG, "SecurityException installing provider " + providerInfo.name + ": " + se.getMessage());
                    }
                } catch (Throwable t) {
                    Slog.w(TAG, "Error installing provider " + providerInfo.name + ": " + t.getMessage());
                }
            }
            Binder.restoreCallingIdentity(origId);
            try {
                ContentProviderDelegate.init();
            } catch (Exception e) {
                Slog.w(TAG, "Error initializing ContentProviderDelegate: " + e.getMessage());
            }
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(origId);
            try {
                ContentProviderDelegate.init();
            } catch (Exception e2) {
                Slog.w(TAG, "Error initializing ContentProviderDelegate: " + e2.getMessage());
            }
            throw th;
        }
    }

    public static Context createPackageContext(ApplicationInfo info) {
        try {
            return BlackBoxCore.getContext().createPackageContext(info.packageName, 3);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Context createMinimalPackageContext(ApplicationInfo info) {
        try {
            Context baseContext = BlackBoxCore.getContext();
            try {
                Context packageContext = baseContext.createPackageContext(info.packageName, 0);
                if (packageContext != null) {
                    Slog.d(TAG, "Successfully created package context with minimal flags for " + info.packageName);
                    return packageContext;
                }
            } catch (Exception e) {
                Slog.w(TAG, "Failed to create package context with minimal flags for " + info.packageName + ": " + e.getMessage());
            }
            try {
                Context packageContext2 = baseContext.createPackageContext(info.packageName, 2);
                if (packageContext2 != null) {
                    Slog.d(TAG, "Successfully created package context with ignore security for " + info.packageName);
                    return packageContext2;
                }
            } catch (Exception e2) {
                Slog.w(TAG, "Failed to create package context with ignore security for " + info.packageName + ": " + e2.getMessage());
            }
            try {
                Context packageContext3 = baseContext.createPackageContext(info.packageName, 1);
                if (packageContext3 != null) {
                    Slog.d(TAG, "Successfully created package context with include code for " + info.packageName);
                    return packageContext3;
                }
            } catch (Exception e3) {
                Slog.w(TAG, "Failed to create package context with include code for " + info.packageName + ": " + e3.getMessage());
            }
        } catch (Exception e4) {
            Slog.e(TAG, "Failed to create minimal package context for " + info.packageName + ": " + e4.getMessage());
        }
        Slog.w(TAG, "Using base context as fallback for " + info.packageName);
        return createWrappedBaseContext(info.packageName);
    }

    private static Context createWrappedBaseContext(final String packageName) {
        try {
            final Context baseContext = BlackBoxCore.getContext();
            return new ContextWrapper(baseContext) { // from class: top.niunaijun.blackbox.app.BActivityThread.6
                @Override // android.content.ContextWrapper, android.content.Context
                public String getPackageName() {
                    return packageName;
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public PackageManager getPackageManager() {
                    return baseContext.getPackageManager();
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Resources getResources() {
                    return baseContext.getResources();
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public ClassLoader getClassLoader() {
                    return baseContext.getClassLoader();
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Context getApplicationContext() {
                    return baseContext.getApplicationContext();
                }
            };
        } catch (Exception e) {
            Slog.e(TAG, "Failed to create wrapped base context for " + packageName + ": " + e.getMessage());
            return BlackBoxCore.getContext();
        }
    }

    private void installProviders(Context context, String processName, List<ProviderInfo> provider) {
        long origId = Binder.clearCallingIdentity();
        try {
            for (ProviderInfo providerInfo : provider) {
                try {
                    if (processName.equals(providerInfo.processName) || providerInfo.processName.equals(context.getPackageName()) || providerInfo.multiprocess) {
                        installProvider(BlackBoxCore.mainThread(), context, providerInfo, null);
                    }
                } catch (Throwable th) {
                }
            }
        } finally {
            Binder.restoreCallingIdentity(origId);
            ContentProviderDelegate.init();
        }
    }

    public Object getPackageInfo() {
        return this.mBoundApplication.info;
    }

    public static void installProvider(Object mainThread, Context context, ProviderInfo providerInfo, Object holder) throws Throwable {
        Method installProvider = Reflector.findMethodByFirstName(mainThread.getClass(), "installProvider");
        if (installProvider != null) {
            installProvider.setAccessible(true);
            installProvider.invoke(mainThread, context, holder, providerInfo, false, true, true);
        }
    }

    public void loadXposed(android.content.Context context) {
        String appPackageName = getAppPackageName();
        String appProcessName = getAppProcessName();
        if (!android.text.TextUtils.isEmpty(appPackageName)
                && !android.text.TextUtils.isEmpty(appProcessName)
                && top.niunaijun.blackbox.fake.frameworks.BXposedManager.get().isXPEnable()) {
            boolean isMainProcess = appPackageName.equals(appProcessName);
            List<top.niunaijun.blackbox.entity.pm.InstalledModule> installedModules =
                    top.niunaijun.blackbox.fake.frameworks.BXposedManager.get().getInstalledModules();
            for (top.niunaijun.blackbox.entity.pm.InstalledModule module : installedModules) {
                if (!module.enable) {
                    continue;
                }
                // 模块已启用，实际 hook 由 Xposed 桥接在类加载时完成
                if (isMainProcess) {
                    Slog.d("BActivityThread", "Xposed module enabled: " + module.packageName);
                }
            }
        }
        if (BlackBoxCore.get().isHideXposed()) {
            NativeCore.hideXposed();
        }
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public IBinder getActivityThread() {
        return BRActivityThread.get(BlackBoxCore.mainThread()).getApplicationThread();
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void bindApplication() {
        if (!isInit()) {
            bindApplication(getAppPackageName(), getAppProcessName());
        }
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void stopService(Intent intent) {
        AppServiceDispatcher.get().stopService(intent);
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void restartJobService(String selfId) throws RemoteException {
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
        if (!isInit()) {
            bindApplication(getAppConfig().packageName, getAppConfig().processName);
        }
        String[] split = providerInfo.authority.split(";");
        for (String auth : split) {
            ContentProviderClient contentProviderClient = BlackBoxCore.getContext().getContentResolver().acquireContentProviderClient(auth);
            IInterface iInterface = BRContentProviderClient.get(contentProviderClient).mContentProvider();
            if (iInterface != null) {
                return iInterface.asBinder();
            }
        }
        return null;
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public IBinder peekService(Intent intent) {
        return AppServiceDispatcher.get().peekService(intent);
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void finishActivity(IBinder token) {
        this.mH.post(() -> {
            Map<IBinder, Object> activities = BRActivityThread.get(BlackBoxCore.mainThread()).mActivities();
            if (activities.isEmpty()) {
                return;
            }
            Object clientRecord = activities.get(token);
            if (clientRecord == null) {
                return;
            }
            Activity activityByToken = getActivityByToken(token);
            while (true) {
                Activity activity = activityByToken;
                if (activity.getParent() != null) {
                    activityByToken = activity.getParent();
                } else {
                    int resultCode = BRActivity.get(activity).mResultCode().intValue();
                    Intent resultData = BRActivity.get(activity).mResultData();
                    ActivityManagerCompat.finishActivity(token, resultCode, resultData);
                    BRActivity.get(activity)._set_mFinished(true);
                    return;
                }
            }
        });
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void handleNewIntent(IBinder token, Intent intent) {
        this.mH.post(() -> {
            Intent newIntent;
            if (Build.VERSION.SDK_INT >= 22) {
                newIntent = BRReferrerIntent.get()._new(intent, BlackBoxCore.getHostPkg());
            } else {
                newIntent = intent;
            }
            Object mainThread = BlackBoxCore.mainThread();
            if (BRActivityThread.get(BlackBoxCore.mainThread())._check_performNewIntents(null, null) != null) {
                BRActivityThread.get(mainThread).performNewIntents(token, Collections.singletonList(newIntent));
            } else if (BRActivityThreadNMR1.get(mainThread)._check_performNewIntents(null, null, false) != null) {
                BRActivityThreadNMR1.get(mainThread).performNewIntents(token, Collections.singletonList(newIntent), true);
            } else if (BRActivityThreadQ.get(mainThread)._check_handleNewIntent(null, null) != null) {
                BRActivityThreadQ.get(mainThread).handleNewIntent(token, Collections.singletonList(newIntent));
            }
        });
    }

    @Override // top.niunaijun.blackbox.core.IBActivityThread
    public void scheduleReceiver(ReceiverData data) throws RemoteException {
        if (!isInit()) {
            bindApplication();
        }
        this.mH.post(() -> {
            BroadcastReceiver mReceiver = null;
            Intent intent = data.intent;
            ActivityInfo activityInfo = data.activityInfo;
            BroadcastReceiver.PendingResult pendingResult = data.data.build();
            try {
                Context baseContext = this.mInitialApplication.getBaseContext();
                ClassLoader classLoader = baseContext.getClassLoader();
                intent.setExtrasClassLoader(classLoader);
                mReceiver = (BroadcastReceiver) classLoader.loadClass(activityInfo.name).newInstance();
                BRBroadcastReceiver.get(mReceiver).setPendingResult(pendingResult);
                mReceiver.onReceive(baseContext, intent);
                BroadcastReceiver.PendingResult finish = BRBroadcastReceiver.get(mReceiver).getPendingResult();
                if (finish != null) {
                    finish.finish();
                }
                BlackBoxCore.getBActivityManager().finishBroadcast(data.data);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                Slog.e(TAG, "Error receiving broadcast " + intent + " in " + mReceiver);
            }
        });
    }

    public static Activity getActivityByToken(IBinder token) {
        Map<IBinder, Object> iBinderObjectMap = BRActivityThread.get(BlackBoxCore.mainThread()).mActivities();
        return BRActivityThreadActivityClientRecord.get(iBinderObjectMap.get(token)).activity();
    }

    private void onBeforeCreateApplication(String packageName, String processName, Context context) {
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.beforeCreateApplication(packageName, processName, context, getUserId());
        }
    }

    private void onBeforeApplicationOnCreate(String packageName, String processName, Application application) {
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.beforeApplicationOnCreate(packageName, processName, application, getUserId());
        }
    }

    private void onAfterApplicationOnCreate(String packageName, String processName, Application application) {
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.afterApplicationOnCreate(packageName, processName, application, getUserId());
        }
    }

    public static void ensureActivityContext(Activity activity) {
        Context validContext;
        if (activity == null) {
            return;
        }
        try {
            Context currentContext = activity.getBaseContext();
            if (currentContext != null) {
                Slog.d(TAG, "Activity already has context: " + activity.getClass().getName());
                return;
            }
            Slog.w(TAG, "Activity has null context, ensuring valid context: " + activity.getClass().getName());
            try {
                validContext = getApplication();
                if (validContext == null) {
                    validContext = BlackBoxCore.getContext();
                }
            } catch (Exception e) {
                Slog.w(TAG, "Could not get application context: " + e.getMessage());
                validContext = BlackBoxCore.getContext();
            }
            if (validContext != null) {
                try {
                    Context packageContext = validContext.createPackageContext(activity.getPackageName(), 3);
                    Method attachBaseContext = Activity.class.getDeclaredMethod("attachBaseContext", Context.class);
                    attachBaseContext.setAccessible(true);
                    attachBaseContext.invoke(activity, packageContext);
                    Slog.d(TAG, "Successfully attached package context to activity: " + activity.getClass().getName());
                } catch (Exception e2) {
                    Slog.w(TAG, "Could not attach base context to activity: " + e2.getMessage());
                }
            }
        } catch (Exception e3) {
            Slog.e(TAG, "Error ensuring activity context: " + e3.getMessage());
        }
    }

    public static void hookActivityThread() {
        try {
            Object activityThread = BlackBoxCore.mainThread();
            if (activityThread != null) {
                Instrumentation instrumentation = BRActivityThread.get(activityThread).mInstrumentation();
                if (instrumentation != null) {
                    Slog.d(TAG, "Found ActivityThread instrumentation, ensuring it's our AppInstrumentation");
                    if (!(instrumentation instanceof AppInstrumentation)) {
                        Slog.w(TAG, "ActivityThread instrumentation is not our AppInstrumentation, attempting to replace");
                        try {
                            AppInstrumentation appInstrumentation = AppInstrumentation.get();
                            appInstrumentation.injectHook();
                            Slog.d(TAG, "Successfully replaced ActivityThread instrumentation with AppInstrumentation");
                        } catch (Exception e) {
                            Slog.w(TAG, "Could not replace ActivityThread instrumentation: " + e.getMessage());
                        }
                    } else {
                        Slog.d(TAG, "ActivityThread instrumentation is already our AppInstrumentation");
                    }
                } else {
                    Slog.w(TAG, "ActivityThread instrumentation is null");
                }
            } else {
                Slog.w(TAG, "ActivityThread is null");
            }
        } catch (Exception e2) {
            Slog.e(TAG, "Error hooking ActivityThread: " + e2.getMessage());
        }
    }

    private Application createMinimalApplication(Context packageContext, final String packageName) {
        try {
            Slog.d(TAG, "Creating minimal application for " + packageName);
            Application app = new Application() { // from class: top.niunaijun.blackbox.app.BActivityThread.7
                @Override // android.app.Application
                public void onCreate() {
                    super.onCreate();
                    Slog.d(BActivityThread.TAG, "Minimal application onCreate called for " + packageName);
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public String getPackageName() {
                    return packageName;
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Context getApplicationContext() {
                    return this;
                }
            };
            if (packageContext != null) {
                try {
                    Method attachBaseContext = Application.class.getDeclaredMethod("attachBaseContext", Context.class);
                    attachBaseContext.setAccessible(true);
                    attachBaseContext.invoke(app, packageContext);
                    Slog.d(TAG, "Successfully attached base context to minimal application for " + packageName);
                } catch (Exception e) {
                    Slog.w(TAG, "Could not attach base context to minimal application: " + e.getMessage());
                }
            } else {
                Slog.w(TAG, "Package context is null, cannot attach base context to minimal application");
            }
            Slog.d(TAG, "Minimal application created successfully for " + packageName);
            return app;
        } catch (Exception e2) {
            Slog.e(TAG, "Error creating minimal application for " + packageName, e2);
            return null;
        }
    }
}
