package top.niunaijun.blackbox.fake.service;

import android.os.IBinder;
import android.util.Log;
import black.android.os.BRServiceManager;
import black.com.android.internal.telephony.BRITelephonyStub;
import java.lang.reflect.Method;
import java.util.List;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.entity.location.BCell;
import top.niunaijun.blackbox.fake.frameworks.BLocationManager;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Md5Utils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy.class */
public class ITelephonyManagerProxy extends BinderInvocationStub {
    public static final String TAG = "ITelephonyManagerProxy";

    public ITelephonyManagerProxy() {
        super(BRServiceManager.get().getService("phone"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        IBinder telephony = BRServiceManager.get().getService("phone");
        return BRITelephonyStub.get().asInterface(telephony);
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("phone");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /** 档案里没给就退回原来的 hash 值，保证 guest 永远拿到非空设备号。 */
    static String idOrFallback(String value) {
        if (value == null) {
            // 档案没给（老配置）：保持原来的 hash 兜底
            return Md5Utils.md5(BlackBoxCore.getHostPkg());
        }
        // 档案显式给了空串 = 该设备确实没有这个标识（如 wifi-only 平板）
        return value.isEmpty() ? null : value;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetDeviceId.class */
    @ProxyMethod("getDeviceId")
    public static class GetDeviceId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return idOrFallback(DeviceInfoManager.imei());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$getImeiForSlot.class */
    @ProxyMethod("getImeiForSlot")
    public static class getImeiForSlot extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            int slot = args != null && args.length > 0 && args[0] instanceof Integer
                    ? (Integer) args[0] : 0;
            return idOrFallback(DeviceInfoManager.imeiForSlot(slot));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetMeidForSlot.class */
    @ProxyMethod("getMeidForSlot")
    public static class GetMeidForSlot extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String meid = DeviceInfoManager.meid();
            return idOrFallback(meid != null ? meid : DeviceInfoManager.imei());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$IsUserDataEnabled.class */
    @ProxyMethod("isUserDataEnabled")
    public static class IsUserDataEnabled extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return true;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$getLine1NumberForDisplay.class */
    @ProxyMethod("getLine1NumberForDisplay")
    public static class getLine1NumberForDisplay extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return DeviceInfoManager.phoneNumber();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetSubscriberId.class */
    @ProxyMethod("getSubscriberId")
    public static class GetSubscriberId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return idOrFallback(DeviceInfoManager.imsi());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetDeviceIdWithFeature.class */
    @ProxyMethod("getDeviceIdWithFeature")
    public static class GetDeviceIdWithFeature extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return idOrFallback(DeviceInfoManager.imei());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetCellLocation.class */
    @ProxyMethod("getCellLocation")
    public static class GetCellLocation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Log.d(ITelephonyManagerProxy.TAG, "getCellLocation");
            if (BLocationManager.isFakeLocationEnable()) {
                BCell cell = BLocationManager.get().getCell(BActivityThread.getUserId(), BActivityThread.getAppPackageName());
                if (cell != null) {
                    return null;
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetAllCellInfo.class */
    @ProxyMethod("getAllCellInfo")
    public static class GetAllCellInfo extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (BLocationManager.isFakeLocationEnable()) {
                List<BCell> cell = BLocationManager.get().getAllCell(BActivityThread.getUserId(), BActivityThread.getAppPackageName());
                return cell;
            }
            try {
                return method.invoke(who, args);
            } catch (Throwable th) {
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetNetworkOperator.class */
    @ProxyMethod("getNetworkOperator")
    public static class GetNetworkOperator extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String operator = DeviceInfoManager.networkOperator();
            return operator != null ? operator : method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetNetworkTypeForSubscriber.class */
    @ProxyMethod("getNetworkTypeForSubscriber")
    public static class GetNetworkTypeForSubscriber extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(who, args);
            } catch (Throwable th) {
                return 0;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ITelephonyManagerProxy$GetNeighboringCellInfo.class */
    @ProxyMethod("getNeighboringCellInfo")
    public static class GetNeighboringCellInfo extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Log.d(ITelephonyManagerProxy.TAG, "getNeighboringCellInfo");
            if (BLocationManager.isFakeLocationEnable()) {
                BLocationManager.get().getNeighboringCell(BActivityThread.getUserId(), BActivityThread.getAppPackageName());
                return null;
            }
            return method.invoke(who, args);
        }
    }

    // ==================== 档案驱动的运营商 / SIM 信息 ====================

    private static Object realOr(Object who, Method method, Object[] args, String value) throws Throwable {
        return value != null ? value : method.invoke(who, args);
    }

    @ProxyMethod("getNetworkOperatorForSubscription")
    public static class GetNetworkOperatorForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.networkOperator());
        }
    }

    @ProxyMethod("getNetworkOperatorName")
    public static class GetNetworkOperatorName extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String name = DeviceInfoManager.carrierName();
            return name != null ? name : (args != null && args.length == 0 ? "" : method.invoke(who, args));
        }
    }

    @ProxyMethod("getNetworkOperatorNameForSubscription")
    public static class GetNetworkOperatorNameForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.carrierName());
        }
    }

    @ProxyMethod("getSimOperator")
    public static class GetSimOperator extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.networkOperator());
        }
    }

    @ProxyMethod("getSimOperatorForSubscription")
    public static class GetSimOperatorForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.networkOperator());
        }
    }

    @ProxyMethod("getSimOperatorName")
    public static class GetSimOperatorName extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.simOperatorName());
        }
    }

    @ProxyMethod("getSimOperatorNameForSubscription")
    public static class GetSimOperatorNameForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.simOperatorName());
        }
    }

    @ProxyMethod("getNetworkCountryIsoForPhone")
    public static class GetNetworkCountryIsoForPhone extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.countryIso());
        }
    }

    @ProxyMethod("getNetworkCountryIsoForSubscription")
    public static class GetNetworkCountryIsoForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.countryIso());
        }
    }

    @ProxyMethod("getSimCountryIsoForPhone")
    public static class GetSimCountryIsoForPhone extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.countryIso());
        }
    }

    @ProxyMethod("getSimCountryIsoForSubscription")
    public static class GetSimCountryIsoForSubscription extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.countryIso());
        }
    }

    @ProxyMethod("getSimStateForSlot")
    public static class GetSimStateForSlot extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (!DeviceInfoManager.hasProfile()) {
                return method.invoke(who, args);
            }
            // 1 = ABSENT（无 SIM，例如 wifi-only 档案 mcc=0），5 = READY
            return DeviceInfoManager.getInt(0, "mcc") > 0 ? 5 : 1;
        }
    }

    @ProxyMethod("getSubscriberIdForSubscriber")
    public static class GetSubscriberIdForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, idOrFallback(DeviceInfoManager.imsi()));
        }
    }

    @ProxyMethod("getLine1NumberForSubscriber")
    public static class GetLine1NumberForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return DeviceInfoManager.phoneNumber();
        }
    }

    @ProxyMethod("getIccSerialNumber")
    public static class GetIccSerialNumber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.iccid());
        }
    }

    @ProxyMethod("getIccSerialNumberForSubscriber")
    public static class GetIccSerialNumberForSubscriber extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return realOr(who, method, args, DeviceInfoManager.iccid());
        }
    }

    /* 网络类型：emu 的 connectionType(wifi/4g/3g/2g) -> TelephonyManager.NETWORK_TYPE_*。 */
    private static int networkTypeOf() {
        String type = DeviceInfoManager.getString("connectionType", "networkType");
        if (type == null) {
            return -1;
        }
        switch (type.toLowerCase(java.util.Locale.US)) {
            case "4g":
            case "lte":
                return 13;
            case "3g":
            case "hspa":
                return 10;
            case "2g":
            case "edge":
                return 2;
            case "wifi":
                return 0;
            default:
                return -1;
        }
    }

    private static Object networkTypeOrReal(Object who, Method method, Object[] args) throws Throwable {
        int type = networkTypeOf();
        return type >= 0 ? type : method.invoke(who, args);
    }

    @ProxyMethod("getNetworkType")
    public static class GetNetworkType extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return networkTypeOrReal(who, method, args);
        }
    }

    @ProxyMethod("getDataNetworkType")
    public static class GetDataNetworkType extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return networkTypeOrReal(who, method, args);
        }
    }

    @ProxyMethod("getVoiceNetworkType")
    public static class GetVoiceNetworkType extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return networkTypeOrReal(who, method, args);
        }
    }
}
