package top.niunaijun.blackbox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.provider.MediaStore;
import black.android.app.BRActivityThread;
import black.android.os.BRUserHandle;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.weishu.reflection.Reflection;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.app.LauncherActivity;
import top.niunaijun.blackbox.app.configuration.AppLifecycleCallback;
import top.niunaijun.blackbox.app.configuration.ClientConfiguration;
import top.niunaijun.blackbox.core.GmsCore;
import top.niunaijun.blackbox.core.NativeCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.DaemonService;
import top.niunaijun.blackbox.core.system.ServiceManager;
import top.niunaijun.blackbox.core.system.am.ActivityRecord;
import top.niunaijun.blackbox.core.system.am.ActivityStack;
import top.niunaijun.blackbox.core.system.am.TaskRecord;

import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.entity.pm.InstallResult;
import top.niunaijun.blackbox.entity.pm.InstalledModule;
import top.niunaijun.blackbox.fake.delegate.ContentProviderDelegate;
import top.niunaijun.blackbox.fake.frameworks.BActivityManager;
import top.niunaijun.blackbox.fake.frameworks.BJobManager;
import top.niunaijun.blackbox.fake.frameworks.BPackageManager;
import top.niunaijun.blackbox.fake.frameworks.BStorageManager;

import top.niunaijun.blackbox.fake.frameworks.BXposedManager;
import top.niunaijun.blackbox.fake.hook.HookManager;
import top.niunaijun.blackbox.proxy.ProxyManifest;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Reflector;
import top.niunaijun.blackbox.utils.ShellUtils;
import top.niunaijun.blackbox.utils.SimpleCrashFix;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.StackTraceFilter;
import top.niunaijun.blackbox.utils.compat.BuildCompat;
import top.niunaijun.blackbox.utils.compat.BundleCompat;
import top.niunaijun.blackbox.utils.compat.XposedParserCompat;
import top.niunaijun.blackbox.utils.provider.ProviderCall;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/BlackBoxCore.class */
@SuppressLint({"StaticFieldLeak", "NewApi"})
public class BlackBoxCore extends ClientConfiguration {
    public static final String TAG = "BlackBoxCore";
    private static final BlackBoxCore sBlackBoxCore = new BlackBoxCore();
    private static Context sContext;
    private ProcessType mProcessType;
    private Thread.UncaughtExceptionHandler mExceptionHandler;
    private ClientConfiguration mClientConfiguration;
    private static final long SERVICE_INIT_TIMEOUT_MS = 10000;
    private final Map<String, IBinder> mServices = new HashMap();
    private final List<AppLifecycleCallback> mAppLifecycleCallbacks = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final int mHostUid = Process.myUid();
    private final int mHostUserId = BRUserHandle.get().myUserId().intValue();
    private boolean mServicesInitialized = false;
    private long mLastServiceInitAttempt = 0;
    private int mCurrentAppUid = -1;
    private String mCurrentAppPackage = null;
    private boolean mIsSandboxedEnvironment = false;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/BlackBoxCore$ProcessType.class */
    private enum ProcessType {
        Server,
        BAppClient,
        Main
    }

    static {
        try {
            SimpleCrashFix.installSimpleFix();
            Slog.d(TAG, "Simple crash fix installed at class loading time");
            StackTraceFilter.install();
            Slog.d(TAG, "Stack trace filter installed at class loading time");
        } catch (Exception e) {
            Slog.w(TAG, "Failed to install simple crash fix or stack trace filter at class loading: " + e.getMessage());
        }
    }

    public static BlackBoxCore get() {
        return sBlackBoxCore;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public static PackageManager getPackageManager() {
        return sContext.getPackageManager();
    }

    public static String getHostPkg() {
        return get().getHostPackageName();
    }

    public static int getHostUid() {
        return get().mHostUid;
    }

    public static int getHostUserId() {
        return get().mHostUserId;
    }

    public static Context getContext() {
        return sContext;
    }

    public Thread.UncaughtExceptionHandler getExceptionHandler() {
        return this.mExceptionHandler;
    }

    public void setExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
        this.mExceptionHandler = exceptionHandler;
    }

    public void setCurrentAppUid(int uid, String packageName) {
        this.mCurrentAppUid = uid;
        this.mCurrentAppPackage = packageName;
        if (uid != this.mHostUid && uid > 10000 && uid < 19999) {
            this.mIsSandboxedEnvironment = true;
            Slog.d(TAG, "Detected sandboxed environment for " + packageName + " with UID: " + uid);
        }
    }

    public int getCurrentAppUid() {
        return this.mCurrentAppUid > 0 ? this.mCurrentAppUid : this.mHostUid;
    }

    public String getCurrentAppPackage() {
        return this.mCurrentAppPackage != null ? this.mCurrentAppPackage : getHostPackageName();
    }

    public boolean isSandboxedEnvironment() {
        return this.mIsSandboxedEnvironment;
    }

    public int resolveUidForOperation(int originalUid, String operation) {
        if (originalUid > 0 && originalUid < 10000) {
            return originalUid;
        }
        if (originalUid > 19999) {
            return originalUid;
        }
        try {
            if (this.mIsSandboxedEnvironment && this.mCurrentAppUid > 0) {
                Slog.d(TAG, "Resolving UID for " + operation + ": " + originalUid + " -> " + this.mCurrentAppUid);
                return this.mCurrentAppUid;
            }
            return originalUid;
        } catch (Exception e) {
            Slog.e(TAG, "Error resolving UID for " + operation + ": " + e.getMessage());
            return originalUid;
        }
    }

    public boolean areServicesAvailable() {
        if (!this.mServicesInitialized) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.mLastServiceInitAttempt < SERVICE_INIT_TIMEOUT_MS) {
                return false;
            }
            this.mLastServiceInitAttempt = currentTime;
            try {
                this.mServicesInitialized = true;
                ServiceManager.get();
                String[] serviceNames = {ServiceManager.ACTIVITY_MANAGER, ServiceManager.JOB_MANAGER, ServiceManager.PACKAGE_MANAGER, ServiceManager.STORAGE_MANAGER, ServiceManager.XPOSED_MANAGER, ServiceManager.ACCOUNT_MANAGER, ServiceManager.LOCATION_MANAGER, ServiceManager.NOTIFICATION_MANAGER};
                for (String serviceName : serviceNames) {
                    try {
                        getServiceInternal(serviceName);
                    } catch (Exception e) {
                        Slog.w(TAG, "Failed to initialize service: " + serviceName, e);
                    }
                }
                Slog.d(TAG, "Services initialized successfully");
                return true;
            } catch (Exception e2) {
                Slog.e(TAG, "Failed to initialize services", e2);
                this.mServicesInitialized = false;
                return false;
            }
        }
        return true;
    }

    public IBinder getService(String name) {
        if (!areServicesAvailable()) {
            Slog.w(TAG, "Services not available, skipping service request: " + name);
            return null;
        }
        return getServiceInternal(name);
    }

    private IBinder getServiceInternal(String name) {
        IBinder binder = this.mServices.get(name);
        if (binder != null && binder.isBinderAlive()) {
            return binder;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("_B_|_server_name_", name);
            Bundle vm = ProviderCall.callSafely(ProxyManifest.getBindProvider(), "VM", null, bundle);
            if (vm == null) {
                Slog.w(TAG, "Provider call returned null for service: " + name);
                return null;
            }
            IBinder binder2 = BundleCompat.getBinder(vm, "_B_|_server_");
            Slog.d(TAG, "getService: " + name + ", " + binder2);
            if (binder2 != null) {
                this.mServices.put(name, binder2);
            } else {
                Slog.w(TAG, "Failed to get binder for service: " + name);
            }
            return binder2;
        } catch (Exception e) {
            Slog.e(TAG, "Error getting service: " + name, e);
            return null;
        }
    }

    public void doAttachBaseContext(Context context, ClientConfiguration clientConfiguration) {
        if (clientConfiguration == null) {
            throw new IllegalArgumentException("ClientConfiguration is null!");
        }
        // Android 14+ no longer exposes the ART entry points the bundled native
        // bypass relies on, so exempt every class from the hidden API policy
        // before any reflective/native lookup happens.
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                org.lsposed.hiddenapibypass.HiddenApiBypass.addHiddenApiExemptions("L");
                Slog.d(TAG, "HiddenApiBypass applied");
            }
        } catch (Throwable t) {
            Slog.w(TAG, "HiddenApiBypass failed: " + t.getMessage());
        }
        if (!NativeCore.disableHiddenApi()) {
            try {
                Reflection.unseal(context);
            } catch (Throwable t) {
                Slog.w(TAG, "Reflection.unseal failed: " + t.getMessage());
            }
        }
        try {
            NativeCore.disableResourceLoading();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to call native resource disabling: " + e.getMessage());
        }
        try {
            System.setProperty("android.view.WindowManager.IGNORE_WINDOW_LEAKS", "true");
            System.setProperty("android.app.Activity.IGNORE_WINDOW_LEAKS", "true");
            System.setProperty("android.view.WindowManager.SUPPRESS_WINDOW_LEAK_WARNINGS", "true");
            try {
                Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
                Field disableOverlayField = resourcesManagerClass.getDeclaredField("mDisableOverlayLoading");
                disableOverlayField.setAccessible(true);
            } catch (Exception e2) {
                Slog.w(TAG, "Could not access ResourcesManager overlay field: " + e2.getMessage());
            }
            try {
                Class<?> windowManagerClass = Class.forName("android.view.WindowManager");
                Field ignoreLeaksField = windowManagerClass.getDeclaredField("mIgnoreWindowLeaks");
                ignoreLeaksField.setAccessible(true);
            } catch (Exception e3) {
                Slog.w(TAG, "Could not access WindowManager leak field: " + e3.getMessage());
            }
        } catch (Exception e4) {
            Slog.w(TAG, "Failed to set essential properties: " + e4.getMessage());
        }
        sContext = context;
        this.mClientConfiguration = clientConfiguration;
        installSystemHooks();
        initNotificationManager();
        String processName = getProcessName(getContext());
        if (processName.equals(getHostPkg())) {
            this.mProcessType = ProcessType.Main;
            startLogcat();
        } else if (processName.endsWith(getContext().getString(R.string.black_box_service_name))) {
            this.mProcessType = ProcessType.Server;
        } else {
            this.mProcessType = ProcessType.BAppClient;
        }
        if (get().isBlackProcess()) {
            BEnvironment.load();
            if (processName.endsWith("p0")) {
            }
        }
        if (isServerProcess() && clientConfiguration.isEnableDaemonService()) {
            Intent intent = new Intent();
            intent.setClass(getContext(), DaemonService.class);
            if (BuildCompat.isOreo()) {
                getContext().startForegroundService(intent);
            } else {
                getContext().startService(intent);
            }
        }
        HookManager.get().init();
    }

    public void doCreate() {
        installSystemHooks();
        ensureProperInitialization();
        if (isBlackProcess()) {
            ContentProviderDelegate.init();
        }
        if (!isServerProcess()) {
            ServiceManager.initBlackManager();
            getBPackageManager().resetTransactionThrottler();
        }
    }

    public static Object mainThread() {
        return BRActivityThread.get().currentActivityThread();
    }

    public void startActivity(Intent intent, int userId) {
        if (this.mClientConfiguration.isEnableLauncherActivity()) {
            LauncherActivity.launch(intent, userId);
        } else {
            getBActivityManager().startActivity(intent, userId);
        }
    }

    public static BJobManager getBJobManager() {
        return BJobManager.get();
    }

    public static BPackageManager getBPackageManager() {
        return BPackageManager.get();
    }

    public static BActivityManager getBActivityManager() {
        return BActivityManager.get();
    }

    public static BStorageManager getBStorageManager() {
        return BStorageManager.get();
    }

    public boolean launchApk(String packageName, int userId) {
        onBeforeMainLaunchApk(packageName, userId);
        Intent launchIntentForPackage = getBPackageManager().getLaunchIntentForPackage(packageName, userId);
        if (launchIntentForPackage == null) {
            return false;
        }
        startActivity(launchIntentForPackage, userId);
        return true;
    }

    public boolean isInstalled(String packageName, int userId) {
        return getBPackageManager().isInstalled(packageName, userId);
    }

    public void uninstallPackageAsUser(String packageName, int userId) {
        getBPackageManager().uninstallPackageAsUser(packageName, userId);
    }

    public void uninstallPackage(String packageName) {
        getBPackageManager().uninstallPackage(packageName);
    }

    public InstallResult installPackageAsUser(String packageName, int userId) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, 0);
            return getBPackageManager().installPackageAsUser(packageInfo.applicationInfo.sourceDir, InstallOption.installBySystem(), userId);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new InstallResult().installError(e.getMessage());
        }
    }

    public InstallResult installPackageAsUser(File apk, int userId) {
        return getBPackageManager().installPackageAsUser(apk.getAbsolutePath(), InstallOption.installByStorage(), userId);
    }

    public InstallResult installPackageAsUser(Uri apk, int userId) {
        return getBPackageManager().installPackageAsUser(apk.toString(), InstallOption.installByStorage().makeUriFile(), userId);
    }

    public InstallResult installXPModule(File apk) {
        return getBPackageManager().installPackageAsUser(apk.getAbsolutePath(), InstallOption.installByStorage().makeXposed(), -4);
    }

    public InstallResult installXPModule(Uri apk) {
        return getBPackageManager().installPackageAsUser(apk.toString(), InstallOption.installByStorage().makeXposed().makeUriFile(), -4);
    }

    public InstallResult installXPModule(String packageName) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, 0);
            String path = packageInfo.applicationInfo.sourceDir;
            return getBPackageManager().installPackageAsUser(path, InstallOption.installBySystem().makeXposed(), -4);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new InstallResult().installError(e.getMessage());
        }
    }

    public void uninstallXPModule(String packageName) {
        uninstallPackage(packageName);
    }

    public boolean isXPEnable() {
        return BXposedManager.get().isXPEnable();
    }

    public void setXPEnable(boolean enable) {
        BXposedManager.get().setXPEnable(enable);
    }

    public boolean isXposedModule(File file) {
        return XposedParserCompat.isXPModule(file.getAbsolutePath());
    }

    public boolean isInstalledXposedModule(String packageName) {
        return isInstalled(packageName, -4);
    }

    public boolean isModuleEnable(String packageName) {
        return BXposedManager.get().isModuleEnable(packageName);
    }

    public void setModuleEnable(String packageName, boolean enable) {
        BXposedManager.get().setModuleEnable(packageName, enable);
    }

    public List<InstalledModule> getInstalledXPModules() {
        return BXposedManager.get().getInstalledModules();
    }

    public List<ApplicationInfo> getInstalledApplications(int flags, int userId) {
        return getBPackageManager().getInstalledApplications(flags, userId);
    }

    public List<PackageInfo> getInstalledPackages(int flags, int userId) {
        return getBPackageManager().getInstalledPackages(flags, userId);
    }

    public void clearPackage(String packageName, int userId) {
        BPackageManager.get().clearPackage(packageName, userId);
    }

    public void stopPackage(String packageName, int userId) {
        BPackageManager.get().stopPackage(packageName, userId);
    }

    public List<AppLifecycleCallback> getAppLifecycleCallbacks() {
        return this.mAppLifecycleCallbacks;
    }

    public void removeAppLifecycleCallback(AppLifecycleCallback appLifecycleCallback) {
        this.mAppLifecycleCallbacks.remove(appLifecycleCallback);
    }

    public void addAppLifecycleCallback(AppLifecycleCallback appLifecycleCallback) {
        this.mAppLifecycleCallbacks.add(appLifecycleCallback);
    }

    public boolean isSupportGms() {
        return GmsCore.isSupportGms();
    }

    public boolean isInstallGms(int userId) {
        return GmsCore.isInstalledGoogleService(userId);
    }

    public InstallResult installGms(int userId) {
        return GmsCore.installGApps(userId);
    }

    public boolean uninstallGms(int userId) {
        GmsCore.uninstallGApps(userId);
        return !GmsCore.isInstalledGoogleService(userId);
    }

    public boolean isBlackProcess() {
        return this.mProcessType == ProcessType.BAppClient;
    }

    public boolean isMainProcess() {
        return this.mProcessType == ProcessType.Main;
    }

    public boolean isServerProcess() {
        return this.mProcessType == ProcessType.Server;
    }

    @Override // top.niunaijun.blackbox.app.configuration.ClientConfiguration
    public boolean isHideRoot() {
        return this.mClientConfiguration.isHideRoot();
    }

    @Override // top.niunaijun.blackbox.app.configuration.ClientConfiguration
    public boolean isHideXposed() {
        return this.mClientConfiguration.isHideXposed();
    }

    @Override // top.niunaijun.blackbox.app.configuration.ClientConfiguration
    public String getHostPackageName() {
        return this.mClientConfiguration.getHostPackageName();
    }

    @Override // top.niunaijun.blackbox.app.configuration.ClientConfiguration
    public boolean requestInstallPackage(File file, int userId) {
        return this.mClientConfiguration.requestInstallPackage(file, userId);
    }

    private void startLogcat() {
        new Thread(() -> {
            Context context = getContext();
            String fileName = context.getPackageName() + "_logcat.txt";
            boolean useMediaStore = Build.VERSION.SDK_INT >= 29;
            try {
                if (useMediaStore) {
                    ContentValues values = new ContentValues();
                    values.put("_display_name", fileName);
                    values.put("mime_type", "text/plain");
                    values.put("relative_path", Environment.DIRECTORY_DOWNLOADS + "/logs");
                    Uri uri = context.getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                    if (uri != null) {
                        OutputStream out = context.getContentResolver().openOutputStream(uri);
                        try {
                            ShellUtils.execCommand("logcat -c", false);
                            java.lang.Process process = Runtime.getRuntime().exec("logcat");
                            InputStream in = process.getInputStream();
                            try {
                                byte[] buffer = new byte[4096];
                                while (true) {
                                    int len = in.read(buffer);
                                    if (len == -1) {
                                        break;
                                    } else {
                                        out.write(buffer, 0, len);
                                    }
                                }
                                if (in != null) {
                                    in.close();
                                }
                                if (out != null) {
                                    out.close();
                                }
                            } catch (Throwable th) {
                                if (in != null) {
                                    try {
                                        in.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (Throwable th4) {
                                    th3.addSuppressed(th4);
                                }
                            }
                            throw th3;
                        }
                    }
                } else {
                    File docuentsdir = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "logs");
                    if (!docuentsdir.exists()) {
                        docuentsdir.mkdirs();
                    }
                    File logFile = new File(docuentsdir, fileName);
                    FileUtils.deleteDir(logFile);
                    ShellUtils.execCommand("logcat -c", false);
                    ShellUtils.execCommand("logcat -f " + logFile.getAbsolutePath(), false);
                }
            } catch (Exception e) {
                Slog.e(TAG, "Failed to save logcat: " + e.getMessage());
            }
        }).start();
    }

    private static String getProcessName(Context context) {
        int pid = Process.myPid();
        String processName = null;
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                ActivityManager am = (ActivityManager) context.getSystemService("activity");
                List<ActivityManager.RunningAppProcessInfo> processes = am.getRunningAppProcesses();
                if (processes != null) {
                    for (ActivityManager.RunningAppProcessInfo info : processes) {
                        if (info.pid == pid) {
                            processName = info.processName;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Slog.w(TAG, "Failed to get process name using modern API", e);
            }
        }
        if (processName == null) {
            try {
                ActivityManager am2 = (ActivityManager) context.getSystemService("activity");
                List<ActivityManager.RunningAppProcessInfo> processes2 = am2.getRunningAppProcesses();
                if (processes2 != null) {
                    for (ActivityManager.RunningAppProcessInfo info2 : processes2) {
                        if (info2.pid == pid) {
                            processName = info2.processName;
                            break;
                        }
                    }
                }
            } catch (Exception e2) {
                Slog.w(TAG, "Failed to get process name using deprecated API", e2);
            }
        }
        if (processName == null) {
            throw new RuntimeException("processName = null");
        }
        return processName;
    }

    public static boolean is64Bit() {
        if (BuildCompat.isM()) {
            return Process.is64Bit();
        }
        return Build.CPU_ABI.equals("arm64-v8a");
    }

    private void initNotificationManager() {
        NotificationManager nm = (NotificationManager) getContext().getSystemService("notification");
        String CHANNEL_ONE_ID = getContext().getPackageName() + ".blackbox_core";
        if (BuildCompat.isOreo()) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, "blackbox_core", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-65536);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(1);
            nm.createNotificationChannel(notificationChannel);
        }
    }

    public void closeCodeInit() {
        try {
            Method attach = Class.forName("top.niunaijun.blackbox.closecode.Entry").getDeclaredMethod("attach", new Class[0]);
            attach.invoke(null, new Object[0]);
        } catch (Exception e) {
            Slog.w(TAG, "closeCodeInit reflection failed: " + e.getMessage());
        }
    }

    public void onBeforeMainLaunchApk(String packageName, int userid) {
        for (AppLifecycleCallback appLifecycleCallback : get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.beforeMainLaunchApk(packageName, userid);
        }
    }

    public void onBeforeMainApplicationAttach(Application app, Context context) {
        for (AppLifecycleCallback appLifecycleCallback : get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.beforeMainApplicationAttach(app, context);
        }
    }

    public void onAfterMainApplicationAttach(Application app, Context context) {
        for (AppLifecycleCallback appLifecycleCallback : get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.afterMainApplicationAttach(app, context);
        }
    }

    public void onBeforeMainActivityOnCreate(Activity activity) {
        for (AppLifecycleCallback appLifecycleCallback : get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.beforeMainActivityOnCreate(activity);
        }
    }

    public void onAfterMainActivityOnCreate(Activity activity) {
        for (AppLifecycleCallback appLifecycleCallback : get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.afterMainActivityOnCreate(activity);
        }
    }

    public static boolean isThreadInit() {
        try {
            return BActivityThread.isThreadInit();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.isThreadInit() failed, returning false", e);
            return false;
        }
    }

    public static BActivityThread currentActivityThread() {
        try {
            return BActivityThread.currentActivityThread();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.currentActivityThread() failed, returning null", e);
            return null;
        }
    }

    public static AppConfig getAppConfig() {
        try {
            return BActivityThread.getAppConfig();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getAppConfig() failed, returning null", e);
            return null;
        }
    }

    public static String getAppProcessName() {
        try {
            return BActivityThread.getAppProcessName();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getAppProcessName() failed, returning null", e);
            return null;
        }
    }

    public static String getAppPackageName() {
        try {
            return BActivityThread.getAppPackageName();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getAppPackageName() failed, returning null", e);
            return null;
        }
    }

    public static Application getApplication() {
        try {
            return BActivityThread.getApplication();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getApplication() failed, returning null", e);
            return null;
        }
    }

    public static int getAppPid() {
        try {
            return BActivityThread.getAppPid();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getAppPid() failed, returning -1", e);
            return -1;
        }
    }

    public static int getBUid() {
        try {
            return BActivityThread.getBUid();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getBUid() failed, returning -1", e);
            return -1;
        }
    }

    public static int getCallingBUid() {
        try {
            return BActivityThread.getCallingBUid();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getCallingBUid() failed, returning -1", e);
            return -1;
        }
    }

    public static int getUid() {
        try {
            return BActivityThread.getUid();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getUid() failed, returning -1", e);
            return -1;
        }
    }

    /** The container is single user. */
    public static int getUserId() {
        try {
            return BActivityThread.getUserId();
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.getUserId() failed, returning 0", e);
            return 0;
        }
    }

    public static void ensureActivityContext(Activity activity) {
        try {
            BActivityThread.ensureActivityContext(activity);
        } catch (Exception e) {
            Slog.w(TAG, "BActivityThread.ensureActivityContext() failed", e);
        }
    }

    public static void installSystemHooks() {
        try {
            SimpleCrashFix.installSimpleFix();
            Slog.d(TAG, "System hooks installed successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Failed to install system hooks", e);
        }
    }

    private static void ensureProperInitialization() {
        try {
            Slog.d(TAG, "Ensuring proper initialization order...");
            try {
                NativeCore.init(Build.VERSION.SDK_INT);
                Slog.d(TAG, "NativeCore initialized successfully");
            } catch (Exception e) {
                Slog.w(TAG, "NativeCore initialization failed: " + e.getMessage());
            }
            try {
                ServiceManager.initBlackManager();
                Slog.d(TAG, "ServiceManager initialized successfully");
            } catch (Exception e2) {
                Slog.w(TAG, "ServiceManager initialization failed: " + e2.getMessage());
            }
            try {
                BActivityThread.hookActivityThread();
                Slog.d(TAG, "BActivityThread hooks initialized successfully");
            } catch (Exception e3) {
                Slog.w(TAG, "BActivityThread hooks initialization failed: " + e3.getMessage());
            }
            Slog.d(TAG, "Proper initialization order ensured");
        } catch (Exception e4) {
            Slog.e(TAG, "Error ensuring proper initialization: " + e4.getMessage());
        }
    }

    public static boolean isRunningApplication(String packageName, int userId) {
        Map<Integer, TaskRecord> tasks;
        try {
            ActivityManager am = (ActivityManager) getContext().getSystemService("activity");
            if (am == null) {
                return false;
            }
            ServiceManager.get();
            ActivityStack stack = (ActivityStack) ServiceManager.getService(ServiceManager.ACTIVITY_MANAGER);
            if (stack == null || (tasks = (Map) Reflector.with(stack).field("mTasks").get()) == null) {
                return false;
            }
            for (TaskRecord task : tasks.values()) {
                if (task.userId == userId && task.taskAffinity != null && task.taskAffinity.contains(packageName)) {
                    for (ActivityRecord activity : task.activities) {
                        if (!activity.finished) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            Slog.w(TAG, "isRunningApplication failed: " + e.getMessage());
            return false;
        }
    }
}
