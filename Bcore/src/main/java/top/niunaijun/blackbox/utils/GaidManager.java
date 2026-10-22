package top.niunaijun.blackbox.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Per-app Google Advertising ID override.
 *
 * <p>Google Play Services exposes the advertising id through a bound service
 * ({@code com.google.android.gms.ads.identifier.service.START}). The id is
 * delivered over {@code IAdvertisingIdService}; instead of hooking the obfuscated
 * GMS classes we intercept the service binding in
 * {@code IActivityManagerProxy} and hand the caller a fake binder, which is
 * stable across GMS versions.</p>
 */
public final class GaidManager {

    private static final String TAG = "GaidManager";

    /** Must match the constant used by the host UI. */
    public static final String CONFIG_FILE = "app_gaid.json";
    public static final String AD_SERVICE_ACTION = "com.google.android.gms.ads.identifier.service.START";
    private static final String GMS_PKG = "com.google.android.gms";
    private static final String AD_SERVICE_CLASS = "com.google.android.gms.ads.identifier.service.AdvertisingIdService";

    /** Transaction codes of IAdvertisingIdService. */
    private static final int TX_GET_ID = 1;
    private static final int TX_IS_LIMIT_AD_TRACKING = 2;
    private static final int TX_IS_AD_ID_TRACKING_LIMITED = 3;

    private static volatile String sGaid;
    private static volatile boolean sLimitAdTracking;

    /** Connections whose bindService() was answered with the fake binder. */
    private static final Map<IBinder, Boolean> sInjectedConnections =
            Collections.synchronizedMap(new WeakHashMap<IBinder, Boolean>());

    private GaidManager() {
    }

    /** GAID overrides are per package, not per virtual user. */
    public static String key(String packageName) {
        return packageName;
    }

    /** Runs in the server process. */
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
            if (value != null) {
                return value;
            }
            // Legacy entries were stored as "<userId>_<packageName>".
            String defaultUserKey = "0_" + packageName;
            String legacy = all.optString(defaultUserKey, null);
            if (legacy != null) {
                return legacy;
            }
            Iterator<String> keys = all.keys();
            while (keys.hasNext()) {
                String legacyKey = keys.next();
                if (legacyKey.endsWith("_" + packageName)) {
                    return all.optString(legacyKey, null);
                }
            }
            return null;
        } catch (Throwable t) {
            Slog.w(TAG, "parse gaid config file failed", t);
            return null;
        }
    }

    /** Runs inside the guest process, pure memory work. */
    public static void setConfig(String raw) {
        sGaid = null;
        sLimitAdTracking = false;
        if (raw == null || raw.isEmpty()) {
            return;
        }
        try {
            JSONObject json = new JSONObject(raw);
            String id = json.optString("gaid", "");
            if (id.isEmpty()) {
                return;
            }
            sGaid = id;
            sLimitAdTracking = json.optBoolean("limitAdTracking", false);
            Slog.d(TAG, "gaid configured: " + id + " (limitAdTracking=" + sLimitAdTracking + ")");
        } catch (Throwable t) {
            Slog.w(TAG, "parse gaid config failed", t);
        }
    }

    public static boolean isConfigured() {
        return sGaid != null && !sGaid.isEmpty();
    }

    public static boolean isAdvertisingIdIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (AD_SERVICE_ACTION.equals(intent.getAction())) {
            return true;
        }
        ComponentName component = intent.getComponent();
        return component != null
                && GMS_PKG.equals(component.getPackageName())
                && component.getClassName() != null
                && component.getClassName().contains("ads.identifier");
    }

    /**
     * Answers the bindService() with a fake advertising id binder.
     *
     * <p>AOSP delivers {@code onServiceConnected} only after bindService()
     * returns. Calling the callback inline makes sdks that create their latch
     * or proxy inside the callback observe a half-initialized state and retry,
     * so the fake binder is handed over from a separate thread.</p>
     */
    public static boolean deliverFakeConnection(Object connection, Intent intent) {
        if (connection == null) {
            return false;
        }
        try {
            ComponentName componentName = intent.getComponent();
            if (componentName == null) {
                componentName = new ComponentName(GMS_PKG, AD_SERVICE_CLASS);
            }
            final IBinder binder = new FakeAdvertisingIdBinder();
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
                        Slog.w(TAG, "async fake advertising id delivery failed", t);
                    }
                }
            }, "gaid-delivery").start();
            return true;
        } catch (Throwable t) {
            Slog.w(TAG, "deliver fake advertising id connection failed", t);
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
                Slog.d(TAG, "delivered fake advertising id service via connected/"
                        + types.length);
                return;
            }
        } catch (Throwable t) {
            Slog.w(TAG, "deliverToConnection failed", t);
        }
    }

    private static boolean deliverDirect(Object connection, ComponentName name, IBinder binder) {
        try {
            java.lang.reflect.Field dispatcherField =
                    connection.getClass().getDeclaredField("mDispatcher");
            dispatcherField.setAccessible(true);
            Object ref = dispatcherField.get(connection);
            Object dispatcher = ref instanceof java.lang.ref.WeakReference
                    ? ((java.lang.ref.WeakReference<?>) ref).get() : ref;
            if (dispatcher == null) {
                return false;
            }
            java.lang.reflect.Field connectionField =
                    dispatcher.getClass().getDeclaredField("mConnection");
            connectionField.setAccessible(true);
            Object sc = connectionField.get(dispatcher);
            if (sc instanceof android.content.ServiceConnection) {
                ((android.content.ServiceConnection) sc).onServiceConnected(name, binder);
                Slog.d(TAG, "delivered fake advertising id service to "
                        + sc.getClass().getName());
                return true;
            }
        } catch (Throwable t) {
            Slog.w(TAG, "direct delivery failed", t);
        }
        return false;
    }

    /**
     * True when the given connection was answered with the fake binder, i.e.
     * the following unbindService() must not reach AMS.
     */
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
            Slog.w(TAG, "read gaid config failed: " + t);
            return null;
        } finally {
            CloseUtils.close(is);
            CloseUtils.close(bos);
        }
    }

    private static final class FakeAdvertisingIdBinder extends Binder {

        @Override
        public String getInterfaceDescriptor() {
            return "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TX_GET_ID:
                    // AIDL proxies call readException() before reading the payload,
                    // so a zero exception header must be written first.
                    reply.writeNoException();
                    reply.writeString(sGaid);
                    return true;
                case TX_IS_LIMIT_AD_TRACKING:
                case TX_IS_AD_ID_TRACKING_LIMITED:
                    reply.writeNoException();
                    reply.writeInt(sLimitAdTracking ? 1 : 0);
                    return true;
                default:
                    Slog.w(TAG, "fake advertising id unknown code=" + code);
                    reply.writeNoException();
                    return true;
            }
        }
    }
}
