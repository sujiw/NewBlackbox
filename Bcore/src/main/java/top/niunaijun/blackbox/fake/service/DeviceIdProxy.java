package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy.class */
public class DeviceIdProxy extends ClassInvocationStub {
    public static final String TAG = "DeviceIdProxy";

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return null;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$GetDeviceId.class */
    @ProxyMethod("getDeviceId")
    public static class GetDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                if (who == null) {
                    Slog.w(DeviceIdProxy.TAG, "GetDeviceId called on null object, returning default device ID");
                    return "default_device_id";
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "GetDeviceId error, returning default device ID: " + e.getMessage());
                return "default_device_id";
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$SetDeviceId.class */
    @ProxyMethod("setDeviceId")
    public static class SetDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                if (who == null) {
                    Slog.w(DeviceIdProxy.TAG, "SetDeviceId called on null object, ignoring");
                    return null;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "SetDeviceId error, ignoring: " + e.getMessage());
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$IsValidDeviceId.class */
    @ProxyMethod("isValidDeviceId")
    public static class IsValidDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                if (who == null) {
                    Slog.w(DeviceIdProxy.TAG, "IsValidDeviceId called on null object, returning true");
                    return true;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "IsValidDeviceId error, returning true: " + e.getMessage());
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$GenerateDeviceId.class */
    @ProxyMethod("generateDeviceId")
    public static class GenerateDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "GenerateDeviceId error, returning default device ID: " + e.getMessage());
                return "generated_device_id_" + System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$StoreDeviceId.class */
    @ProxyMethod("storeDeviceId")
    public static class StoreDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                if (who == null) {
                    Slog.w(DeviceIdProxy.TAG, "StoreDeviceId called on null object, ignoring");
                    return null;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "StoreDeviceId error, ignoring: " + e.getMessage());
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/DeviceIdProxy$RetrieveDeviceId.class */
    @ProxyMethod("retrieveDeviceId")
    public static class RetrieveDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                if (who == null) {
                    Slog.w(DeviceIdProxy.TAG, "RetrieveDeviceId called on null object, returning default device ID");
                    return "retrieved_device_id";
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(DeviceIdProxy.TAG, "RetrieveDeviceId error, returning default device ID: " + e.getMessage());
                return "retrieved_device_id";
            }
        }
    }
}
