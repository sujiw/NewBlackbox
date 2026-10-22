package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy.class */
public class GoogleAccountManagerProxy extends ClassInvocationStub {
    public static final String TAG = "GoogleAccountManagerProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$GetAccounts.class */
    @ProxyMethod("getAccounts")
    public static class GetAccounts extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling getAccounts call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: GetAccounts error, returning empty array", e);
                return new Object[0];
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$GetAccountsByType.class */
    @ProxyMethod("getAccountsByType")
    public static class GetAccountsByType extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling getAccountsByType call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: GetAccountsByType error, returning empty array", e);
                return new Object[0];
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$GetPassword.class */
    @ProxyMethod("getPassword")
    public static class GetPassword extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling getPassword call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: GetPassword error, returning null", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$GetUserData.class */
    @ProxyMethod("getUserData")
    public static class GetUserData extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling getUserData call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: GetUserData error, returning null", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$AddAccount.class */
    @ProxyMethod("addAccount")
    public static class AddAccount extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling addAccount call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: AddAccount error, returning null", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$RemoveAccount.class */
    @ProxyMethod("removeAccount")
    public static class RemoveAccount extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling removeAccount call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: RemoveAccount error, returning false", e);
                return false;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$HasFeatures.class */
    @ProxyMethod("hasFeatures")
    public static class HasFeatures extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling hasFeatures call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: HasFeatures error, returning true", e);
                return true;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$GetAuthToken.class */
    @ProxyMethod("getAuthToken")
    public static class GetAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling getAuthToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: GetAuthToken error, returning mock token", e);
                return "mock_auth_token_" + System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$InvalidateAuthToken.class */
    @ProxyMethod("invalidateAuthToken")
    public static class InvalidateAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling invalidateAuthToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: InvalidateAuthToken error, ignoring", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$PeekAuthToken.class */
    @ProxyMethod("peekAuthToken")
    public static class PeekAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling peekAuthToken call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: PeekAuthToken error, returning null", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$SetPassword.class */
    @ProxyMethod("setPassword")
    public static class SetPassword extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling setPassword call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: SetPassword error, ignoring", e);
                return null;
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/GoogleAccountManagerProxy$SetUserData.class */
    @ProxyMethod("setUserData")
    public static class SetUserData extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Slog.d(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: Handling setUserData call");
                return method.invoke(who, args);
            } catch (Exception e) {
                Slog.w(GoogleAccountManagerProxy.TAG, "GoogleAccountManager: SetUserData error, ignoring", e);
                return null;
            }
        }
    }
}
