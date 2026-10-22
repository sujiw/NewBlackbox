package top.niunaijun.blackbox.closecode;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.io.File;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.configuration.AppLifecycleCallback;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/closecode/Entry.class */
public class Entry {
    private static final String TAG = "Lib Injection";
    private static final String targetpackagename = "com.whatever";

    public static void attach() {
        BlackBoxCore.get().addAppLifecycleCallback(new AppLifecycleCallback() { // from class: top.niunaijun.blackbox.closecode.Entry.1
            @Override // top.niunaijun.blackbox.app.configuration.AppLifecycleCallback
            public void beforeMainApplicationAttach(Application app, Context context) {
                if (app != null && app.getPackageName().equals(Entry.targetpackagename)) {
                    String dataDir = app.getFilesDir().getAbsolutePath();
                    String libPath = dataDir + "/inject.so";
                    try {
                        File file = new File(libPath);
                        if (file.exists()) {
                            System.load(libPath);
                            Slog.d(Entry.TAG, "Injected native lib before Application created: " + libPath + " for package: " + Entry.targetpackagename);
                        } else {
                            Slog.d(Entry.TAG, "Failed to inject because lib not found");
                        }
                    } catch (Throwable t) {
                        Slog.e(Entry.TAG, "Failed to inject native lib before Application created: " + libPath + " for package: " + Entry.targetpackagename, t);
                    }
                }
            }

            @Override // top.niunaijun.blackbox.app.configuration.AppLifecycleCallback
            public void afterMainActivityOnCreate(Activity activity) {
                if (activity != null && activity.getPackageName().equals(Entry.targetpackagename)) {
                    String dataDir = activity.getFilesDir().getAbsolutePath();
                    String libPath = dataDir + "/inject.so";
                    try {
                        File file = new File(libPath);
                        if (file.exists()) {
                            System.load(libPath);
                            Slog.d(Entry.TAG, "Injected native lib before Application created: " + libPath + " for package: " + Entry.targetpackagename);
                        } else {
                            Slog.d(Entry.TAG, "Failed to inject because lib not found");
                        }
                    } catch (Throwable t) {
                        Slog.e(Entry.TAG, "Failed to inject native lib after main activity onCreate: " + libPath + " for package: " + Entry.targetpackagename, t);
                    }
                }
            }
        });
        Slog.d(TAG, "Custom closed code initialized!");
    }
}
