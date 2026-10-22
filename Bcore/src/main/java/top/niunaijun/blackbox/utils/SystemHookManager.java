package top.niunaijun.blackbox.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.List;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/SystemHookManager.class */
public class SystemHookManager {
    private static final String TAG = "SystemHookManager";

    public static void installAllHooks() {
        try {
            Log.d(TAG, "Installing system hooks...");
            hookClientTransactionListenerController();
            hookConfigurationController();
            hookActivityThread();
            Log.d(TAG, "System hooks installed successfully");
        } catch (Exception e) {
            Log.e(TAG, "Failed to install system hooks: " + e.getMessage(), e);
        }
    }

    private static void hookClientTransactionListenerController() {
        try {
            Class<?> controllerClass = Class.forName("android.app.servertransaction.ClientTransactionListenerController");
            if (controllerClass != null) {
                Log.d(TAG, "Found ClientTransactionListenerController class");
                Object proxy = Proxy.newProxyInstance(controllerClass.getClassLoader(), new Class[]{controllerClass}, (proxyObj, method, args) -> {
                    if ("onContextConfigurationPreChanged".equals(method.getName())) {
                        try {
                            ensureAllActivitiesHaveContext();
                            if (args != null && args.length > 0) {
                                return method.invoke(proxyObj, args);
                            }
                            Log.w(TAG, "ClientTransactionListenerController called with null args, skipping");
                            return null;
                        } catch (Exception e) {
                            Log.w(TAG, "Error in ClientTransactionListenerController proxy: " + e.getMessage());
                            return null;
                        }
                    }
                    return method.invoke(proxyObj, args);
                });
                replaceControllerInstance(controllerClass, proxy, "ClientTransactionListenerController");
            }
        } catch (Exception e) {
            Log.w(TAG, "Could not hook ClientTransactionListenerController: " + e.getMessage());
        }
    }

    private static void hookConfigurationController() {
        try {
            Class<?> controllerClass = Class.forName("android.app.ConfigurationController");
            if (controllerClass != null) {
                Log.d(TAG, "Found ConfigurationController class");
                Object proxy = Proxy.newProxyInstance(controllerClass.getClassLoader(), new Class[]{controllerClass}, (proxyObj, method, args) -> {
                    if ("handleConfigurationChanged".equals(method.getName())) {
                        try {
                            ensureAllActivitiesHaveContext();
                            if (args != null && args.length > 0) {
                                return method.invoke(proxyObj, args);
                            }
                            Log.w(TAG, "ConfigurationController called with null args, skipping");
                            return null;
                        } catch (Exception e) {
                            Log.w(TAG, "Error in ConfigurationController proxy: " + e.getMessage());
                            return null;
                        }
                    }
                    return method.invoke(proxyObj, args);
                });
                replaceControllerInstance(controllerClass, proxy, "ConfigurationController");
            }
        } catch (Exception e) {
            Log.w(TAG, "Could not hook ConfigurationController: " + e.getMessage());
        }
    }

    private static void hookActivityThread() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            if (activityThreadClass != null) {
                Log.d(TAG, "Found ActivityThread class");
                Object activityThread = BlackBoxCore.mainThread();
                if (activityThread != null) {
                    Log.d(TAG, "Found ActivityThread instance");
                    try {
                        Method handleLaunchActivity = activityThreadClass.getDeclaredMethod("handleLaunchActivity", Object.class, Intent.class, Object.class, Object.class, Object.class, String.class, Object.class, Object.class, Object.class, List.class, List.class, Boolean.TYPE, Boolean.TYPE, Object.class);
                        if (handleLaunchActivity != null) {
                            Log.d(TAG, "Found handleLaunchActivity method");
                            handleLaunchActivity.setAccessible(true);
                        }
                    } catch (Exception e) {
                        Log.w(TAG, "Could not hook handleLaunchActivity: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, "Could not hook ActivityThread: " + e2.getMessage());
        }
    }

    private static void replaceControllerInstance(Class<?> controllerClass, Object proxy, String controllerName) {
        try {
            Object activityThread = BlackBoxCore.mainThread();
            if (activityThread != null) {
                Field[] fields = activityThread.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType().equals(controllerClass)) {
                        field.setAccessible(true);
                        Object currentInstance = field.get(activityThread);
                        if (currentInstance != null) {
                            field.set(activityThread, proxy);
                            Log.d(TAG, "Successfully replaced " + controllerName + " instance");
                            return;
                        }
                    }
                }
                Log.w(TAG, "Could not find " + controllerName + " field, trying to create new instance");
                try {
                    Constructor<?>[] constructors = controllerClass.getDeclaredConstructors();
                    if (constructors.length > 0) {
                        constructors[0].setAccessible(true);
                        Object newInstance = constructors[0].newInstance(new Object[0]);
                        if (newInstance != null) {
                            Log.d(TAG, "Created new " + controllerName + " instance");
                        }
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Could not create new " + controllerName + " instance: " + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, "Could not replace " + controllerName + " instance: " + e2.getMessage());
        }
    }

    private static void ensureAllActivitiesHaveContext() {
        try {
            Object activityThread = BlackBoxCore.mainThread();
            if (activityThread != null) {
                try {
                    Field[] fields = activityThread.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getType().getName().contains("ArrayMap") || field.getType().getName().contains("HashMap")) {
                            field.setAccessible(true);
                            Object activityRecords = field.get(activityThread);
                            if (activityRecords != null) {
                                try {
                                    Method valuesMethod = activityRecords.getClass().getMethod("values", new Class[0]);
                                    Object values = valuesMethod.invoke(activityRecords, new Object[0]);
                                    if (values instanceof Collection) {
                                        for (Object record : (Collection) values) {
                                            if (record != null) {
                                                try {
                                                    Field activityField = record.getClass().getDeclaredField("activity");
                                                    activityField.setAccessible(true);
                                                    Object activity = activityField.get(record);
                                                    if (activity instanceof Activity) {
                                                        BlackBoxCore.ensureActivityContext((Activity) activity);
                                                    }
                                                } catch (Exception e) {
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e2) {
                                    Log.w(TAG, "Could not iterate through activity records: " + e2.getMessage());
                                }
                            }
                        }
                    }
                } catch (Exception e3) {
                    Log.w(TAG, "Could not access activity records: " + e3.getMessage());
                }
            }
        } catch (Exception e4) {
            Log.w(TAG, "Error ensuring all activities have context: " + e4.getMessage());
        }
    }
}
