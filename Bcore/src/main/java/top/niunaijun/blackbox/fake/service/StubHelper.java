package top.niunaijun.blackbox.fake.service;

import android.os.IBinder;
import android.util.Log;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/StubHelper.class */
public class StubHelper {
    private static final String TAG = "StubHelper";

    public static Object getServiceInterface(String serviceName, String stubClassName, String realStubClassName) {
        try {
            Class<?> stubClass = Class.forName(stubClassName);
            Method getMethod = stubClass.getMethod("get", new Class[0]);
            Object stub = getMethod.invoke(null, new Object[0]);
            Method asInterfaceMethod = null;
            try {
                asInterfaceMethod = stubClass.getMethod("asInterface", IBinder.class);
            } catch (NoSuchMethodException e) {
                try {
                    asInterfaceMethod = stubClass.getMethod("asInterface", Object.class);
                } catch (NoSuchMethodException e2) {
                    Method[] methods = stubClass.getMethods();
                    for (Method method : methods) {
                        if (method.getName().equals("asInterface") && method.getParameterCount() == 1) {
                            asInterfaceMethod = method;
                            break;
                        }
                    }
                }
            }
            if (asInterfaceMethod != null) {
                IBinder binder = BRServiceManager.get().getService(serviceName);
                return asInterfaceMethod.invoke(stub, binder);
            }
            Log.w(TAG, "Could not find asInterface method in " + stubClassName);
            try {
                IBinder binder2 = BRServiceManager.get().getService(serviceName);
                Method asInterfaceMethod2 = Class.forName(realStubClassName).getMethod("asInterface", IBinder.class);
                return asInterfaceMethod2.invoke(null, binder2);
            } catch (Exception ex) {
                Log.e(TAG, "Failed to get service interface for: " + serviceName, ex);
                return null;
            }
        } catch (Exception e3) {
            Log.w(TAG, "Failed to use generated stub class: " + stubClassName, e3);
        }
        return null;
    }

    public static Object getServiceInterface(String serviceName, String stubClassName, String realStubClassName, IBinder binder) {
        IBinder service;
        try {
            Class<?> stubClass = Class.forName(stubClassName);
            Method getMethod = stubClass.getMethod("get", new Class[0]);
            Object stub = getMethod.invoke(null, new Object[0]);
            Method asInterfaceMethod = null;
            try {
                asInterfaceMethod = stubClass.getMethod("asInterface", IBinder.class);
            } catch (NoSuchMethodException e) {
                try {
                    asInterfaceMethod = stubClass.getMethod("asInterface", Object.class);
                } catch (NoSuchMethodException e2) {
                    Method[] methods = stubClass.getMethods();
                    for (Method method : methods) {
                        if (method.getName().equals("asInterface") && method.getParameterCount() == 1) {
                            asInterfaceMethod = method;
                            break;
                        }
                    }
                }
            }
            if (asInterfaceMethod != null) {
                IBinder serviceBinder = binder != null ? binder : BRServiceManager.get().getService(serviceName);
                return asInterfaceMethod.invoke(stub, serviceBinder);
            }
            Log.w(TAG, "Could not find asInterface method in " + stubClassName);
            if (binder != null) {
                service = binder;
            } else {
                try {
                    service = BRServiceManager.get().getService(serviceName);
                } catch (Exception ex) {
                    Log.e(TAG, "Failed to get service interface for: " + serviceName, ex);
                    return null;
                }
            }
            IBinder serviceBinder2 = service;
            Method asInterfaceMethod2 = Class.forName(realStubClassName).getMethod("asInterface", IBinder.class);
            return asInterfaceMethod2.invoke(null, serviceBinder2);
        } catch (Exception e3) {
            Log.w(TAG, "Failed to use generated stub class: " + stubClassName, e3);
        }
        return null;
    }
}
