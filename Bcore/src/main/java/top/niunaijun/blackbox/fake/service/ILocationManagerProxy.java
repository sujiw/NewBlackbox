package top.niunaijun.blackbox.fake.service;

import android.os.IInterface;
import android.util.Log;
import black.android.location.BRILocationManagerStub;
import black.android.location.provider.BRProviderProperties;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.GmsCore;
import top.niunaijun.blackbox.fake.frameworks.BLocationManager;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy.class */
public class ILocationManagerProxy extends BinderInvocationStub {
    public static final String TAG = "ILocationManagerProxy";

    public ILocationManagerProxy() {
        super(BRServiceManager.get().getService("location"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRILocationManagerStub.get().asInterface(BRServiceManager.get().getService("location"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("location");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceFirstAppPkg(args);
        String packageName = BActivityThread.getAppPackageName();
        if (packageName != null && packageName.equals(GmsCore.GMS_PKG) && (method.getName().equals("getLastLocation") || method.getName().equals("getLastKnownLocation") || method.getName().equals("requestLocationUpdates"))) {
            Log.w(TAG, "Blocking location request from Google Play Services to prevent crash");
            return null;
        }
        return super.invoke(proxy, method, args);
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$RegisterGnssStatusCallback.class */
    @ProxyMethod("registerGnssStatusCallback")
    public static class RegisterGnssStatusCallback extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return true;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$GetLastLocation.class */
    @ProxyMethod("getLastLocation")
    public static class GetLastLocation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            if (BLocationManager.isFakeLocationEnable()) {
                return BLocationManager.get().getLocation(BActivityThread.getUserId(), BActivityThread.getAppPackageName()).convert2SystemLocation();
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if (e.getCause() instanceof SecurityException) {
                    Log.w(ILocationManagerProxy.TAG, "Location permission denied, returning null for getLastLocation");
                    return null;
                }
                throw e;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$GetLastKnownLocation.class */
    @ProxyMethod("getLastKnownLocation")
    public static class GetLastKnownLocation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            if (BLocationManager.isFakeLocationEnable()) {
                return BLocationManager.get().getLocation(BActivityThread.getUserId(), BActivityThread.getAppPackageName()).convert2SystemLocation();
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if (e.getCause() instanceof SecurityException) {
                    Log.w(ILocationManagerProxy.TAG, "Location permission denied, returning null for getLastKnownLocation");
                    return null;
                }
                throw e;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$RequestLocationUpdates.class */
    @ProxyMethod("requestLocationUpdates")
    public static class RequestLocationUpdates extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            if (BLocationManager.isFakeLocationEnable() && (args[1] instanceof IInterface)) {
                IInterface listener = (IInterface) args[1];
                BLocationManager.get().requestLocationUpdates(listener.asBinder());
                return 0;
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if (e.getCause() instanceof SecurityException) {
                    Log.w(ILocationManagerProxy.TAG, "Location permission denied for requestLocationUpdates, returning 0");
                    return 0;
                }
                throw e;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$RemoveUpdates.class */
    @ProxyMethod("removeUpdates")
    public static class RemoveUpdates extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args[0] instanceof IInterface) {
                IInterface listener = (IInterface) args[0];
                BLocationManager.get().removeUpdates(listener.asBinder());
                return 0;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$GetProviderProperties.class */
    @ProxyMethod("getProviderProperties")
    public static class GetProviderProperties extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object providerProperties = method.invoke(who, args);
            if (BLocationManager.isFakeLocationEnable()) {
                BRProviderProperties.get(providerProperties)._set_mHasNetworkRequirement(false);
                if (BLocationManager.get().getCell(BActivityThread.getUserId(), BActivityThread.getAppPackageName()) == null) {
                    BRProviderProperties.get(providerProperties)._set_mHasCellRequirement(false);
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$RemoveGpsStatusListener.class */
    @ProxyMethod("removeGpsStatusListener")
    public static class RemoveGpsStatusListener extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$GetBestProvider.class */
    @ProxyMethod("getBestProvider")
    public static class GetBestProvider extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (BLocationManager.isFakeLocationEnable()) {
                return "gps";
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$GetAllProviders.class */
    @ProxyMethod("getAllProviders")
    public static class GetAllProviders extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Arrays.asList("gps", "network");
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$isProviderEnabledForUser.class */
    @ProxyMethod("isProviderEnabledForUser")
    public static class isProviderEnabledForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String provider = (String) args[0];
            return Boolean.valueOf(Objects.equals(provider, "gps"));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ILocationManagerProxy$setExtraLocationControllerPackageEnabled.class */
    @ProxyMethod("setExtraLocationControllerPackageEnabled")
    public static class setExtraLocationControllerPackageEnabled extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }
}
