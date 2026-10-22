package top.niunaijun.blackbox.app.configuration;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/configuration/AppLifecycleCallback.class */
public class AppLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    public static AppLifecycleCallback EMPTY = new AppLifecycleCallback() { // from class: top.niunaijun.blackbox.app.configuration.AppLifecycleCallback.1
    };

    public void beforeMainLaunchApk(String packageName, int userid) {
    }

    public void beforeMainApplicationAttach(Application app, Context context) {
    }

    public void afterMainApplicationAttach(Application app, Context context) {
    }

    public void beforeMainActivityOnCreate(Activity activity) {
    }

    public void afterMainActivityOnCreate(Activity activity) {
    }

    public void beforeCreateApplication(String packageName, String processName, Context context, int userId) {
    }

    public void beforeApplicationOnCreate(String packageName, String processName, Application application, int userId) {
    }

    public void afterApplicationOnCreate(String packageName, String processName, Application application, int userId) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }
}
