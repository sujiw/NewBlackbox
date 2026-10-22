package top.niunaijun.blackbox.fake.service;

import android.content.Context;
import android.os.Process;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/WebViewProxy.class */
public class WebViewProxy extends ClassInvocationStub {
    public static final String TAG = "WebViewProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/WebViewProxy$Constructor.class */
    @ProxyMethod("<init>")
    public static class Constructor extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(WebViewProxy.TAG, "WebView: Constructor called, intercepting to prevent data directory conflicts");
            try {
                Context context = BlackBoxCore.getContext();
                if (context != null) {
                    String customDataDir = context.getApplicationInfo().dataDir + "/webview";
                    System.setProperty("webview.data.dir", customDataDir);
                    Slog.d(WebViewProxy.TAG, "WebView: Set custom data directory: " + customDataDir);
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(WebViewProxy.TAG, "WebView: Constructor failed, returning null", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/WebViewProxy$SetDataDirectorySuffix.class */
    @ProxyMethod("setDataDirectorySuffix")
    public static class SetDataDirectorySuffix extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0) {
                String suffix = (String) args[0];
                Slog.d(WebViewProxy.TAG, "WebView: setDataDirectorySuffix called with: " + suffix);
                String uniqueSuffix = suffix + "_" + Process.myPid();
                args[0] = uniqueSuffix;
                Slog.d(WebViewProxy.TAG, "WebView: Using unique suffix: " + uniqueSuffix);
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/WebViewProxy$GetDataDirectory.class */
    @ProxyMethod("getDataDirectory")
    public static class GetDataDirectory extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(WebViewProxy.TAG, "WebView: getDataDirectory called, returning unique directory");
            Context context = BlackBoxCore.getContext();
            if (context != null) {
                String uniqueDir = context.getApplicationInfo().dataDir + "/webview_" + Process.myPid();
                Slog.d(WebViewProxy.TAG, "WebView: Returning unique data directory: " + uniqueDir);
                return uniqueDir;
            }
            return method.invoke(who, args);
        }
    }
}
