package top.niunaijun.blackbox.fake.delegate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.configuration.AppLifecycleCallback;
import top.niunaijun.blackbox.utils.Reflector;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/delegate/BaseInstrumentationDelegate.class */
public class BaseInstrumentationDelegate extends Instrumentation {
    protected Instrumentation mBaseInstrumentation;

    @Override // android.app.Instrumentation
    public void onCreate(Bundle arguments) {
        this.mBaseInstrumentation.onCreate(arguments);
    }

    @Override // android.app.Instrumentation
    public void start() {
        this.mBaseInstrumentation.start();
    }

    @Override // android.app.Instrumentation
    public void onStart() {
        this.mBaseInstrumentation.onStart();
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable e) {
        return this.mBaseInstrumentation.onException(obj, e);
    }

    @Override // android.app.Instrumentation
    public void sendStatus(int resultCode, Bundle results) {
        this.mBaseInstrumentation.sendStatus(resultCode, results);
    }

    @Override // android.app.Instrumentation
    public void addResults(Bundle results) {
        this.mBaseInstrumentation.addResults(results);
    }

    @Override // android.app.Instrumentation
    public void finish(int resultCode, Bundle results) {
        this.mBaseInstrumentation.finish(resultCode, results);
    }

    @Override // android.app.Instrumentation
    public void setAutomaticPerformanceSnapshots() {
        this.mBaseInstrumentation.setAutomaticPerformanceSnapshots();
    }

    @Override // android.app.Instrumentation
    public void startPerformanceSnapshot() {
        this.mBaseInstrumentation.startPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void endPerformanceSnapshot() {
        this.mBaseInstrumentation.endPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        this.mBaseInstrumentation.onDestroy();
    }

    @Override // android.app.Instrumentation
    public Context getContext() {
        return this.mBaseInstrumentation.getContext();
    }

    @Override // android.app.Instrumentation
    public ComponentName getComponentName() {
        return this.mBaseInstrumentation.getComponentName();
    }

    @Override // android.app.Instrumentation
    public Context getTargetContext() {
        return this.mBaseInstrumentation.getTargetContext();
    }

    @Override // android.app.Instrumentation
    public boolean isProfiling() {
        return this.mBaseInstrumentation.isProfiling();
    }

    @Override // android.app.Instrumentation
    public void startProfiling() {
        this.mBaseInstrumentation.startProfiling();
    }

    @Override // android.app.Instrumentation
    public void stopProfiling() {
        this.mBaseInstrumentation.stopProfiling();
    }

    @Override // android.app.Instrumentation
    public void setInTouchMode(boolean inTouch) {
        this.mBaseInstrumentation.setInTouchMode(inTouch);
    }

    @Override // android.app.Instrumentation
    public void waitForIdle(Runnable recipient) {
        this.mBaseInstrumentation.waitForIdle(recipient);
    }

    @Override // android.app.Instrumentation
    public void waitForIdleSync() {
        this.mBaseInstrumentation.waitForIdleSync();
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runner) {
        this.mBaseInstrumentation.runOnMainSync(runner);
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(Intent intent) {
        return this.mBaseInstrumentation.startActivitySync(intent);
    }

    @Override // android.app.Instrumentation
    public void addMonitor(Instrumentation.ActivityMonitor monitor) {
        this.mBaseInstrumentation.addMonitor(monitor);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(IntentFilter filter, Instrumentation.ActivityResult result, boolean block) {
        return this.mBaseInstrumentation.addMonitor(filter, result, block);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(String cls, Instrumentation.ActivityResult result, boolean block) {
        return this.mBaseInstrumentation.addMonitor(cls, result, block);
    }

    @Override // android.app.Instrumentation
    public boolean checkMonitorHit(Instrumentation.ActivityMonitor monitor, int minHits) {
        return this.mBaseInstrumentation.checkMonitorHit(monitor, minHits);
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitor(Instrumentation.ActivityMonitor monitor) {
        return this.mBaseInstrumentation.waitForMonitor(monitor);
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor monitor, long timeOut) {
        return this.mBaseInstrumentation.waitForMonitorWithTimeout(monitor, timeOut);
    }

    @Override // android.app.Instrumentation
    public void removeMonitor(Instrumentation.ActivityMonitor monitor) {
        this.mBaseInstrumentation.removeMonitor(monitor);
    }

    @Override // android.app.Instrumentation
    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return this.mBaseInstrumentation.invokeMenuActionSync(targetActivity, id, flag);
    }

    @Override // android.app.Instrumentation
    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return this.mBaseInstrumentation.invokeContextMenuAction(targetActivity, id, flag);
    }

    @Override // android.app.Instrumentation
    public void sendStringSync(String text) {
        this.mBaseInstrumentation.sendStringSync(text);
    }

    @Override // android.app.Instrumentation
    public void sendKeySync(KeyEvent event) {
        this.mBaseInstrumentation.sendKeySync(event);
    }

    @Override // android.app.Instrumentation
    public void sendKeyDownUpSync(int key) {
        this.mBaseInstrumentation.sendKeyDownUpSync(key);
    }

    @Override // android.app.Instrumentation
    public void sendCharacterSync(int keyCode) {
        this.mBaseInstrumentation.sendCharacterSync(keyCode);
    }

    @Override // android.app.Instrumentation
    public void sendPointerSync(MotionEvent event) {
        this.mBaseInstrumentation.sendPointerSync(event);
    }

    @Override // android.app.Instrumentation
    public void sendTrackballEventSync(MotionEvent event) {
        this.mBaseInstrumentation.sendTrackballEventSync(event);
    }

    @Override // android.app.Instrumentation
    public Application newApplication(ClassLoader cl, String className, Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return this.mBaseInstrumentation.newApplication(cl, className, context);
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application app) {
        this.mBaseInstrumentation.callApplicationOnCreate(app);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException {
        return this.mBaseInstrumentation.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return this.mBaseInstrumentation.newActivity(cl, className, intent);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        this.mBaseInstrumentation.callActivityOnCreate(activity, icicle);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityCreated(activity, icicle);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        this.mBaseInstrumentation.callActivityOnCreate(activity, icicle, persistentState);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityCreated(activity, icicle);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        this.mBaseInstrumentation.callActivityOnDestroy(activity);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityDestroyed(activity);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        this.mBaseInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState, PersistableBundle persistentState) {
        this.mBaseInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPostCreate(Activity activity, Bundle icicle) {
        this.mBaseInstrumentation.callActivityOnPostCreate(activity, icicle);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPostCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        this.mBaseInstrumentation.callActivityOnPostCreate(activity, icicle, persistentState);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        this.mBaseInstrumentation.callActivityOnNewIntent(activity, intent);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        this.mBaseInstrumentation.callActivityOnStart(activity);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityStarted(activity);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        this.mBaseInstrumentation.callActivityOnRestart(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        this.mBaseInstrumentation.callActivityOnResume(activity);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityResumed(activity);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        this.mBaseInstrumentation.callActivityOnStop(activity);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityStopped(activity);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        this.mBaseInstrumentation.callActivityOnSaveInstanceState(activity, outState);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState, PersistableBundle outPersistentState) {
        this.mBaseInstrumentation.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivitySaveInstanceState(activity, outState);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        this.mBaseInstrumentation.callActivityOnPause(activity);
        for (AppLifecycleCallback appLifecycleCallback : BlackBoxCore.get().getAppLifecycleCallbacks()) {
            appLifecycleCallback.onActivityPaused(activity);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnUserLeaving(Activity activity) {
        this.mBaseInstrumentation.callActivityOnUserLeaving(activity);
    }

    @Override // android.app.Instrumentation
    public void startAllocCounting() {
        this.mBaseInstrumentation.startAllocCounting();
    }

    @Override // android.app.Instrumentation
    public void stopAllocCounting() {
        this.mBaseInstrumentation.stopAllocCounting();
    }

    @Override // android.app.Instrumentation
    public Bundle getAllocCounts() {
        return this.mBaseInstrumentation.getAllocCounts();
    }

    @Override // android.app.Instrumentation
    public Bundle getBinderCounts() {
        return this.mBaseInstrumentation.getBinderCounts();
    }

    @Override // android.app.Instrumentation
    public UiAutomation getUiAutomation() {
        return this.mBaseInstrumentation.getUiAutomation();
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder binder, IBinder binder1, Activity activity, Intent intent, int i, Bundle bundle) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class).callByCaller(this.mBaseInstrumentation, context, binder, binder1, activity, intent, Integer.valueOf(i), bundle);
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder binder, IBinder binder1, String str, Intent intent, int i, Bundle bundle) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, String.class, Intent.class, Integer.TYPE, Bundle.class).callByCaller(this.mBaseInstrumentation, context, binder, binder1, str, intent, Integer.valueOf(i), bundle);
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder binder, IBinder binder1, Fragment fragment, Intent intent, int i) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE).callByCaller(this.mBaseInstrumentation, context, binder, binder1, fragment, intent, Integer.valueOf(i));
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder binder, IBinder binder1, Activity activity, Intent intent, int i) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE).callByCaller(this.mBaseInstrumentation, context, binder, binder1, activity, intent, Integer.valueOf(i));
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder binder, IBinder binder1, Fragment fragment, Intent intent, int i, Bundle bundle) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE, Bundle.class).callByCaller(this.mBaseInstrumentation, context, binder, binder1, fragment, intent, Integer.valueOf(i), bundle);
    }

    @TargetApi(17)
    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle, UserHandle userHandle) throws Throwable {
        return (Instrumentation.ActivityResult) invokeExecStartActivity(this.mBaseInstrumentation, Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class, UserHandle.class).callByCaller(this.mBaseInstrumentation, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle, userHandle);
    }

    private static Reflector invokeExecStartActivity(Object obj, Class<?>... args) throws NoSuchMethodException {
        Class<?> superclass = obj.getClass();
        while (true) {
            Class<?> cls = superclass;
            if (cls != null) {
                try {
                    return Reflector.on(obj.getClass()).method("execStartActivity", args);
                } catch (Exception e) {
                    superclass = cls.getSuperclass();
                }
            } else {
                throw new NoSuchMethodException();
            }
        }
    }
}
