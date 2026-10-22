package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy.class */
public class AuthenticationProxy extends ClassInvocationStub {
    public static final String TAG = "AuthenticationProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$SignIn.class */
    @ProxyMethod("signIn")
    public static class SignIn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling signIn call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: SignIn error, returning success", e);
                return AuthenticationProxy.createMockSignInResult();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$SignOut.class */
    @ProxyMethod("signOut")
    public static class SignOut extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling signOut call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: SignOut error, returning success", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$IsSignedIn.class */
    @ProxyMethod("isSignedIn")
    public static class IsSignedIn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling isSignedIn call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: IsSignedIn error, returning true", e);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$GetCurrentUser.class */
    @ProxyMethod("getCurrentUser")
    public static class GetCurrentUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling getCurrentUser call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: GetCurrentUser error, returning mock user", e);
                return AuthenticationProxy.createMockUser();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$GetAccessToken.class */
    @ProxyMethod("getAccessToken")
    public static class GetAccessToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling getAccessToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: GetAccessToken error, returning mock token", e);
                return "mock_access_token_" + System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$RefreshToken.class */
    @ProxyMethod("refreshToken")
    public static class RefreshToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling refreshToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: RefreshToken error, returning mock token", e);
                return "mock_refresh_token_" + System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$ValidateToken.class */
    @ProxyMethod("validateToken")
    public static class ValidateToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling validateToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: ValidateToken error, returning true", e);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$Login.class */
    @ProxyMethod("login")
    public static class Login extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling login call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: Login error, returning success", e);
                return AuthenticationProxy.createMockLoginResult();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$Logout.class */
    @ProxyMethod("logout")
    public static class Logout extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling logout call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: Logout error, returning success", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AuthenticationProxy$IsLoggedIn.class */
    @ProxyMethod("isLoggedIn")
    public static class IsLoggedIn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(AuthenticationProxy.TAG, "Authentication: Handling isLoggedIn call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(AuthenticationProxy.TAG, "Authentication: IsLoggedIn error, returning true", e);
                return true;
            }
        }
    }

    private static Object createMockSignInResult() {
        try {
            Class<?> bundleClass = Class.forName("android.os.Bundle");
            return bundleClass.newInstance();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to create mock sign in result", e);
            return null;
        }
    }

    private static Object createMockLoginResult() {
        try {
            Class<?> bundleClass = Class.forName("android.os.Bundle");
            return bundleClass.newInstance();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to create mock login result", e);
            return null;
        }
    }

    private static Object createMockUser() {
        try {
            Class<?> userClass = Class.forName("android.os.Bundle");
            return userClass.newInstance();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to create mock user", e);
            return null;
        }
    }
}
