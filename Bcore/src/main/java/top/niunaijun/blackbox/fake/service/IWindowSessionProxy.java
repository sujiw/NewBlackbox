package top.niunaijun.blackbox.fake.service;

import android.os.IInterface;
import android.view.WindowManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IWindowSessionProxy.class */
public class IWindowSessionProxy extends BinderInvocationStub {
    public static final String TAG = "WindowSessionStub";
    private IInterface mSession;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IWindowSessionProxy$AddToDisplayAsUser.class */
    @ProxyMethod("addToDisplayAsUser")
    public static class AddToDisplayAsUser extends AddToDisplay {
    }

    public IWindowSessionProxy(IInterface session) {
        super(session.asBinder());
        this.mSession = session;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return this.mSession;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    public Object getProxyInvocation() {
        return super.getProxyInvocation();
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IWindowSessionProxy$AddToDisplay.class */
    @ProxyMethod("addToDisplay")
    public static class AddToDisplay extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            for (Object arg : args) {
                if (arg != null && (arg instanceof WindowManager.LayoutParams)) {
                    ((WindowManager.LayoutParams) arg).packageName = BlackBoxCore.getHostPkg();
                }
            }
            return method.invoke(who, args);
        }
    }
}
