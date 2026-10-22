package top.niunaijun.blackbox.fake.service;

import android.net.Uri;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy.class */
public class ContentResolverProxy extends ClassInvocationStub {
    public static final String TAG = "ContentResolverProxy";

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BlackBoxCore.getContext().getContentResolver();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy$Query.class */
    @ProxyMethod("query")
    public static class Query extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof Uri)) {
                Uri uri = (Uri) args[0];
                String uriString = uri.toString();
                if (uriString.contains("audio") || uriString.contains("media") || uriString.contains("content://media/external/audio") || uriString.contains("content://media/internal/audio") || uriString.contains("content://media/external/file") || uriString.contains("content://media/internal/file")) {
                    Slog.d(ContentResolverProxy.TAG, "ContentResolver: Allowing audio query: " + uriString);
                    return method.invoke(who, args);
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy$QueryWithProjection.class */
    @ProxyMethod("query")
    public static class QueryWithProjection extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 1 && (args[0] instanceof Uri)) {
                Uri uri = (Uri) args[0];
                String uriString = uri.toString();
                if (uriString.contains("audio") || uriString.contains("media") || uriString.contains("content://media/external/audio") || uriString.contains("content://media/internal/audio") || uriString.contains("content://media/external/file") || uriString.contains("content://media/internal/file")) {
                    Slog.d(ContentResolverProxy.TAG, "ContentResolver: Allowing audio query with projection: " + uriString);
                    return method.invoke(who, args);
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy$Insert.class */
    @ProxyMethod("insert")
    public static class Insert extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof Uri)) {
                Uri uri = (Uri) args[0];
                String uriString = uri.toString();
                if (uriString.contains("audio") || uriString.contains("media")) {
                    Slog.d(ContentResolverProxy.TAG, "ContentResolver: insert called for audio URI: " + uriString);
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy$Update.class */
    @ProxyMethod("update")
    public static class Update extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof Uri)) {
                Uri uri = (Uri) args[0];
                String uriString = uri.toString();
                if (uriString.contains("audio") || uriString.contains("media")) {
                    Slog.d(ContentResolverProxy.TAG, "ContentResolver: update called for audio URI: " + uriString);
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ContentResolverProxy$Delete.class */
    @ProxyMethod("delete")
    public static class Delete extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof Uri)) {
                Uri uri = (Uri) args[0];
                String uriString = uri.toString();
                if (uriString.contains("audio") || uriString.contains("media")) {
                    Slog.d(ContentResolverProxy.TAG, "ContentResolver: delete called for audio URI: " + uriString);
                }
            }
            return method.invoke(who, args);
        }
    }
}
