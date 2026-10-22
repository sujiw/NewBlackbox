package top.niunaijun.blackbox.fake.service;

import android.database.sqlite.SQLiteException;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SQLiteDatabaseProxy.class */
public class SQLiteDatabaseProxy extends ClassInvocationStub {
    public static final String TAG = "SQLiteDatabaseProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SQLiteDatabaseProxy$RawQuery.class */
    @ProxyMethod("rawQuery")
    public static class RawQuery extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                String sql = (String) args[0];
                if (sql != null && sql.contains("DurableJob")) {
                    Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: rawQuery called with DurableJob table, returning empty cursor");
                    return null;
                }
                return method.invoke(who, args);
            } catch (SQLiteException e) {
                Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: rawQuery failed, returning null", e);
                return null;
            } catch (Exception e2) {
                Slog.e(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: rawQuery error", e2);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SQLiteDatabaseProxy$Query.class */
    @ProxyMethod("query")
    public static class Query extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                String table = (String) args[0];
                if (table != null && table.equals("DurableJob")) {
                    Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: query called on DurableJob table, returning empty cursor");
                    return null;
                }
                return method.invoke(who, args);
            } catch (SQLiteException e) {
                Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: query failed, returning null", e);
                return null;
            } catch (Exception e2) {
                Slog.e(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: query error", e2);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SQLiteDatabaseProxy$ExecSQL.class */
    @ProxyMethod("execSQL")
    public static class ExecSQL extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                String sql = (String) args[0];
                if (sql != null && sql.contains("DurableJob")) {
                    Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: execSQL called with DurableJob table, ignoring");
                    return null;
                }
                return method.invoke(who, args);
            } catch (SQLiteException e) {
                Slog.w(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: execSQL failed, ignoring", e);
                return null;
            } catch (Exception e2) {
                Slog.e(SQLiteDatabaseProxy.TAG, "SQLiteDatabase: execSQL error", e2);
                return method.invoke(who, args);
            }
        }
    }
}
