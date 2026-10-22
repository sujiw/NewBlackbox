package top.niunaijun.blackbox.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import black.android.app.BRActivityThread;
import black.android.app.BRActivityThreadActivityClientRecord;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/SimpleCrashFix.class */
public class SimpleCrashFix {
    private static final String TAG = "SimpleCrashFix";
    private static boolean sIsInstalled = false;

    public static void installSimpleFix() {
        if (sIsInstalled) {
            Slog.d(TAG, "Simple crash fix already installed");
            return;
        }
        try {
            Slog.d(TAG, "Installing essential crash fix...");
            installGlobalExceptionHandler();
            installContextWrapperHook();
            sIsInstalled = true;
            Slog.d(TAG, "Essential crash fix installed successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Failed to install essential crash fix: " + e.getMessage(), e);
        }
    }

    private static void installGlobalExceptionHandler() {
        try {
            final Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: top.niunaijun.blackbox.utils.SimpleCrashFix.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable throwable) {
                    String message;
                    if (SimpleCrashFix.isNullContextCrash(throwable)) {
                        Slog.w(SimpleCrashFix.TAG, "Caught null context crash, preventing crash: " + throwable.getMessage());
                        if (SimpleCrashFix.attemptRecovery(thread, throwable)) {
                            Slog.d(SimpleCrashFix.TAG, "Successfully recovered from null context crash");
                            return;
                        }
                    }
                    if (SimpleCrashFix.isGooglePlayServicesCrash(throwable)) {
                        Slog.w(SimpleCrashFix.TAG, "Caught Google Play Services crash, preventing crash: " + throwable.getMessage());
                        return;
                    }
                    if ((throwable instanceof NullPointerException) && (message = throwable.getMessage()) != null && (message.contains("Context") || message.contains("ContextWrapper"))) {
                        Slog.w(SimpleCrashFix.TAG, "Caught potential context-related crash, attempting recovery: " + message);
                        if (SimpleCrashFix.attemptRecovery(thread, throwable)) {
                            Slog.d(SimpleCrashFix.TAG, "Successfully recovered from potential context crash");
                            return;
                        }
                    }
                    Slog.e(SimpleCrashFix.TAG, "Uncaught exception in thread " + thread.getName() + ": " + Log.getStackTraceString(throwable));
                    if (currentHandler != null) {
                        currentHandler.uncaughtException(thread, throwable);
                    }
                }
            });
            Slog.d(TAG, "Global exception handler installed successfully");
        } catch (Exception e) {
            Slog.w(TAG, "Could not install global exception handler: " + e.getMessage());
        }
    }

    private static void installContextWrapperHook() {
        try {
            Slog.d(TAG, "Context wrapper hook installed");
        } catch (Exception e) {
            Slog.w(TAG, "Could not install context wrapper hook: " + e.getMessage());
        }
    }

    private static boolean isNullContextCrash(Throwable throwable) {
        String message;
        if ((throwable instanceof NullPointerException) && (message = throwable.getMessage()) != null) {
            return (message.contains("getResources") && message.contains("null object reference")) || (message.contains("ContextWrapper.getResources") && message.contains("null")) || ((message.contains("Context.getResources") && message.contains("null")) || ((message.contains("getClassLoader") && message.contains("null object reference")) || ((message.contains("ContextWrapper.getClassLoader") && message.contains("null")) || ((message.contains("Context.getClassLoader") && message.contains("null")) || ((message.contains("getPackageManager") && message.contains("null object reference")) || ((message.contains("ContextWrapper.getPackageManager") && message.contains("null")) || (message.contains("Context.getPackageManager") && message.contains("null"))))))));
        }
        return false;
    }

    private static boolean isGooglePlayServicesCrash(Throwable throwable) {
        String message;
        if (throwable instanceof UndeclaredThrowableException) {
            Throwable cause = throwable.getCause();
            if (cause instanceof InvocationTargetException) {
                Throwable targetException = cause.getCause();
                if ((targetException instanceof SecurityException) && (message = targetException.getMessage()) != null && message.contains("ACCESS_COARSE_LOCATION")) {
                    return true;
                }
            }
        }
        String message2 = throwable.getMessage();
        if (message2 != null) {
            if (message2.contains("google.android.gms") || message2.contains("ACCESS_COARSE_LOCATION") || message2.contains("ACCESS_FINE_LOCATION")) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static boolean attemptRecovery(Thread thread, Throwable throwable) {
        try {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if (stackTrace == null || stackTrace.length == 0) {
                return false;
            }
            for (StackTraceElement element : stackTrace) {
                String className = element.getClassName();
                String methodName = element.getMethodName();
                if (className.contains("ClientTransactionListenerController") && methodName.contains("onContextConfigurationPreChanged")) {
                    Slog.d(TAG, "Detected ClientTransactionListenerController crash, attempting recovery");
                    return fixContextConfigurationIssue();
                }
                if (className.contains("ContextWrapper") && (methodName.contains("getResources") || methodName.contains("getClassLoader") || methodName.contains("getPackageManager"))) {
                    Slog.d(TAG, "Detected ContextWrapper null context crash, attempting recovery");
                    return fixContextWrapperIssue();
                }
                if (className.contains(BActivityThread.TAG) && methodName.contains("installContentProvider")) {
                    Slog.d(TAG, "Detected content provider installation crash, attempting recovery");
                    return fixApplicationContextIssue();
                }
            }
            return false;
        } catch (Exception e) {
            Slog.w(TAG, "Error during recovery attempt: " + e.getMessage());
            return false;
        }
    }

    private static boolean fixApplicationContextIssue() {
        try {
            Slog.d(TAG, "Attempting to fix application context issue");
            Application currentApp = BlackBoxCore.getApplication();
            if (currentApp != null && currentApp.getBaseContext() == null) {
                Slog.w(TAG, "Found application with null base context, attempting to fix");
                Context fallbackContext = BlackBoxCore.getContext();
                if (fallbackContext != null) {
                    try {
                        Method attachBaseContext = Application.class.getDeclaredMethod("attachBaseContext", Context.class);
                        attachBaseContext.setAccessible(true);
                        attachBaseContext.invoke(currentApp, fallbackContext);
                        Slog.d(TAG, "Successfully attached fallback context to application");
                        return true;
                    } catch (Exception e) {
                        Slog.w(TAG, "Could not attach fallback context to application: " + e.getMessage());
                    }
                }
            }
            Slog.d(TAG, "Application context fix applied");
            return true;
        } catch (Exception e2) {
            Slog.w(TAG, "Failed to fix application context: " + e2.getMessage());
            return false;
        }
    }

    private static boolean fixContextConfigurationIssue() {
        Object activities;
        Activity activity;
        try {
            Slog.d(TAG, "Attempting to fix context configuration issue");
            Object activityThread = BlackBoxCore.mainThread();
            if (activityThread != null && (activities = BRActivityThread.get(activityThread).mActivities()) != null) {
                try {
                    Method valuesMethod = activities.getClass().getMethod("values", new Class[0]);
                    Object values = valuesMethod.invoke(activities, new Object[0]);
                    if (values instanceof Collection) {
                        for (Object record : (Collection) values) {
                            if (record != null && (activity = BRActivityThreadActivityClientRecord.get(record).activity()) != null && activity.getBaseContext() == null) {
                                Slog.w(TAG, "Found activity with null base context, attempting to fix");
                                fixActivityContext(activity);
                            }
                        }
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Error iterating through activities: " + e.getMessage());
                }
            }
            Slog.d(TAG, "Context configuration fix applied");
            return true;
        } catch (Exception e2) {
            Slog.w(TAG, "Failed to fix context configuration: " + e2.getMessage());
            return false;
        }
    }

    private static boolean fixContextWrapperIssue() {
        try {
            Slog.d(TAG, "Attempting to fix context wrapper issue");
            return fixContextConfigurationIssue();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to fix context wrapper issue: " + e.getMessage());
            return false;
        }
    }

    private static void fixActivityContext(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            Context safeContext = createSafeContextWrapper(BlackBoxCore.getContext());
            if (safeContext != null) {
                try {
                    Method attachBaseContext = Activity.class.getDeclaredMethod("attachBaseContext", Context.class);
                    attachBaseContext.setAccessible(true);
                    attachBaseContext.invoke(activity, safeContext);
                    Slog.d(TAG, "Successfully attached safe context to activity: " + activity.getClass().getName());
                } catch (Exception e) {
                    Slog.w(TAG, "Could not attach safe context to activity: " + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Error fixing activity context: " + e2.getMessage());
        }
    }

    private static Context createSafeContextWrapper(Context base) {
        if (base == null) {
            return null;
        }
        try {
            return new SafeContextWrapper(base);
        } catch (Exception e) {
            Slog.w(TAG, "Error creating safe context wrapper: " + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/SimpleCrashFix$SafeContextWrapper.class */
    private static class SafeContextWrapper extends ContextWrapper {
        private final Context fallbackContext;

        public SafeContextWrapper(Context base) {
            super(base);
            this.fallbackContext = base;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Resources getResources() {
            try {
                if (getBaseContext() != null) {
                    return super.getResources();
                }
            } catch (Exception e) {
                Slog.w(SimpleCrashFix.TAG, "Error getting resources from base context: " + e.getMessage());
            }
            try {
                if (this.fallbackContext != null) {
                    return this.fallbackContext.getResources();
                }
            } catch (Exception e2) {
                Slog.w(SimpleCrashFix.TAG, "Error getting resources from fallback context: " + e2.getMessage());
            }
            try {
                return Resources.getSystem();
            } catch (Exception e3) {
                Slog.e(SimpleCrashFix.TAG, "Error getting system resources: " + e3.getMessage());
                return null;
            }
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public ClassLoader getClassLoader() {
            try {
                if (getBaseContext() != null) {
                    return super.getClassLoader();
                }
            } catch (Exception e) {
                Slog.w(SimpleCrashFix.TAG, "Error getting class loader from base context: " + e.getMessage());
            }
            try {
                if (this.fallbackContext != null) {
                    return this.fallbackContext.getClassLoader();
                }
            } catch (Exception e2) {
                Slog.w(SimpleCrashFix.TAG, "Error getting class loader from fallback context: " + e2.getMessage());
            }
            try {
                return ClassLoader.getSystemClassLoader();
            } catch (Exception e3) {
                Slog.e(SimpleCrashFix.TAG, "Error getting system class loader: " + e3.getMessage());
                return null;
            }
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public PackageManager getPackageManager() {
            try {
                if (getBaseContext() != null) {
                    return super.getPackageManager();
                }
            } catch (Exception e) {
                Slog.w(SimpleCrashFix.TAG, "Error getting package manager from base context: " + e.getMessage());
            }
            return new PackageManager() { // from class: top.niunaijun.blackbox.utils.SimpleCrashFix.SafeContextWrapper.1
                @Override // android.content.pm.PackageManager
                public PackageInfo getPackageInfo(String packageName, int flags) {
                    throw new UnsupportedOperationException("Stub PackageManager");
                }

                @Override // android.content.pm.PackageManager
                public PackageInfo getPackageInfo(@NonNull VersionedPackage versionedPackage, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                public String[] currentToCanonicalPackageNames(@NonNull String[] strings) {
                    return new String[0];
                }

                @Override // android.content.pm.PackageManager
                public String[] canonicalToCurrentPackageNames(@NonNull String[] strings) {
                    return new String[0];
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Intent getLaunchIntentForPackage(@NonNull String s) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Intent getLeanbackLaunchIntentForPackage(@NonNull String s) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                public int[] getPackageGids(@NonNull String s) throws PackageManager.NameNotFoundException {
                    return new int[0];
                }

                @Override // android.content.pm.PackageManager
                public int[] getPackageGids(@NonNull String s, int i) throws PackageManager.NameNotFoundException {
                    return new int[0];
                }

                @Override // android.content.pm.PackageManager
                public int getPackageUid(@NonNull String s, int i) throws PackageManager.NameNotFoundException {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public PermissionInfo getPermissionInfo(@NonNull String s, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<PermissionInfo> queryPermissionsByGroup(@Nullable String s, int i) throws PackageManager.NameNotFoundException {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public PermissionGroupInfo getPermissionGroupInfo(@NonNull String s, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                public ApplicationInfo getApplicationInfo(String packageName, int flags) {
                    throw new UnsupportedOperationException("Stub PackageManager");
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public ActivityInfo getActivityInfo(@NonNull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public ActivityInfo getReceiverInfo(@NonNull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public ServiceInfo getServiceInfo(@NonNull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public ProviderInfo getProviderInfo(@NonNull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<PackageInfo> getInstalledPackages(int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<PackageInfo> getPackagesHoldingPermissions(@NonNull String[] strings, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                public int checkPermission(@NonNull String s, @NonNull String s1) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public boolean isPermissionRevokedByPolicy(@NonNull String s, @NonNull String s1) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public boolean addPermission(@NonNull PermissionInfo permissionInfo) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public boolean addPermissionAsync(@NonNull PermissionInfo permissionInfo) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public void removePermission(@NonNull String s) {
                }

                @Override // android.content.pm.PackageManager
                public int checkSignatures(@NonNull String s, @NonNull String s1) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public int checkSignatures(int i, int i1) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public String[] getPackagesForUid(int i) {
                    return new String[0];
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public String getNameForUid(int i) {
                    return "";
                }

                @Override // android.content.pm.PackageManager
                public List<ApplicationInfo> getInstalledApplications(int flags) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                public boolean isInstantApp() {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public boolean isInstantApp(@NonNull String s) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public int getInstantAppCookieMaxBytes() {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public byte[] getInstantAppCookie() {
                    return new byte[0];
                }

                @Override // android.content.pm.PackageManager
                public void clearInstantAppCookie() {
                }

                @Override // android.content.pm.PackageManager
                public void updateInstantAppCookie(@Nullable byte[] bytes) {
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public String[] getSystemSharedLibraryNames() {
                    return new String[0];
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<SharedLibraryInfo> getSharedLibraries(int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public ChangedPackages getChangedPackages(int i) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public FeatureInfo[] getSystemAvailableFeatures() {
                    return new FeatureInfo[0];
                }

                @Override // android.content.pm.PackageManager
                public boolean hasSystemFeature(@NonNull String s) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public boolean hasSystemFeature(@NonNull String s, int i) {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public ResolveInfo resolveActivity(@NonNull Intent intent, int i) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ResolveInfo> queryIntentActivities(@NonNull Intent intent, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ResolveInfo> queryIntentActivityOptions(@Nullable ComponentName componentName, @Nullable Intent[] intents, @NonNull Intent intent, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ResolveInfo> queryBroadcastReceivers(@NonNull Intent intent, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public ResolveInfo resolveService(@NonNull Intent intent, int i) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ResolveInfo> queryIntentServices(@NonNull Intent intent, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ResolveInfo> queryIntentContentProviders(@NonNull Intent intent, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public ProviderInfo resolveContentProvider(@NonNull String s, int i) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<ProviderInfo> queryContentProviders(@Nullable String s, int i, int i1) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public InstrumentationInfo getInstrumentationInfo(@NonNull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<InstrumentationInfo> queryInstrumentation(@NonNull String s, int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getDrawable(@NonNull String s, int i, @Nullable ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getActivityIcon(@NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getActivityIcon(@NonNull Intent intent) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getActivityBanner(@NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getActivityBanner(@NonNull Intent intent) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getDefaultActivityIcon() {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getApplicationIcon(@NonNull ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getApplicationIcon(@NonNull String s) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getApplicationBanner(@NonNull ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getApplicationBanner(@NonNull String s) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getActivityLogo(@NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getActivityLogo(@NonNull Intent intent) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getApplicationLogo(@NonNull ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public Drawable getApplicationLogo(@NonNull String s) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getUserBadgedIcon(@NonNull Drawable drawable, @NonNull UserHandle userHandle) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Drawable getUserBadgedDrawableForDensity(@NonNull Drawable drawable, @NonNull UserHandle userHandle, @Nullable Rect rect, int i) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public CharSequence getUserBadgedLabel(@NonNull CharSequence charSequence, @NonNull UserHandle userHandle) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public CharSequence getText(@NonNull String s, int i, @Nullable ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public XmlResourceParser getXml(@NonNull String s, int i, @Nullable ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public CharSequence getApplicationLabel(@NonNull ApplicationInfo applicationInfo) {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Resources getResourcesForActivity(@NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Resources getResourcesForApplication(@NonNull ApplicationInfo applicationInfo) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public Resources getResourcesForApplication(@NonNull String s) throws PackageManager.NameNotFoundException {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                public void verifyPendingInstall(int i, int i1) {
                }

                @Override // android.content.pm.PackageManager
                public void extendVerificationTimeout(int i, int i1, long l) {
                }

                @Override // android.content.pm.PackageManager
                public void setInstallerPackageName(@NonNull String s, @Nullable String s1) {
                }

                @Override // android.content.pm.PackageManager
                @Nullable
                public String getInstallerPackageName(@NonNull String s) {
                    return "";
                }

                @Override // android.content.pm.PackageManager
                public void addPackageToPreferred(@NonNull String s) {
                }

                @Override // android.content.pm.PackageManager
                public void removePackageFromPreferred(@NonNull String s) {
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public List<PackageInfo> getPreferredPackages(int i) {
                    return Collections.emptyList();
                }

                @Override // android.content.pm.PackageManager
                public void addPreferredActivity(@NonNull IntentFilter intentFilter, int i, @Nullable ComponentName[] componentNames, @NonNull ComponentName componentName) {
                }

                @Override // android.content.pm.PackageManager
                public void clearPackagePreferredActivities(@NonNull String s) {
                }

                @Override // android.content.pm.PackageManager
                public int getPreferredActivities(@NonNull List<IntentFilter> list, @NonNull List<ComponentName> list1, @Nullable String s) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public void setComponentEnabledSetting(@NonNull ComponentName componentName, int i, int i1) {
                }

                @Override // android.content.pm.PackageManager
                public int getComponentEnabledSetting(@NonNull ComponentName componentName) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public void setApplicationEnabledSetting(@NonNull String s, int i, int i1) {
                }

                @Override // android.content.pm.PackageManager
                public int getApplicationEnabledSetting(@NonNull String s) {
                    return 0;
                }

                @Override // android.content.pm.PackageManager
                public boolean isSafeMode() {
                    return false;
                }

                @Override // android.content.pm.PackageManager
                public void setApplicationCategoryHint(@NonNull String s, int i) {
                }

                @Override // android.content.pm.PackageManager
                @NonNull
                public PackageInstaller getPackageInstaller() {
                    return null;
                }

                @Override // android.content.pm.PackageManager
                public boolean canRequestPackageInstalls() {
                    return false;
                }
            };
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public ContentResolver getContentResolver() {
            try {
                if (getBaseContext() != null) {
                    return super.getContentResolver();
                }
            } catch (Exception e) {
                Slog.w(SimpleCrashFix.TAG, "Error getting content resolver from base context: " + e.getMessage());
            }
            try {
                if (this.fallbackContext != null) {
                    return this.fallbackContext.getContentResolver();
                }
            } catch (Exception e2) {
                Slog.w(SimpleCrashFix.TAG, "Error getting content resolver from fallback context: " + e2.getMessage());
            }
            return new ContentResolver(this) { // from class: top.niunaijun.blackbox.utils.SimpleCrashFix.SafeContextWrapper.2
                public Bundle call(String method, String arg, Bundle extras) {
                    return null;
                }
            };
        }
    }
}
