package top.niunaijun.blackbox.fake.service;

import android.content.pm.ApplicationInfo;
import black.android.os.BRServiceManager;
import black.android.view.accessibility.BRIAccessibilityManagerStub;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.user.BUserHandle;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethods;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccessibilityManagerProxy.class */
public class IAccessibilityManagerProxy extends BinderInvocationStub {
    public IAccessibilityManagerProxy() {
        super(BRServiceManager.get().getService("accessibility"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return BRIAccessibilityManagerStub.get().asInterface(BRServiceManager.get().getService("accessibility"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("accessibility");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAccessibilityManagerProxy$ReplaceUserId.class */
    @ProxyMethods({"interrupt", "sendAccessibilityEvent", "addClient", "getInstalledAccessibilityServiceList", "getEnabledAccessibilityServiceList", "addAccessibilityInteractionConnection", "getWindowToken"})
    public static class ReplaceUserId extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null) {
                int index = args.length - 1;
                Object arg = args[index];
                if (arg instanceof Integer) {
                    ApplicationInfo applicationInfo = BlackBoxCore.getContext().getApplicationInfo();
                    args[index] = Integer.valueOf(BUserHandle.getUserId(applicationInfo.uid));
                }
            }
            return method.invoke(who, args);
        }
    }
}
