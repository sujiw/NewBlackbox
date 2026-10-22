package top.niunaijun.blackbox.fake.service;

import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/FileSystemProxy.class */
public class FileSystemProxy extends ClassInvocationStub {
    public static final String TAG = "FileSystemProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/FileSystemProxy$Mkdirs.class */
    @ProxyMethod("mkdirs")
    public static class Mkdirs extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                File file = (File) who;
                String path = file.getAbsolutePath();
                if (path.contains("Helium Crashpad") || path.contains("HeliumCrashReporter")) {
                    Slog.d(FileSystemProxy.TAG, "FileSystem: mkdirs called for Helium crash path: " + path + ", returning true");
                    return true;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(FileSystemProxy.TAG, "FileSystem: mkdirs failed, returning true", e);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/FileSystemProxy$Mkdir.class */
    @ProxyMethod("mkdir")
    public static class Mkdir extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                File file = (File) who;
                String path = file.getAbsolutePath();
                if (path.contains("Helium Crashpad") || path.contains("HeliumCrashReporter")) {
                    Slog.d(FileSystemProxy.TAG, "FileSystem: mkdir called for Helium crash path: " + path + ", returning true");
                    return true;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(FileSystemProxy.TAG, "FileSystem: mkdir failed, returning true", e);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/FileSystemProxy$IsDirectory.class */
    @ProxyMethod("isDirectory")
    public static class IsDirectory extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                File file = (File) who;
                String path = file.getAbsolutePath();
                if (path.contains("Helium Crashpad") || path.contains("HeliumCrashReporter")) {
                    Slog.d(FileSystemProxy.TAG, "FileSystem: isDirectory called for Helium crash path: " + path + ", returning true");
                    return true;
                }
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(FileSystemProxy.TAG, "FileSystem: isDirectory failed, returning false", e);
                return false;
            }
        }
    }
}
