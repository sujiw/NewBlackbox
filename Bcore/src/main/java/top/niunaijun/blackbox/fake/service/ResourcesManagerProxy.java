package top.niunaijun.blackbox.fake.service;

import android.util.Log;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ResourcesManagerProxy.class */
public class ResourcesManagerProxy extends ClassInvocationStub {
    public static final String TAG = "ResourcesManagerProxy";
    private static final String RESOURCES_MANAGER_CLASS = "android.app.ResourcesManager";

    public ResourcesManagerProxy() {
        try {
            Class.forName(RESOURCES_MANAGER_CLASS);
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "ResourcesManager class not found: " + e.getMessage());
        }
    }

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

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
        super.onBindMethod();
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ResourcesManagerProxy$LoadApkAssets.class */
    @ProxyMethod("loadApkAssets")
    public static class LoadApkAssets extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String path = (String) args[0];
            if (path != null && (path.contains("resource-cache") || path.contains("@idmap") || path.contains(".frro") || path.contains("systemui") || path.contains("data@resource-cache@"))) {
                Log.d(ResourcesManagerProxy.TAG, "Blocking problematic ApkAssets load: " + path);
                return null;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ResourcesManagerProxy$LoadOverlayFromPath.class */
    @ProxyMethod("loadOverlayFromPath")
    public static class LoadOverlayFromPath extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String path = (String) args[0];
            if (path != null && (path.contains("resource-cache") || path.contains("@idmap") || path.contains(".frro") || path.contains("systemui") || path.contains("data@resource-cache@"))) {
                Log.d(ResourcesManagerProxy.TAG, "Blocking problematic overlay path: " + path);
                return null;
            }
            return method.invoke(who, args);
        }
    }
}
