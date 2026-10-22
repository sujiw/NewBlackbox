package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ReLinkerProxy.class */
public class ReLinkerProxy extends ClassInvocationStub {
    public static final String TAG = "ReLinkerProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ReLinkerProxy$LoadLibrary.class */
    @ProxyMethod("loadLibrary")
    public static class LoadLibrary extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(ReLinkerProxy.TAG, "ReLinker: loadLibrary called, intercepting to prevent MissingLibraryException");
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ReLinkerProxy$LoadLibraryWithContext.class */
    @ProxyMethod("loadLibrary")
    public static class LoadLibraryWithContext extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 1) {
                String libraryName = (String) args[1];
                Slog.d(ReLinkerProxy.TAG, "ReLinker: loadLibrary called for: " + libraryName);
                return null;
            }
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ReLinkerProxy$LoadLibraryWithAllParams.class */
    @ProxyMethod("loadLibrary")
    public static class LoadLibraryWithAllParams extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 2) {
                String libraryName = (String) args[1];
                String version = (String) args[2];
                Slog.d(ReLinkerProxy.TAG, "ReLinker: loadLibrary called for: " + libraryName + " version: " + version);
                return null;
            }
            return null;
        }
    }
}
