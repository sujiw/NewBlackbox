package top.niunaijun.blackbox.fake.service.context.providers;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.IInterface;
import black.android.content.BRAttributionSource;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.ContextCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/context/providers/ContentProviderStub.class */
public class ContentProviderStub extends ClassInvocationStub implements BContentProvider {
    public static final String TAG = "ContentProviderStub";
    private IInterface mBase;
    private String mAppPkg;

    @Override // top.niunaijun.blackbox.fake.service.context.providers.BContentProvider
    public IInterface wrapper(IInterface contentProviderProxy, String appPkg) {
        this.mBase = contentProviderProxy;
        this.mAppPkg = appPkg;
        injectHook();
        return (IInterface) getProxyInvocation();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return this.mBase;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String message;
        if ("asBinder".equals(method.getName())) {
            return method.invoke(this.mBase, args);
        }
        if (!BlackBoxCore.get().isBlackProcess()) {
            // Host / server process: nothing to rewrite. Patching the
            // attribution source here makes system providers (settings, which
            // TextKeyListener / accessibility read) reject the call with
            // "Calling uid ... doesn't match source uid ...", killing the host.
            return method.invoke(this.mBase, args);
        }
        // 设备档案里的 Settings 值（android_id …）优先于系统 provider：这里直接返回，
        // 不再走真实查询（虚拟包名会被系统 provider 以 uid 不匹配拒绝）。
        String settingValue = settingsOverrideValue(method, args);
        if (settingValue != null) {
            return settingCursor(settingValue);
        }
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof String) {
                    String strArg = (String) arg;
                    if (!isSystemProviderAuthority(strArg)) {
                        args[i] = this.mAppPkg;
                    }
                }
                // The attribution source is deliberately left untouched: its uid
                // must match the real calling uid, otherwise system providers
                // (settings, media, ...) throw SecurityException.
            }
        }
        String methodName = method.getName();
        if (methodName.equals("query") || methodName.equals("insert") || methodName.equals("update") || methodName.equals("delete") || methodName.equals("bulkInsert") || methodName.equals("call")) {
            try {
                return method.invoke(this.mBase, args);
            } catch (Throwable e2) {
                Throwable cause = unwrap(e2);
                if (cause instanceof SecurityException) {
                    // System providers compare the attribution source uid with the
                    // (virtualised) calling uid and reject the call. Returning a
                    // safe default keeps the guest app alive.
                    Slog.w(TAG, "SecurityException in " + methodName
                            + ", returning safe default: " + cause.getMessage());
                    return getSafeDefaultValue(methodName);
                } else if ((cause instanceof RuntimeException) && (message = cause.getMessage()) != null && (message.contains("uid") || message.contains("permission"))) {
                    Slog.w(TAG, "Permission/UID error in ContentProvider call, returning safe default: " + message);
                    return getSafeDefaultValue(methodName);
                }
                throw e2.getCause();
            }
        }
        try {
            return method.invoke(this.mBase, args);
        } catch (Throwable e3) {
            throw e3.getCause();
        }
    }

    /**
     * Walks the cause chain, which may include wrappers added by dynamic proxies
     * (InvocationTargetException / UndeclaredThrowableException).
     */
    private static Throwable unwrap(Throwable t) {
        Throwable current = t;
        for (int i = 0; i < 6 && current != null; i++) {
            if (current instanceof SecurityException) {
                return current;
            }
            Throwable next = current.getCause();
            if (next == null || next == current) {
                return current;
            }
            current = next;
        }
        return current;
    }

    /** Settings.Secure/System/Global 单 key 查询 -> 档案值（没有则返回 null）。 */
    private String settingsOverrideValue(Method method, Object[] args) {
        try {
            if (!"query".equals(method.getName()) || args == null) {
                return null;
            }
            boolean settingsUri = false;
            String[] selectionArgs = null;
            for (Object arg : args) {
                if (arg instanceof Uri && "settings".equals(((Uri) arg).getAuthority())) {
                    settingsUri = true;
                } else if (arg instanceof String && "settings".equals(arg)) {
                    settingsUri = true;
                } else if (arg instanceof String[] && ((String[]) arg).length == 1) {
                    selectionArgs = (String[]) arg;
                }
            }
            if (!settingsUri || selectionArgs == null) {
                return null;
            }
            String value = DeviceInfoManager.getSecureSetting(selectionArgs[0]);
            if (value != null) {
                Slog.d(TAG, "settings " + selectionArgs[0] + " -> " + value);
            }
            return value;
        } catch (Throwable t) {
            return null;
        }
    }

    private Cursor settingCursor(String value) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"value"});
        cursor.addRow(new Object[]{value});
        return cursor;
    }

    private Object getSafeDefaultValue(String methodName) {
        switch (methodName) {
            case "query":
                return null;
            case "insert":
                return null;
            case "update":
            case "delete":
                return 0;
            case "bulkInsert":
                return 0;
            case "call":
                return null;
            case "getType":
                return null;
            case "openFile":
                return null;
            case "openAssetFile":
                return null;
            default:
                return null;
        }
    }

    private boolean isSystemProviderAuthority(String authority) {
        if (authority == null) {
            return false;
        }
        return authority.equals("settings") || authority.equals("media") || authority.equals("downloads") || authority.equals("contacts") || authority.equals("call_log") || authority.equals("telephony") || authority.equals("calendar") || authority.equals("browser") || authority.equals("user_dictionary") || authority.equals("applications") || authority.startsWith("com.android.") || authority.startsWith("android.");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }
}
