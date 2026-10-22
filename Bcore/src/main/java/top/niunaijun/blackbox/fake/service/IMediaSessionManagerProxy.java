package top.niunaijun.blackbox.fake.service;

import black.android.media.session.BRISessionManagerStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IMediaSessionManagerProxy.class */
public class IMediaSessionManagerProxy extends BinderInvocationStub {
    public IMediaSessionManagerProxy() {
        super(BRServiceManager.get().getService("media_session"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRISessionManagerStub.get().asInterface(BRServiceManager.get().getService("media_session"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("media_session");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IMediaSessionManagerProxy$CreateSession.class */
    @ProxyMethod("createSession")
    public static class CreateSession extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && (args[0] instanceof String)) {
                args[0] = BlackBoxCore.getHostPkg();
            }
            return method.invoke(who, args);
        }
    }
}
