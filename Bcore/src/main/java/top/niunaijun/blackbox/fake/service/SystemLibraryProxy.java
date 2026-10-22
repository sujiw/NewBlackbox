package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SystemLibraryProxy.class */
public class SystemLibraryProxy extends ClassInvocationStub {
    public static final String TAG = "SystemLibraryProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SystemLibraryProxy$LoadLibrary.class */
    @ProxyMethod("loadLibrary")
    public static class LoadLibrary extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof String)) {
                String libraryName = (String) args[0];
                Slog.d(SystemLibraryProxy.TAG, "System: loadLibrary called for: " + libraryName);
                if (libraryName.equals("c++_shared") || libraryName.contains("c++")) {
                    Slog.d(SystemLibraryProxy.TAG, "System: Intercepting c++ library load, returning success");
                    return null;
                }
                if (libraryName.contains("flutter") || libraryName.contains("meemo")) {
                    Slog.d(SystemLibraryProxy.TAG, "System: Intercepting Flutter/Meemo library load, returning success");
                    return null;
                }
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/SystemLibraryProxy$Load.class */
    @ProxyMethod("load")
    public static class Load extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof String)) {
                String libraryPath = (String) args[0];
                Slog.d(SystemLibraryProxy.TAG, "System: load called for: " + libraryPath);
                if (libraryPath.contains("libc++_shared.so") || libraryPath.contains("c++_shared")) {
                    Slog.d(SystemLibraryProxy.TAG, "System: Intercepting libc++_shared.so load, returning success");
                    return null;
                }
                if (libraryPath.contains("flutter") || libraryPath.contains("meemo")) {
                    Slog.d(SystemLibraryProxy.TAG, "System: Intercepting Flutter/Meemo library load, returning success");
                    return null;
                }
            }
            return method.invoke(who, args);
        }
    }
}
