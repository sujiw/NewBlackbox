package top.niunaijun.blackbox.fake.service.context.providers;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.IInterface;

import java.lang.reflect.Method;

import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/context/providers/SystemProviderStub.class */
public class SystemProviderStub extends ClassInvocationStub implements BContentProvider {
    private IInterface mBase;

    @Override // top.niunaijun.blackbox.fake.service.context.providers.BContentProvider
    public IInterface wrapper(IInterface contentProviderProxy, String appPkg) {
        this.mBase = contentProviderProxy;
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

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("asBinder".equals(method.getName())) {
            return method.invoke(this.mBase, args);
        }
        if (!BlackBoxCore.get().isBlackProcess()) {
            // Host process: leave the call untouched. Rewriting the attribution
            // source makes system providers reject it with
            // "Calling uid ... doesn't match source uid ...".
            return method.invoke(this.mBase, args);
        }
        if (args != null && args.length > 0) {
            Object arg = args[0];
            if (arg instanceof String) {
                String authority = (String) arg;
                if (!isSystemProviderAuthority(authority)) {
                    args[0] = BlackBoxCore.getHostPkg();
                }
            }
        }
        String settingValue = settingsOverrideValue(method, args);
        Object result;
        try {
            result = method.invoke(this.mBase, args);
        } catch (Throwable t) {
            if (settingValue != null) {
                // 系统 settings provider 对虚拟包名的调用会抛 SecurityException，
                // 这里直接用档案值兜底，app 拿到的仍是伪造后的 android_id。
                return settingCursor(settingValue);
            }
            throw t;
        }
        return maybeOverrideSetting(method, result, settingValue);
    }

    /**
     * Replaces {@code Settings.Secure/System/Global} values for the keys carried by
     * the device profile (android_id …).
     *
     * <p>{@code Settings.Secure.getString()} funnels into a single-key
     * {@code query()} on the settings provider, so the cursor is rewritten instead
     * of the (cached) value map. Only single-key queries are touched — a query
     * without a name filter must keep returning the real table.</p>
     */
    private Object maybeOverrideSetting(Method method, Object result, String settingValue) {
        if (settingValue == null || !"query".equals(method.getName())) {
            return result;
        }
        try {
            if (!(result instanceof Cursor)) {
                return settingCursor(settingValue);
            }
            Cursor cursor = (Cursor) result;
            if (!cursor.moveToFirst()) {
                return settingCursor(settingValue);
            }
            return new OverrideCursor(cursor, settingValue);
        } catch (Throwable t) {
            return result;
        }
    }

    /** 单 key 查询时返回档案里的值，其余查询保持原样。 */
    private String settingsOverrideValue(Method method, Object[] args) {
        try {
            if (!"query".equals(method.getName())) {
                return null;
            }
            if (!isSettingsUri(args) || !isSettingsAuthority(args)) {
                return null;
            }
            String key = singleSelectionArg(args);
            String value = key != null ? DeviceInfoManager.getSecureSetting(key) : null;
            if (value != null) {
                Slog.d("SystemProviderStub", "settings " + key + " -> " + value);
            }
            return value;
        } catch (Throwable t) {
            return null;
        }
    }

    private Cursor settingCursor(String value) {
        MatrixCursor fake = new MatrixCursor(new String[]{"value"});
        fake.addRow(new Object[]{value});
        return fake;
    }

    private boolean isSettingsUri(Object[] args) {
        if (args == null) {
            return false;
        }
        for (Object arg : args) {
            if (arg instanceof Uri) {
                String authority = ((Uri) arg).getAuthority();
                return "settings".equals(authority);
            }
        }
        return false;
    }

    private boolean isSettingsAuthority(Object[] args) {
        if (args == null) {
            return false;
        }
        for (Object arg : args) {
            if (arg instanceof String && "settings".equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /** query(..., selectionArgs = new String[]{name}, ...) 里的唯一 key。 */
    private String singleSelectionArg(Object[] args) {
        if (args == null) {
            return null;
        }
        for (Object arg : args) {
            if (arg instanceof String[] && ((String[]) arg).length == 1) {
                String key = ((String[]) arg)[0];
                if (key != null && DeviceInfoManager.getSecureSetting(key) != null) {
                    return key;
                }
            }
        }
        return null;
    }

    private boolean isSystemProviderAuthority(String authority) {
        if (authority == null) {
            return false;
        }
        return authority.equals("settings") || authority.equals("media") || authority.equals("downloads") || authority.equals("contacts") || authority.equals("call_log") || authority.equals("telephony") || authority.equals("calendar") || authority.equals("browser") || authority.equals("user_dictionary") || authority.equals("applications") || authority.startsWith("com.android.") || authority.startsWith("android.");
    }

    /** 单行单列覆盖：Settings 的取值逻辑只读第 0 列。 */
    private static final class OverrideCursor extends CursorWrapper {
        private final String mValue;

        OverrideCursor(Cursor cursor, String value) {
            super(cursor);
            this.mValue = value;
        }

        @Override
        public String getString(int columnIndex) {
            return columnIndex == 0 ? mValue : super.getString(columnIndex);
        }

        @Override
        public int getInt(int columnIndex) {
            if (columnIndex == 0) {
                try {
                    return Integer.parseInt(mValue);
                } catch (NumberFormatException ignored) {
                    return 0;
                }
            }
            return super.getInt(columnIndex);
        }

        @Override
        public long getLong(int columnIndex) {
            if (columnIndex == 0) {
                try {
                    return Long.parseLong(mValue);
                } catch (NumberFormatException ignored) {
                    return 0L;
                }
            }
            return super.getLong(columnIndex);
        }

        @Override
        public boolean isNull(int columnIndex) {
            return columnIndex == 0 ? false : super.isNull(columnIndex);
        }
    }
}
