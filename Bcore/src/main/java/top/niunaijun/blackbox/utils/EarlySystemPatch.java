package top.niunaijun.blackbox.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/EarlySystemPatch.class */
public class EarlySystemPatch {
    private static final String TAG = "EarlySystemPatch";

    public static void installAllPatches() {
        try {
            Slog.d(TAG, "Installing early system patches...");
            patchContextWrapperGetResources();
            patchClientTransactionListenerController();
            patchConfigurationController();
            Slog.d(TAG, "Early system patches installed successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Failed to install early system patches: " + e.getMessage(), e);
        }
    }

    private static void patchContextWrapperGetResources() {
        try {
            Method getResourcesMethod = ContextWrapper.class.getDeclaredMethod("getResources", new Class[0]);
            getResourcesMethod.setAccessible(true);
            Method customMethod = EarlySystemPatch.class.getDeclaredMethod("safeGetResources", ContextWrapper.class);
            customMethod.setAccessible(true);
            try {
                Field methodAccessorField = Method.class.getDeclaredField("methodAccessor");
                methodAccessorField.setAccessible(true);
                Object customAccessor = createCustomMethodAccessor(getResourcesMethod, customMethod);
                if (customAccessor != null) {
                    methodAccessorField.set(getResourcesMethod, customAccessor);
                    Slog.d(TAG, "Successfully patched ContextWrapper.getResources() method");
                }
            } catch (Exception e) {
                Slog.w(TAG, "Could not patch ContextWrapper.getResources() method: " + e.getMessage());
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Could not find ContextWrapper.getResources() method: " + e2.getMessage());
        }
    }

    private static Object createCustomMethodAccessor(Method originalMethod, Method customMethod) {
        try {
            Class<?> methodAccessorClass = Class.forName("java.lang.reflect.MethodAccessor");
            if (methodAccessorClass != null) {
                return Proxy.newProxyInstance(methodAccessorClass.getClassLoader(), new Class[]{methodAccessorClass}, (proxy, method, args) -> {
                    if ("invoke".equals(method.getName())) {
                        return customMethod.invoke(null, args);
                    }
                    return method.invoke(proxy, args);
                });
            }
            return null;
        } catch (Exception e) {
            Slog.w(TAG, "Could not create custom method accessor: " + e.getMessage());
            return null;
        }
    }

    public static Resources safeGetResources(ContextWrapper contextWrapper) {
        try {
            Context baseContext = contextWrapper.getBaseContext();
            if (baseContext != null) {
                return baseContext.getResources();
            }
        } catch (Exception e) {
            Slog.w(TAG, "Error getting resources from base context: " + e.getMessage());
        }
        try {
            Context hostContext = BlackBoxCore.getContext();
            if (hostContext != null) {
                return hostContext.getResources();
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Error getting host context resources: " + e2.getMessage());
        }
        Slog.w(TAG, "All resource fallbacks failed, returning null");
        return null;
    }

    private static void patchClientTransactionListenerController() {
        try {
            Class<?> controllerClass = Class.forName("android.app.servertransaction.ClientTransactionListenerController");
            if (controllerClass != null) {
                Slog.d(TAG, "Found ClientTransactionListenerController class");
                Method originalMethod = controllerClass.getDeclaredMethod("onContextConfigurationPreChanged", Configuration.class);
                originalMethod.setAccessible(true);
                Method customMethod = EarlySystemPatch.class.getDeclaredMethod("safeOnContextConfigurationPreChanged", Object.class, Configuration.class);
                customMethod.setAccessible(true);
                try {
                    Field methodAccessorField = Method.class.getDeclaredField("methodAccessor");
                    methodAccessorField.setAccessible(true);
                    Object customAccessor = createCustomMethodAccessor(originalMethod, customMethod);
                    if (customAccessor != null) {
                        methodAccessorField.set(originalMethod, customAccessor);
                        Slog.d(TAG, "Successfully patched ClientTransactionListenerController.onContextConfigurationPreChanged");
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Could not patch ClientTransactionListenerController method: " + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Could not find ClientTransactionListenerController class: " + e2.getMessage());
        }
    }

    public static void safeOnContextConfigurationPreChanged(Object controller, Configuration config) {
        try {
            ensureAllActivitiesHaveContext();
            try {
                Class<?> controllerClass = controller.getClass();
                Method originalMethod = controllerClass.getDeclaredMethod("onContextConfigurationPreChanged", Configuration.class);
                originalMethod.setAccessible(true);
                originalMethod.invoke(controller, config);
            } catch (Exception e) {
                Slog.w(TAG, "Could not call original onContextConfigurationPreChanged: " + e.getMessage());
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Error in safe onContextConfigurationPreChanged: " + e2.getMessage());
        }
    }

    private static void patchConfigurationController() {
        try {
            Class<?> controllerClass = Class.forName("android.app.ConfigurationController");
            if (controllerClass != null) {
                Slog.d(TAG, "Found ConfigurationController class");
                Method originalMethod = controllerClass.getDeclaredMethod("handleConfigurationChanged", Configuration.class);
                originalMethod.setAccessible(true);
                Method customMethod = EarlySystemPatch.class.getDeclaredMethod("safeHandleConfigurationChanged", Object.class, Configuration.class);
                customMethod.setAccessible(true);
                try {
                    Field methodAccessorField = Method.class.getDeclaredField("methodAccessor");
                    methodAccessorField.setAccessible(true);
                    Object customAccessor = createCustomMethodAccessor(originalMethod, customMethod);
                    if (customAccessor != null) {
                        methodAccessorField.set(originalMethod, customAccessor);
                        Slog.d(TAG, "Successfully patched ConfigurationController.handleConfigurationChanged");
                    }
                } catch (Exception e) {
                    Slog.w(TAG, "Could not patch ConfigurationController method: " + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Could not find ConfigurationController class: " + e2.getMessage());
        }
    }

    public static void safeHandleConfigurationChanged(Object controller, Configuration config) {
        try {
            ensureAllActivitiesHaveContext();
            try {
                Class<?> controllerClass = controller.getClass();
                Method originalMethod = controllerClass.getDeclaredMethod("handleConfigurationChanged", Configuration.class);
                originalMethod.setAccessible(true);
                originalMethod.invoke(controller, config);
            } catch (Exception e) {
                Slog.w(TAG, "Could not call original handleConfigurationChanged: " + e.getMessage());
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Error in safe handleConfigurationChanged: " + e2.getMessage());
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
                                    Slog.w(TAG, "Could not iterate through activity records: " + e2.getMessage());
                                }
                            }
                        }
                    }
                } catch (Exception e3) {
                    Slog.w(TAG, "Could not access activity records: " + e3.getMessage());
                }
            }
        } catch (Exception e4) {
            Slog.w(TAG, "Error ensuring all activities have context: " + e4.getMessage());
        }
    }
}
