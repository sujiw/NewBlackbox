package top.niunaijun.blackbox.fake.service;

import android.app.AppOpsManager;
import android.os.IBinder;
import black.android.app.BRAppOpsManager;
import black.android.os.BRServiceManager;
import black.com.android.internal.app.BRIAppOpsServiceStub;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.MethodParameterUtils;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy.class */
public class IAppOpsManagerProxy extends BinderInvocationStub {
    public IAppOpsManagerProxy() {
        super(BRServiceManager.get().getService("appops"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        IBinder call = BRServiceManager.get().getService("appops");
        return BRIAppOpsServiceStub.get().asInterface(call);
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        if (BRAppOpsManager.get(null)._check_mService() != null) {
            AppOpsManager appOpsManager = (AppOpsManager) BlackBoxCore.getContext().getSystemService("appops");
            try {
                BRAppOpsManager.get(appOpsManager)._set_mService(getProxyInvocation());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        replaceSystemService("appops");
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            MethodParameterUtils.replaceFirstAppPkg(args);
            MethodParameterUtils.replaceLastUid(args);
            return super.invoke(proxy, method, args);
        } catch (SecurityException e) {
            Slog.w(TAG, "AppOps invoke: SecurityException caught, allowing operation", e);
            return 0;
        } catch (Exception e2) {
            Slog.e(TAG, "AppOps invoke: Error in method " + method.getName(), e2);
            return super.invoke(proxy, method, args);
        }
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$NoteProxyOperation.class */
    @ProxyMethod("noteProxyOperation")
    public static class NoteProxyOperation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$CheckPackage.class */
    @ProxyMethod("checkPackage")
    public static class CheckPackage extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$CheckOperation.class */
    @ProxyMethod("checkOperation")
    public static class CheckOperation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceLastUid(args);
            try {
                int op = ((Integer) args[0]).intValue();
                String publicName = IAppOpsManagerProxy.getOpPublicName(op);
                if (publicName != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(publicName)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps CheckOperation: Allowing operation: " + publicName);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$NoteOperation.class */
    @ProxyMethod("noteOperation")
    public static class NoteOperation extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                int op = ((Integer) args[0]).intValue();
                String publicName = IAppOpsManagerProxy.getOpPublicName(op);
                if (publicName != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(publicName)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps NoteOperation: Allowing operation: " + publicName);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$CheckOpNoThrow.class */
    @ProxyMethod("checkOpNoThrow")
    public static class CheckOpNoThrow extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                String opStr = (String) args[0];
                if (opStr != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(opStr)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps CheckOpNoThrow: Allowing operation: " + opStr);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$StartOp.class */
    @ProxyMethod("startOp")
    public static class StartOp extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                int op = ((Integer) args[0]).intValue();
                String name = IAppOpsManagerProxy.getOpPublicName(op);
                if (name != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(name)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps StartOp: Allowing operation: " + name);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$StartOpNoThrow.class */
    @ProxyMethod("startOpNoThrow")
    public static class StartOpNoThrow extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                String opStr = (String) args[0];
                if (opStr != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(opStr)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps StartOpNoThrow: Allowing operation: " + opStr);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$FinishOp.class */
    @ProxyMethod("finishOp")
    public static class FinishOp extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                int op = ((Integer) args[0]).intValue();
                String name = IAppOpsManagerProxy.getOpPublicName(op);
                if (name != null && IAppOpsManagerProxy.isMediaStorageOrAudioOp(name)) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps FinishOp: Finishing operation: " + name);
                }
                return null;
            } catch (Throwable th) {
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$NoteOp.class */
    @ProxyMethod("noteOp")
    public static class NoteOp extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                int op = ((Integer) args[0]).intValue();
                String name = IAppOpsManagerProxy.getOpPublicName(op);
                if (name != null && (name.contains("RECORD_AUDIO") || name.contains("AUDIO") || name.contains("MICROPHONE"))) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps NoteOp: Allowing RECORD_AUDIO operation: " + name);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAppOpsManagerProxy$NoteOpNoThrow.class */
    @ProxyMethod("noteOpNoThrow")
    public static class NoteOpNoThrow extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                int op = ((Integer) args[0]).intValue();
                String name = IAppOpsManagerProxy.getOpPublicName(op);
                if (name != null && (name.contains("RECORD_AUDIO") || name.contains("AUDIO") || name.contains("MICROPHONE"))) {
                    Slog.d(ClassInvocationStub.TAG, "AppOps NoteOpNoThrow: Allowing RECORD_AUDIO operation: " + name);
                    return 0;
                }
            } catch (Throwable th) {
            }
            return method.invoke(who, args);
        }
    }

    private static boolean isMediaStorageOrAudioOp(String opPublicNameOrStr) {
        if (opPublicNameOrStr == null) {
            return false;
        }
        String n = opPublicNameOrStr.toUpperCase();
        return n.contains("READ_MEDIA") || n.contains("READ_EXTERNAL_STORAGE") || n.contains("RECORD_AUDIO") || n.contains("CAPTURE_AUDIO_OUTPUT") || n.contains("MODIFY_AUDIO_SETTINGS") || n.contains("AUDIO") || n.contains("MICROPHONE") || n.contains("FOREGROUND_SERVICE") || n.contains("SYSTEM_ALERT_WINDOW") || n.contains("WRITE_SETTINGS") || n.contains("ACCESS_FINE_LOCATION") || n.contains("ACCESS_COARSE_LOCATION") || n.contains("CAMERA") || n.contains("BODY_SENSORS") || n.contains("BLUETOOTH_SCAN") || n.contains("BLUETOOTH_CONNECT") || n.contains("BLUETOOTH_ADVERTISE") || n.contains("NEARBY_WIFI_DEVICES") || n.contains("POST_NOTIFICATIONS");
    }

    private static String getOpPublicName(int op) {
        try {
            Method m = AppOpsManager.class.getMethod("opToPublicName", Integer.TYPE);
            Object name = m.invoke(null, Integer.valueOf(op));
            if (name != null) {
                return name.toString();
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }
}
