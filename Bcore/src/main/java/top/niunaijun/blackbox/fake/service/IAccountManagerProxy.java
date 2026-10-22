package top.niunaijun.blackbox.fake.service;

import android.accounts.Account;
import android.accounts.IAccountManagerResponse;
import android.os.Bundle;
import black.android.accounts.BRIAccountManagerStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import java.util.Map;
import top.niunaijun.blackbox.fake.frameworks.BAccountManager;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy.class */
public class IAccountManagerProxy extends BinderInvocationStub {
    public static final String TAG = "IAccountManagerProxy";

    public IAccountManagerProxy() {
        super(BRServiceManager.get().getService("account"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRIAccountManagerStub.get().asInterface(BRServiceManager.get().getService("account"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("account");
    }

    @Override // top.niunaijun.blackbox.fake.hook.BinderInvocationStub, top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
        super.onBindMethod();
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Slog.d(TAG, "call " + method.getName());
        return super.invoke(proxy, method, args);
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getPassword.class */
    @ProxyMethod("getPassword")
    public static class getPassword extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getPassword((Account) args[0]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getUserData.class */
    @ProxyMethod("getUserData")
    public static class getUserData extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getUserData((Account) args[0], (String) args[1]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAuthenticatorTypes.class */
    @ProxyMethod("getAuthenticatorTypes")
    public static class getAuthenticatorTypes extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getAuthenticatorTypes();
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountsForPackage.class */
    @ProxyMethod("getAccountsForPackage")
    public static class getAccountsForPackage extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getAccountsForPackage((String) args[0], ((Integer) args[1]).intValue());
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountsByTypeForPackage.class */
    @ProxyMethod("getAccountsByTypeForPackage")
    public static class getAccountsByTypeForPackage extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getAccountsByTypeForPackage((String) args[0], (String) args[1]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountByTypeAndFeatures.class */
    @ProxyMethod("getAccountByTypeAndFeatures")
    public static class getAccountByTypeAndFeatures extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().getAccountByTypeAndFeatures((IAccountManagerResponse) args[0], (String) args[1], (String[]) args[2]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountsByFeatures.class */
    @ProxyMethod("getAccountsByFeatures")
    public static class getAccountsByFeatures extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().getAccountsByFeatures((IAccountManagerResponse) args[0], (String) args[1], (String[]) args[2]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountsAsUser.class */
    @ProxyMethod("getAccountsAsUser")
    public static class getAccountsAsUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getAccountsAsUser((String) args[0]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$addAccountExplicitly.class */
    @ProxyMethod("addAccountExplicitly")
    public static class addAccountExplicitly extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Boolean.valueOf(BAccountManager.get().addAccountExplicitly((Account) args[0], (String) args[1], (Bundle) args[2]));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$removeAccountAsUser.class */
    @ProxyMethod("removeAccountAsUser")
    public static class removeAccountAsUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().removeAccountAsUser((IAccountManagerResponse) args[0], (Account) args[1], ((Boolean) args[2]).booleanValue());
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$removeAccountExplicitly.class */
    @ProxyMethod("removeAccountExplicitly")
    public static class removeAccountExplicitly extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Boolean.valueOf(BAccountManager.get().removeAccountExplicitly((Account) args[0]));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$copyAccountToUser.class */
    @ProxyMethod("copyAccountToUser")
    public static class copyAccountToUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().copyAccountToUser((IAccountManagerResponse) args[0], (Account) args[1], ((Integer) args[2]).intValue(), ((Integer) args[3]).intValue());
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$invalidateAuthToken.class */
    @ProxyMethod("invalidateAuthToken")
    public static class invalidateAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().invalidateAuthToken((String) args[0], (String) args[1]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$peekAuthToken.class */
    @ProxyMethod("peekAuthToken")
    public static class peekAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().peekAuthToken((Account) args[0], (String) args[1]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$setAuthToken.class */
    @ProxyMethod("setAuthToken")
    public static class setAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().setAuthToken((Account) args[0], (String) args[1], (String) args[2]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$setPassword.class */
    @ProxyMethod("setPassword")
    public static class setPassword extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().setPassword((Account) args[0], (String) args[1]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$clearPassword.class */
    @ProxyMethod("clearPassword")
    public static class clearPassword extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().clearPassword((Account) args[0]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$setUserData.class */
    @ProxyMethod("setUserData")
    public static class setUserData extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().setUserData((Account) args[0], (String) args[1], (String) args[2]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$updateAppPermission.class */
    @ProxyMethod("updateAppPermission")
    public static class updateAppPermission extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().updateAppPermission((Account) args[0], (String) args[1], ((Integer) args[2]).intValue(), ((Boolean) args[3]).booleanValue());
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAuthToken.class */
    @ProxyMethod("getAuthToken")
    public static class getAuthToken extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().getAuthToken((IAccountManagerResponse) args[0], (Account) args[1], (String) args[2], ((Boolean) args[3]).booleanValue(), ((Boolean) args[4]).booleanValue(), (Bundle) args[5]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$addAccount.class */
    @ProxyMethod("addAccount")
    public static class addAccount extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().addAccount((IAccountManagerResponse) args[0], (String) args[1], (String) args[2], (String[]) args[3], ((Boolean) args[4]).booleanValue(), (Bundle) args[5]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$addAccountAsUser.class */
    @ProxyMethod("addAccountAsUser")
    public static class addAccountAsUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().addAccountAsUser((IAccountManagerResponse) args[0], (String) args[1], (String) args[2], (String[]) args[3], ((Boolean) args[4]).booleanValue(), (Bundle) args[5]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$updateCredentials.class */
    @ProxyMethod("updateCredentials")
    public static class updateCredentials extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().updateCredentials((IAccountManagerResponse) args[0], (Account) args[1], (String) args[2], ((Boolean) args[3]).booleanValue(), (Bundle) args[4]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$editProperties.class */
    @ProxyMethod("editProperties")
    public static class editProperties extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().editProperties((IAccountManagerResponse) args[0], (String) args[1], ((Boolean) args[2]).booleanValue());
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$confirmCredentialsAsUser.class */
    @ProxyMethod("confirmCredentialsAsUser")
    public static class confirmCredentialsAsUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().confirmCredentialsAsUser((IAccountManagerResponse) args[0], (Account) args[1], (Bundle) args[2], ((Boolean) args[3]).booleanValue());
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$accountAuthenticated.class */
    @ProxyMethod("accountAuthenticated")
    public static class accountAuthenticated extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().accountAuthenticated((Account) args[0]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAuthTokenLabel.class */
    @ProxyMethod("getAuthTokenLabel")
    public static class getAuthTokenLabel extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().getAuthTokenLabel((IAccountManagerResponse) args[0], (String) args[1], (String) args[2]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getPackagesAndVisibilityForAccount.class */
    @ProxyMethod("getPackagesAndVisibilityForAccount")
    public static class getPackagesAndVisibilityForAccount extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getPackagesAndVisibilityForAccount((Account) args[0]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$addAccountExplicitlyWithVisibility.class */
    @ProxyMethod("addAccountExplicitlyWithVisibility")
    public static class addAccountExplicitlyWithVisibility extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Boolean.valueOf(BAccountManager.get().addAccountExplicitlyWithVisibility((Account) args[0], (String) args[1], (Bundle) args[2], (Map) args[3]));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$setAccountVisibility.class */
    @ProxyMethod("setAccountVisibility")
    public static class setAccountVisibility extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Boolean.valueOf(BAccountManager.get().setAccountVisibility((Account) args[0], (String) args[1], ((Integer) args[2]).intValue()));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountVisibility.class */
    @ProxyMethod("getAccountVisibility")
    public static class getAccountVisibility extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return Integer.valueOf(BAccountManager.get().getAccountVisibility((Account) args[0], (String) args[1]));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$getAccountsAndVisibilityForPackage.class */
    @ProxyMethod("getAccountsAndVisibilityForPackage")
    public static class getAccountsAndVisibilityForPackage extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return BAccountManager.get().getAccountsAndVisibilityForPackage((String) args[0], (String) args[1]);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$registerAccountListener.class */
    @ProxyMethod("registerAccountListener")
    public static class registerAccountListener extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().registerAccountListener((String[]) args[0], (String) args[1]);
            return 0;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccountManagerProxy$unregisterAccountListener.class */
    @ProxyMethod("unregisterAccountListener")
    public static class unregisterAccountListener extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BAccountManager.get().unregisterAccountListener((String[]) args[0], (String) args[1]);
            return 0;
        }
    }
}
