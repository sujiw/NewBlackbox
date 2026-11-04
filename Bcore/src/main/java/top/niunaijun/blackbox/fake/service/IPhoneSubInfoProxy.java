package top.niunaijun.blackbox.fake.service;

import black.android.telephony.BRTelephonyManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IPhoneSubInfoProxy.class */
public class IPhoneSubInfoProxy extends ClassInvocationStub {
    public static final String TAG = "IPhoneSubInfoProxy";

    public IPhoneSubInfoProxy() {
        if (BRTelephonyManager.get()._check_sServiceHandleCacheEnabled() != null) {
            BRTelephonyManager.get()._set_sServiceHandleCacheEnabled(true);
        }
        if (BRTelephonyManager.get()._check_getSubscriberInfoService() != null) {
            BRTelephonyManager.get().getSubscriberInfoService();
        }
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRTelephonyManager.get().sIPhoneSubInfo();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        BRTelephonyManager.get()._set_sIPhoneSubInfo(proxyInvocation);
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceFirstAppPkg(args);
        return super.invoke(proxy, method, args);
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IPhoneSubInfoProxy$getLine1NumberForSubscriber.class */
    @ProxyMethod("getLine1NumberForSubscriber")
    public static class getLine1NumberForSubscriber extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return DeviceInfoManager.phoneNumber();
        }
    }

    /**
     * 档案里没给就返回 null：不能回落到真实调用 —— 虚拟包名去查系统 provider 会抛
     * SecurityException，被代理包成 UndeclaredThrowableException 抛给应用。
     */
    private static Object realOr(Object who, Method method, Object[] args, String value) {
        return value != null && !value.isEmpty() ? value : null;
    }

    @ProxyMethod("getDeviceId")
    public static class getDeviceId extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.imei());
        }
    }

    @ProxyMethod("getDeviceIdForSubscriber")
    public static class getDeviceIdForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.imei());
        }
    }

    @ProxyMethod("getImeiForSubscriber")
    public static class getImeiForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.imei());
        }
    }

    @ProxyMethod("getMeidForSubscriber")
    public static class getMeidForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return DeviceInfoManager.meid();
        }
    }

    @ProxyMethod("getSubscriberId")
    public static class getSubscriberId extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.imsi());
        }
    }

    @ProxyMethod("getSubscriberIdForSubscriber")
    public static class getSubscriberIdForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.imsi());
        }
    }

    @ProxyMethod("getIccSerialNumber")
    public static class getIccSerialNumber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.iccid());
        }
    }

    @ProxyMethod("getIccSerialNumberForSubscriber")
    public static class getIccSerialNumberForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.iccid());
        }
    }
}
