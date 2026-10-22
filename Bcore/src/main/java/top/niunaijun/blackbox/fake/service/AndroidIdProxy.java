package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy.class */
public class AndroidIdProxy extends ClassInvocationStub {
    public static final String TAG = "AndroidIdProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy$GetAndroidId.class */
    @ProxyMethod("getAndroidId")
    public static class GetAndroidId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AndroidIdProxy.TAG, "AndroidId: Handling getAndroidId call");
                Object result = method.invoke(who, args);
                if (result == null || "0".equals(result.toString()) || "".equals(result.toString())) {
                    Slog.w(AndroidIdProxy.TAG, "AndroidId: Invalid Android ID detected, returning mock ID");
                    return AndroidIdProxy.generateMockAndroidId();
                }
                return result;
            } catch (Exception e) {
                Slog.w(AndroidIdProxy.TAG, "AndroidId: GetAndroidId error, returning mock ID", e);
                return AndroidIdProxy.generateMockAndroidId();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy$GetString.class */
    @ProxyMethod("getString")
    public static class GetString extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AndroidIdProxy.TAG, "AndroidId: Handling getString call");
                Object result = method.invoke(who, args);
                if (args != null && args.length > 0 && (args[0] instanceof String)) {
                    String key = (String) args[0];
                    if ((key.contains("android_id") || key.contains("ANDROID_ID") || key.contains("secure_id") || key.contains("device_id")) && (result == null || "0".equals(result.toString()) || "".equals(result.toString()))) {
                        Slog.w(AndroidIdProxy.TAG, "AndroidId: Invalid Android ID string detected, returning mock ID");
                        return AndroidIdProxy.generateMockAndroidId();
                    }
                }
                return result;
            } catch (Exception e) {
                Slog.w(AndroidIdProxy.TAG, "AndroidId: GetString error, returning original result", e);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy$GetLong.class */
    @ProxyMethod("getLong")
    public static class GetLong extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AndroidIdProxy.TAG, "AndroidId: Handling getLong call");
                Object result = method.invoke(who, args);
                if (args != null && args.length > 0 && (args[0] instanceof String)) {
                    String key = (String) args[0];
                    if ((key.contains("android_id") || key.contains("ANDROID_ID") || key.contains("secure_id") || key.contains("device_id")) && (result == null || ((Number) result).longValue() == 0)) {
                        Slog.w(AndroidIdProxy.TAG, "AndroidId: Invalid Android ID long detected, returning mock ID");
                        return AndroidIdProxy.generateMockAndroidIdLong();
                    }
                }
                return result;
            } catch (Exception e) {
                Slog.w(AndroidIdProxy.TAG, "AndroidId: GetLong error, returning original result", e);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy$Get.class */
    @ProxyMethod("get")
    public static class Get extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AndroidIdProxy.TAG, "AndroidId: Handling get call");
                Object result = method.invoke(who, args);
                if (args != null && args.length > 0 && (args[0] instanceof String)) {
                    String key = (String) args[0];
                    if ((key.contains("android_id") || key.contains("ANDROID_ID") || key.contains("secure_id") || key.contains("device_id")) && (result == null || "0".equals(result.toString()) || "".equals(result.toString()))) {
                        Slog.w(AndroidIdProxy.TAG, "AndroidId: Invalid Android ID get detected, returning mock ID");
                        return AndroidIdProxy.generateMockAndroidId();
                    }
                }
                return result;
            } catch (Exception e) {
                Slog.w(AndroidIdProxy.TAG, "AndroidId: Get error, returning original result", e);
                return method.invoke(who, args);
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AndroidIdProxy$Read.class */
    @ProxyMethod("read")
    public static class Read extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AndroidIdProxy.TAG, "AndroidId: Handling read call");
                Object result = method.invoke(who, args);
                if (args != null && args.length > 0 && (args[0] instanceof String)) {
                    String key = (String) args[0];
                    if ((key.contains("android_id") || key.contains("ANDROID_ID") || key.contains("secure_id") || key.contains("device_id")) && (result == null || "0".equals(result.toString()) || "".equals(result.toString()))) {
                        Slog.w(AndroidIdProxy.TAG, "AndroidId: Invalid Android ID read detected, returning mock ID");
                        return AndroidIdProxy.generateMockAndroidId();
                    }
                }
                return result;
            } catch (Exception e) {
                Slog.w(AndroidIdProxy.TAG, "AndroidId: Read error, returning original result", e);
                return method.invoke(who, args);
            }
        }
    }

    private static String generateMockAndroidId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(Integer.toHexString((int) (Math.random() * 16.0d)));
        }
        String mockId = sb.toString().toUpperCase();
        Slog.d(TAG, "AndroidId: Generated mock Android ID: " + mockId);
        return mockId;
    }

    private static Long generateMockAndroidIdLong() {
        long mockId = (long) (Math.random() * 9.223372036854776E18d);
        Slog.d(TAG, "AndroidId: Generated mock Android ID long: " + mockId);
        return Long.valueOf(mockId);
    }
}
