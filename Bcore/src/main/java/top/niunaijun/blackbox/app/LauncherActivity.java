package top.niunaijun.blackbox.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.R;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/LauncherActivity.class */
public class LauncherActivity extends Activity {
    public static final String TAG = "SplashScreen";
    public static final String KEY_INTENT = "launch_intent";
    public static final String KEY_PKG = "launch_pkg";
    public static final String KEY_USER_ID = "launch_user_id";
    private boolean isRunning = false;

    public static void launch(Intent intent, int userId) {
        try {
            Intent splash = new Intent();
            splash.setClass(BlackBoxCore.getContext(), LauncherActivity.class);
            splash.setFlags(268435456);
            splash.putExtra(KEY_INTENT, intent);
            splash.putExtra(KEY_PKG, intent.getPackage());
            splash.putExtra(KEY_USER_ID, userId);
            BlackBoxCore.getContext().startActivity(splash);
            Slog.d(TAG, "LauncherActivity.launch() called for package: " + intent.getPackage());
        } catch (Exception e) {
            Slog.e(TAG, "Error in LauncherActivity.launch()", e);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            if (intent == null) {
                Slog.w(TAG, "Intent is null, finishing activity");
                finish();
                return;
            }
            Intent launchIntent = (Intent) intent.getParcelableExtra(KEY_INTENT);
            String packageName = intent.getStringExtra(KEY_PKG);
            int userId = intent.getIntExtra(KEY_USER_ID, 0);
            if (launchIntent == null || packageName == null) {
                Slog.w(TAG, "Missing launch intent or package name, finishing activity");
                finish();
                return;
            }
            Slog.d(TAG, "LauncherActivity.onCreate() for package: " + packageName + ", userId: " + userId);
            PackageInfo packageInfo = getPackageInfoWithFallback(packageName, userId);
            if (packageInfo == null) {
                Slog.w(TAG, "Package info not available for " + packageName + ", but proceeding with launch");
            } else {
                Slog.d(TAG, "Successfully retrieved package info for " + packageName);
            }
            Drawable drawable = null;
            String appName = packageName;
            if (packageInfo != null) {
                try {
                    if (packageInfo.applicationInfo != null) {
                        PackageManager pm = getPackageManager();
                        drawable = pm.getApplicationIcon(packageInfo.applicationInfo);
                        CharSequence label = pm.getApplicationLabel(packageInfo.applicationInfo);
                        if (label != null) {
                            appName = label.toString();
                        }
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Failed to load app icon or name for " + packageName + ": " + e.getMessage());
                }
            }
            setContentView(R.layout.activity_launcher);
            ImageView iconView = (ImageView) findViewById(R.id.iv_icon);
            TextView nameView = (TextView) findViewById(R.id.tv_app_name);
            if (nameView != null) {
                nameView.setText(appName);
                nameView.setAlpha(0.0f);
                nameView.animate().alpha(1.0f).setDuration(500L).setStartDelay(200L).start();
            }
            if (iconView != null && drawable != null) {
                iconView.setImageDrawable(drawable);
                iconView.setScaleX(0.7f);
                iconView.setScaleY(0.7f);
                iconView.setAlpha(0.0f);
                iconView.animate().scaleX(1.1f).scaleY(1.1f).alpha(1.0f).setDuration(350L).setInterpolator(new OvershootInterpolator()).withEndAction(() -> {
                    iconView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150L).setInterpolator(new DecelerateInterpolator()).start();
                }).start();
            }
            launchAppAsync(launchIntent, userId);
        } catch (Exception e2) {
            Slog.e(TAG, "Critical error in LauncherActivity.onCreate()", e2);
            finish();
        }
    }

    private PackageInfo getPackageInfoWithFallback(String packageName, int userId) {
        try {
            return BlackBoxCore.getBPackageManager().getPackageInfo(packageName, 0, userId);
        } catch (Exception e) {
            Slog.w(TAG, "Failed to get package info for " + packageName + " (attempt 1): " + e.getMessage());
            try {
                return BlackBoxCore.getBPackageManager().getPackageInfo(packageName, 128, userId);
            } catch (Exception e2) {
                Slog.w(TAG, "Failed to get package info for " + packageName + " (attempt 2): " + e2.getMessage());
                try {
                    ApplicationInfo appInfo = BlackBoxCore.getBPackageManager().getApplicationInfo(packageName, 0, userId);
                    if (appInfo != null) {
                        PackageInfo fallbackInfo = new PackageInfo();
                        fallbackInfo.packageName = packageName;
                        fallbackInfo.applicationInfo = appInfo;
                        fallbackInfo.versionCode = 1;
                        fallbackInfo.versionName = "1.0";
                        fallbackInfo.firstInstallTime = System.currentTimeMillis();
                        fallbackInfo.lastUpdateTime = System.currentTimeMillis();
                        Slog.d(TAG, "Created fallback PackageInfo for " + packageName);
                        return fallbackInfo;
                    }
                    return null;
                } catch (Exception e3) {
                    Slog.w(TAG, "Failed to get application info for " + packageName + ": " + e3.getMessage());
                    return null;
                }
            }
        }
    }

    private void launchAppAsync(Intent launchIntent, int userId) {
        new Thread(() -> {
            try {
                Slog.d(TAG, "Starting app launch in background thread");
                Thread.sleep(100L);
                BlackBoxCore.getBActivityManager().startActivity(launchIntent, userId);
                Slog.d(TAG, "App launch initiated successfully");
            } catch (Exception e) {
                Slog.e(TAG, "Error launching app", e);
                runOnUiThread(() -> {
                    try {
                        Slog.e(TAG, "Failed to launch app: " + e.getMessage());
                    } catch (Exception uiException) {
                        Slog.e(TAG, "Error showing error message", uiException);
                    }
                });
            }
        }, "AppLaunchThread").start();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isRunning = true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isRunning) {
            finish();
        }
    }
}
