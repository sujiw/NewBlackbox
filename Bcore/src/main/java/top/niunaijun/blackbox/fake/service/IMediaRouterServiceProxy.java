package top.niunaijun.blackbox.fake.service;

import black.android.media.BRIMediaRouterServiceStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IMediaRouterServiceProxy.class */
public class IMediaRouterServiceProxy extends BinderInvocationStub {
    public IMediaRouterServiceProxy() {
        super(BRServiceManager.get().getService("media_router"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRIMediaRouterServiceStub.get().asInterface(BRServiceManager.get().getService("media_router"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("media_router");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IMediaRouterServiceProxy$registerClientAsUser.class */
    @ProxyMethod("registerClientAsUser")
    public static class registerClientAsUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IMediaRouterServiceProxy$registerRouter2.class */
    @ProxyMethod("registerRouter2")
    public static class registerRouter2 extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
