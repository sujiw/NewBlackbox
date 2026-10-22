package top.niunaijun.blackbox.fake.service;

import android.util.Log;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ApkAssetsProxy.class */
public class ApkAssetsProxy extends ClassInvocationStub {
    public static final String TAG = "ApkAssetsProxy";
    private static final String APK_ASSETS_CLASS = "android.content.res.ApkAssets";

    public ApkAssetsProxy() {
        try {
            Class.forName(APK_ASSETS_CLASS);
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "ApkAssets class not found: " + e.getMessage());
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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ApkAssetsProxy$LoadOverlayFromPath.class */
    @ProxyMethod("loadOverlayFromPath")
    public static class LoadOverlayFromPath extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String path = (String) args[0];
            if (path != null && (path.contains("resource-cache") || path.contains("@idmap") || path.contains(".frro") || path.contains("systemui") || path.contains("data@resource-cache@"))) {
                Log.d(ApkAssetsProxy.TAG, "Blocking problematic overlay path: " + path);
                throw new RuntimeException("Blocked problematic overlay path: " + path);
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/ApkAssetsProxy$NativeLoad.class */
    @ProxyMethod("nativeLoad")
    public static class NativeLoad extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            String path = (String) args[0];
            if (path != null && (path.contains("resource-cache") || path.contains("@idmap") || path.contains(".frro") || path.contains("systemui") || path.contains("data@resource-cache@"))) {
                Log.d(ApkAssetsProxy.TAG, "Blocking problematic native load path: " + path);
                throw new RuntimeException("Blocked problematic native load path: " + path);
            }
            return method.invoke(who, args);
        }
    }
}
