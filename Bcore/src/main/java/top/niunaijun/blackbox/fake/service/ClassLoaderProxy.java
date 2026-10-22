package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ClassLoaderProxy.class */
public class ClassLoaderProxy extends ClassInvocationStub {
    public static final String TAG = "ClassLoaderProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ClassLoaderProxy$LoadClass.class */
    @ProxyMethod("loadClass")
    public static class LoadClass extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            String className = (String) args[0];
            boolean zBooleanValue = args.length > 1 ? ((Boolean) args[1]).booleanValue() : false;
            if (ClassLoaderProxy.isProblematicClass(className)) {
                Slog.d(ClassLoaderProxy.TAG, "ClassLoader: loadClass called for problematic class: " + className + ", returning null");
                return null;
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if ((e.getCause() instanceof ClassNotFoundException) && ClassLoaderProxy.isProblematicClass(className)) {
                    Slog.d(ClassLoaderProxy.TAG, "ClassLoader: ClassNotFoundException for problematic class: " + className + ", returning null");
                    return null;
                }
                Slog.e(ClassLoaderProxy.TAG, "ClassLoader: loadClass error", e);
                throw e;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ClassLoaderProxy$FindClass.class */
    @ProxyMethod("findClass")
    public static class FindClass extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            String className = (String) args[0];
            if (ClassLoaderProxy.isProblematicClass(className)) {
                Slog.d(ClassLoaderProxy.TAG, "ClassLoader: findClass called for problematic class: " + className + ", returning null");
                return null;
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if ((e.getCause() instanceof ClassNotFoundException) && ClassLoaderProxy.isProblematicClass(className)) {
                    Slog.d(ClassLoaderProxy.TAG, "ClassLoader: ClassNotFoundException for problematic class: " + className + ", returning null");
                    return null;
                }
                Slog.e(ClassLoaderProxy.TAG, "ClassLoader: findClass error", e);
                throw e;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ClassLoaderProxy$ForName.class */
    @ProxyMethod("forName")
    public static class ForName extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Exception {
            String className = (String) args[0];
            if (ClassLoaderProxy.isProblematicClass(className)) {
                Slog.d(ClassLoaderProxy.TAG, "ClassLoader: forName called for problematic class: " + className + ", returning null");
                return null;
            }
            try {
                return method.invoke(who, args);
            } catch (Exception e) {
                if ((e.getCause() instanceof ClassNotFoundException) && ClassLoaderProxy.isProblematicClass(className)) {
                    Slog.d(ClassLoaderProxy.TAG, "ClassLoader: ClassNotFoundException for problematic class: " + className + ", returning null");
                    return null;
                }
                Slog.e(ClassLoaderProxy.TAG, "ClassLoader: forName error", e);
                throw e;
            }
        }
    }

    private static boolean isProblematicClass(String className) {
        if (className == null) {
            return false;
        }
        if (className.contains("kotlinx.coroutines.test") || className.contains("com.mediatek.perfservice") || className.contains("com.mediatek.powerhalmgr") || className.contains("androidx.datastore.preferences.protobuf") || className.contains("NewInstanceSchemaFull") || className.contains("ExtensionSchemaFull") || className.contains("org.robolectric") || className.contains("Robolectric") || className.contains("TestMainDispatcherFactory") || className.contains("PerfServiceWrapper") || className.contains("PowerHalMgrFactory")) {
            return true;
        }
        return false;
    }
}
