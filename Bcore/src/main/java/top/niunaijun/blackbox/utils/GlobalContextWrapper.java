package top.niunaijun.blackbox.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/GlobalContextWrapper.class */
public class GlobalContextWrapper extends ContextWrapper {
    private static final String TAG = "GlobalContextWrapper";
    private final Context fallbackContext;
    private final String packageName;

    public GlobalContextWrapper(Context base, String packageName) {
        super(base != null ? base : BlackBoxCore.getContext());
        this.fallbackContext = BlackBoxCore.getContext();
        this.packageName = packageName;
    }

    public GlobalContextWrapper(Context base) {
        this(base, base != null ? base.getPackageName() : "unknown");
    }

    @Override // android.content.ContextWrapper
    public Context getBaseContext() {
        Context base = super.getBaseContext();
        return base != null ? base : this.fallbackContext;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        if (this.packageName != null) {
            return this.packageName;
        }
        return getBaseContext() != null ? getBaseContext().getPackageName() : "unknown";
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getResources();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting resources from base context: " + e.getMessage());
        }
        try {
            return this.fallbackContext.getResources();
        } catch (Exception e2) {
            Slog.w(TAG, "Error getting fallback resources: " + e2.getMessage());
            try {
                return new Resources(null, null, null);
            } catch (Exception e3) {
                Slog.w(TAG, "Error creating minimal resources: " + e3.getMessage());
                return null;
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public PackageManager getPackageManager() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getPackageManager();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting package manager from base context: " + e.getMessage());
        }
        try {
            return this.fallbackContext.getPackageManager();
        } catch (Exception e2) {
            Slog.w(TAG, "Error getting fallback package manager: " + e2.getMessage());
            return null;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getApplicationContext();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting application context from base context: " + e.getMessage());
        }
        return this.fallbackContext.getApplicationContext();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getClassLoader();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting class loader from base context: " + e.getMessage());
        }
        return this.fallbackContext.getClassLoader();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ContentResolver getContentResolver() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getContentResolver();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting content resolver from base context: " + e.getMessage());
        }
        return this.fallbackContext.getContentResolver();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getAssets();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting assets from base context: " + e.getMessage());
        }
        return this.fallbackContext.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        try {
            Context base = getBaseContext();
            if (base != null) {
                return base.getSystemService(name);
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting system service from base context: " + e.getMessage());
        }
        return this.fallbackContext.getSystemService(name);
    }

    public static Context createSafeContext(Context context, String packageName) {
        if (context == null) {
            return new GlobalContextWrapper(null, packageName);
        }
        if (context instanceof GlobalContextWrapper) {
            return context;
        }
        if (context instanceof ContextWrapper) {
            try {
                Context baseContext = ((ContextWrapper) context).getBaseContext();
                if (baseContext == null) {
                    return new GlobalContextWrapper(context, packageName);
                }
            } catch (Exception e) {
                Slog.w(TAG, "Error checking base context: " + e.getMessage());
                return new GlobalContextWrapper(context, packageName);
            }
        }
        return new GlobalContextWrapper(context, packageName);
    }

    public static Context createSafeContext(Context context) {
        String packageName = context != null ? context.getPackageName() : "unknown";
        return createSafeContext(context, packageName);
    }
}
