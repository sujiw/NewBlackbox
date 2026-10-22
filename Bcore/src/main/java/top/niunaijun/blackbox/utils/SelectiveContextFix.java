package top.niunaijun.blackbox.utils;

import android.app.Activity;
import android.content.res.Configuration;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/SelectiveContextFix.class */
public class SelectiveContextFix {
    private static final String TAG = "SelectiveContextFix";

    public static void installSelectiveFix() {
        try {
            Slog.d(TAG, "Installing selective context fix...");
            patchClientTransactionListenerControllerOnly();
            Slog.d(TAG, "Selective context fix installed successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Failed to install selective context fix: " + e.getMessage(), e);
        }
    }

    private static void patchClientTransactionListenerControllerOnly() {
        try {
            Class<?> controllerClass = Class.forName("android.app.servertransaction.ClientTransactionListenerController");
            if (controllerClass != null) {
                Slog.d(TAG, "Found ClientTransactionListenerController class");
                Method originalMethod = controllerClass.getDeclaredMethod("onContextConfigurationPreChanged", Configuration.class);
                originalMethod.setAccessible(true);
                Method customMethod = SelectiveContextFix.class.getDeclaredMethod("safeOnContextConfigurationPreChanged", Object.class, Configuration.class);
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

    public static void safeOnContextConfigurationPreChanged(Object controller, Configuration config) {
        try {
            if (controller == null) {
                Slog.w(TAG, "ClientTransactionListenerController is null, skipping");
                return;
            }
            try {
                Class<?> controllerClass = controller.getClass();
                Method originalMethod = controllerClass.getDeclaredMethod("onContextConfigurationPreChanged", Configuration.class);
                originalMethod.setAccessible(true);
                originalMethod.invoke(controller, config);
                Slog.d(TAG, "Successfully called original onContextConfigurationPreChanged");
            } catch (Exception e) {
                if (e.getCause() instanceof NullPointerException) {
                    String message = e.getCause().getMessage();
                    if (message != null && message.contains("getResources") && message.contains("null object reference")) {
                        Slog.w(TAG, "Caught null context crash in onContextConfigurationPreChanged, attempting recovery");
                        try {
                            ensureActivityContextForController(controller);
                            Class<?> controllerClass2 = controller.getClass();
                            Method originalMethod2 = controllerClass2.getDeclaredMethod("onContextConfigurationPreChanged", Configuration.class);
                            originalMethod2.setAccessible(true);
                            originalMethod2.invoke(controller, config);
                            Slog.d(TAG, "Successfully called original onContextConfigurationPreChanged after recovery");
                        } catch (Exception recoveryException) {
                            Slog.w(TAG, "Recovery attempt failed: " + recoveryException.getMessage());
                        }
                    } else {
                        throw e;
                    }
                } else {
                    throw e;
                }
            }
        } catch (Exception e2) {
            Slog.w(TAG, "Error in safe onContextConfigurationPreChanged: " + e2.getMessage());
        }
    }

    private static void ensureActivityContextForController(Object controller) {
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
            Slog.w(TAG, "Error ensuring activity context for controller: " + e4.getMessage());
        }
    }
}
