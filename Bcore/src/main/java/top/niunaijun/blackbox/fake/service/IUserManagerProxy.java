package top.niunaijun.blackbox.fake.service;

import black.android.content.pm.BRUserInfo;
import black.android.os.BRIUserManagerStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IUserManagerProxy.class */
public class IUserManagerProxy extends BinderInvocationStub {
    public IUserManagerProxy() {
        super(BRServiceManager.get().getService("user"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRIUserManagerStub.get().asInterface(BRServiceManager.get().getService("user"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("user");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IUserManagerProxy$GetApplicationRestrictions.class */
    @ProxyMethod("getApplicationRestrictions")
    public static class GetApplicationRestrictions extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            args[0] = BlackBoxCore.getHostPkg();
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IUserManagerProxy$GetProfileParent.class */
    @ProxyMethod("getProfileParent")
    public static class GetProfileParent extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object blackBox = BRUserInfo.get()._new(BActivityThread.getUserId(), "BlackBox", BRUserInfo.get().FLAG_PRIMARY().intValue());
            return blackBox;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IUserManagerProxy$getUsers.class */
    @ProxyMethod("getUsers")
    public static class getUsers extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            return new ArrayList();
        }
    }
}
