package top.niunaijun.blackbox.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 每个应用的 Google Play 安装来源（install referrer）覆盖。
 *
 * <p>Play 安装来源由 {@code com.android.vending} 的
 * {@code IGetInstallReferrerService} 通过 bindService 提供；这里和
 * {@link GaidManager} 一样，在 {@code IActivityManagerProxy#bindService} 里
 * 换成假 binder，按 AIDL 事务号回一个 Bundle，避免依赖混淆后的 GMS 类。</p>
 */
public final class ReferrerManager {

    private static final String TAG = "ReferrerManager";

    /** Must match the constant used by the host UI. */
    public static final String CONFIG_FILE = "app_referrer.json";

    public static final String ACTION = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE";
    private static final String VENDING_PKG = "com.android.vending";
    private static final String SERVICE_CLASS =
            "com.google.android.finsky.externalreferrer.GetInstallReferrerService";
    private static final String DESCRIPTOR =
            "com.google.android.finsky.externalreferrer.IGetInstallReferrerService";

    /** IGetInstallReferrerService.getInstallReferrer(Bundle) */
    private static final int TX_GET_INSTALL_REFERRER = 1;

    private static volatile String sReferrer;
    private static volatile long sClickTimestampSeconds;
    private static volatile long sInstallTimestampSeconds;
    private static volatile String sInstallVersion;

    private static final Map<IBinder, Boolean> sInjectedConnections =
            Collections.synchronizedMap(new WeakHashMap<IBinder, Boolean>());

    private ReferrerManager() {
    }

    public static String key(String packageName) {
        return packageName;
    }

    /** Runs in the server process, reads the raw json stored for the package. */
    public static String readStoredConfig(File hostFilesDir, String packageName) {
        if (hostFilesDir == null || packageName == null) {
            return null;
        }
        File file = new File(hostFilesDir, CONFIG_FILE);
        if (!file.exists()) {
            return null;
        }
        String raw = readText(file);
        if (raw == null || raw.isEmpty()) {
            return null;
        }
        try {
            JSONObject all = new JSONObject(raw);
            String value = all.optString(key(packageName), null);
            return value == null || value.isEmpty() ? null : value;
        } catch (Throwable t) {
            Slog.w(TAG, "parse " + CONFIG_FILE + " failed: " + t.getMessage());
            return null;
        }
    }

    /** Runs in the guest process, invoked from {@code BActivityThread.setConfig}. */
    public static void setConfig(String raw) {
        sReferrer = null;
        sInstallVersion = null;
        sClickTimestampSeconds = 0;
        sInstallTimestampSeconds = 0;
        if (raw == null || raw.isEmpty()) {
            return;
        }
        try {
            JSONObject json = new JSONObject(raw);
            String referrer = json.optString("referrer", "");
            if (referrer.isEmpty()) {
                return;
            }
            long now = System.currentTimeMillis() / 1000L;
            sReferrer = referrer;
            sClickTimestampSeconds = json.optLong("clickTimestamp", now);
            sInstallTimestampSeconds = json.optLong("installTimestamp", now);
            String version = json.optString("installVersion", "");
            sInstallVersion = version.isEmpty() ? null : version;
            Slog.d(TAG, "install referrer configured: " + referrer);
        } catch (Throwable t) {
            Slog.w(TAG, "parse referrer config failed: " + t.getMessage());
        }
    }

    public static boolean isConfigured() {
        String referrer = sReferrer;
        return referrer != null && !referrer.isEmpty();
    }

    public static String getReferrer() {
        return sReferrer;
    }

    public static boolean isInstallReferrerIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (ACTION.equals(intent.getAction())) {
            return true;
        }
        ComponentName component = intent.getComponent();
        return component != null
                && VENDING_PKG.equals(component.getPackageName())
                && SERVICE_CLASS.equals(component.getClassName());
    }

    public static boolean deliverFakeConnection(Object connection, Intent intent) {
        if (connection == null) {
            return false;
        }
        try {
            ComponentName componentName = intent.getComponent();
            if (componentName == null) {
                componentName = new ComponentName(VENDING_PKG, SERVICE_CLASS);
            }
            final IBinder binder = new FakeInstallReferrerBinder();
            final Object conn = connection;
            final ComponentName name = componentName;
            if (connection instanceof IInterface) {
                sInjectedConnections.put(((IInterface) connection).asBinder(), Boolean.TRUE);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (!deliverDirect(conn, name, binder)) {
                            deliverToConnection(conn, name, binder);
                        }
                    } catch (Throwable t) {
                        Slog.w(TAG, "async fake referrer delivery failed", t);
                    }
                }
            }, "referrer-delivery").start();
            return true;
        } catch (Throwable t) {
            Slog.w(TAG, "deliver fake referrer connection failed", t);
        }
        return false;
    }

    private static void deliverToConnection(Object connection, ComponentName name, IBinder binder) {
        try {
            for (Method method : connection.getClass().getMethods()) {
                if (!"connected".equals(method.getName())) {
                    continue;
                }
                Class<?>[] types = method.getParameterTypes();
                Object[] args;
                if (types.length == 4) {
                    args = new Object[]{name, binder, false, 0};
                } else if (types.length == 3) {
                    args = new Object[]{name, binder, false};
                } else if (types.length == 2) {
                    args = new Object[]{name, binder};
                } else {
                    continue;
                }
                method.invoke(connection, args);
                Slog.d(TAG, "delivered fake referrer service via connected/" + types.length);
                return;
            }
        } catch (Throwable t) {
            Slog.w(TAG, "deliverToConnection failed", t);
        }
    }

    private static boolean deliverDirect(Object connection, ComponentName name, IBinder binder) {
        try {
            Field dispatcherField = connection.getClass().getDeclaredField("mDispatcher");
            dispatcherField.setAccessible(true);
            Object ref = dispatcherField.get(connection);
            Object dispatcher = ref instanceof java.lang.ref.WeakReference
                    ? ((java.lang.ref.WeakReference<?>) ref).get() : ref;
            if (dispatcher == null) {
                return false;
            }
            Field connectionField = dispatcher.getClass().getDeclaredField("mConnection");
            connectionField.setAccessible(true);
            Object sc = connectionField.get(dispatcher);
            if (sc instanceof ServiceConnection) {
                ((ServiceConnection) sc).onServiceConnected(name, binder);
                Slog.d(TAG, "delivered fake referrer service to " + sc.getClass().getName());
                return true;
            }
        } catch (Throwable t) {
            Slog.w(TAG, "direct referrer delivery failed", t);
        }
        return false;
    }

    /** True when this connection was answered with the fake binder (skip the real unbind). */
    public static boolean consumeInjected(Object connection) {
        if (!(connection instanceof IInterface)) {
            return false;
        }
        try {
            return sInjectedConnections.remove(((IInterface) connection).asBinder()) != null;
        } catch (Throwable t) {
            return false;
        }
    }

    private static String readText(File file) {
        FileInputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toString("UTF-8");
        } catch (Throwable t) {
            Slog.w(TAG, "read referrer config failed: " + t);
            return null;
        } finally {
            CloseUtils.close(is);
            CloseUtils.close(bos);
        }
    }

    private static final class FakeInstallReferrerBinder extends Binder {

        @Override
        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            if (code != TX_GET_INSTALL_REFERRER) {
                Slog.w(TAG, "fake install referrer unknown code=" + code);
                return true;
            }
            Bundle result = new Bundle();
            result.putString("install_referrer", sReferrer == null ? "" : sReferrer);
            result.putLong("referrer_click_timestamp_seconds", sClickTimestampSeconds);
            result.putLong("install_begin_timestamp_seconds", sInstallTimestampSeconds);
            if (sInstallVersion != null) {
                result.putString("install_version", sInstallVersion);
            }
            result.putBoolean("google_play_instant", false);
            reply.writeNoException();
            if (Build.VERSION.SDK_INT >= 33) {
                reply.writeTypedObject(result, 0);
            } else {
                reply.writeParcelable(result, 0);
            }
            return true;
        }
    }
}
