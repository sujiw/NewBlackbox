package top.niunaijun.blackbox.fake.frameworks;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.IInjectHook;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/frameworks/BResourcesManager.class */
public class BResourcesManager implements IInjectHook {
    private static final String TAG = "BResourcesManager";

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public void injectHook() {
        Log.d(TAG, "BResourcesManager hook initialized");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    public static String safeLoadAppLabel(Object applicationInfo) {
        Object label;
        Object packageManager;
        if (applicationInfo == null) {
            return "Unknown App";
        }
        String packageName = getPackageNameSafely(applicationInfo);
        if (packageName != null && !packageName.isEmpty()) {
            return packageName;
        }
        try {
            Method getLabelResMethod = applicationInfo.getClass().getMethod("getLabelRes", new Class[0]);
            Integer labelRes = (Integer) getLabelResMethod.invoke(applicationInfo, new Object[0]);
            if (labelRes != null && labelRes.intValue() != 0 && (packageManager = getPackageManager()) != null) {
                Method getTextMethod = packageManager.getClass().getMethod("getText", String.class, Integer.TYPE, ApplicationInfo.class);
                Object label2 = getTextMethod.invoke(packageManager, packageName, labelRes, applicationInfo);
                if (label2 != null) {
                    return label2.toString();
                }
            }
            Method loadLabelMethod = applicationInfo.getClass().getMethod("loadLabel", PackageManager.class);
            Object packageManager2 = getPackageManager();
            if (packageManager2 != null && (label = loadLabelMethod.invoke(applicationInfo, packageManager2)) != null) {
                return label.toString();
            }
            return "Unknown App";
        } catch (Exception e) {
            Log.w(TAG, "Failed to load app label: " + e.getMessage());
            return "Unknown App";
        }
    }

    private static String getPackageNameSafely(Object applicationInfo) {
        try {
            Field packageNameField = applicationInfo.getClass().getDeclaredField("packageName");
            packageNameField.setAccessible(true);
            Object packageName = packageNameField.get(applicationInfo);
            if (packageName != null) {
                return packageName.toString();
            }
        } catch (Exception e) {
            Log.w(TAG, "Failed to get package name via field: " + e.getMessage());
        }
        try {
            Method getPackageNameMethod = applicationInfo.getClass().getMethod("getPackageName", new Class[0]);
            Object packageName2 = getPackageNameMethod.invoke(applicationInfo, new Object[0]);
            if (packageName2 != null) {
                return packageName2.toString();
            }
        } catch (Exception e2) {
            Log.w(TAG, "Failed to get package name via method: " + e2.getMessage());
        }
        try {
            String toString = applicationInfo.toString();
            if (toString.contains("packageName=")) {
                int start = toString.indexOf("packageName=") + 12;
                int end = toString.indexOf(" ", start);
                if (end == -1) {
                    end = toString.length();
                }
                return toString.substring(start, end);
            }
            return null;
        } catch (Exception e3) {
            Log.w(TAG, "Failed to get package name via toString: " + e3.getMessage());
            return null;
        }
    }

    public static Object safeLoadAppIcon(Object applicationInfo) {
        Object packageManager;
        if (applicationInfo != null) {
            try {
                Method getIconMethod = applicationInfo.getClass().getMethod("getIcon", new Class[0]);
                Integer iconRes = (Integer) getIconMethod.invoke(applicationInfo, new Object[0]);
                if (iconRes != null && iconRes.intValue() != 0 && (packageManager = getPackageManager()) != null) {
                    try {
                        Method getDrawableMethod = packageManager.getClass().getMethod("getDrawable", String.class, Integer.TYPE, ApplicationInfo.class);
                        Object drawable = getDrawableMethod.invoke(packageManager, getPackageName(applicationInfo), iconRes, applicationInfo);
                        if (drawable != null) {
                            return drawable;
                        }
                    } catch (Exception e) {
                        Log.w(TAG, "Failed to load icon via getDrawable: " + e.getMessage());
                    }
                }
                Method loadIconMethod = applicationInfo.getClass().getMethod("loadIcon", PackageManager.class);
                Object packageManager2 = getPackageManager();
                if (packageManager2 != null) {
                    return loadIconMethod.invoke(applicationInfo, packageManager2);
                }
                return null;
            } catch (Exception e2) {
                Log.w(TAG, "Failed to load app icon: " + e2.getMessage());
                return null;
            }
        }
        return null;
    }

    private static String getPackageName(Object applicationInfo) {
        String packageName = getPackageNameSafely(applicationInfo);
        return packageName != null ? packageName : "";
    }

    private static Object getPackageManager() {
        try {
            Class<?> blackBoxCoreClass = Class.forName("top.niunaijun.blackbox.BlackBoxCore");
            Method getPackageManagerMethod = blackBoxCoreClass.getMethod("getPackageManager", new Class[0]);
            return getPackageManagerMethod.invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.w(TAG, "Failed to get PackageManager: " + e.getMessage());
            return null;
        }
    }

    public static Object createSafeResourceManager(Context context) {
        try {
            Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
            Object resourcesManager = resourcesManagerClass.newInstance();
            try {
                Field disableOverlayField = resourcesManagerClass.getDeclaredField("mDisableOverlayLoading");
                disableOverlayField.setAccessible(true);
                disableOverlayField.setBoolean(resourcesManager, true);
            } catch (Exception e) {
                Log.w(TAG, "Could not set overlay loading flag: " + e.getMessage());
            }
            return resourcesManager;
        } catch (Exception e2) {
            Log.w(TAG, "Failed to create safe resource manager: " + e2.getMessage());
            return null;
        }
    }

    public static boolean isProblematicOverlayPath(String path) {
        return path != null && path.contains("/data/resource-cache/") && path.contains(".frro");
    }
}
