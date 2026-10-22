package top.niunaijun.blackbox.fake.service;

import android.app.ActivityManager;
import android.os.IBinder;
import black.android.app.BRActivityClient;
import black.android.util.BRSingleton;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.frameworks.BActivityManager;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.fake.hook.ScanClass;
import top.niunaijun.blackbox.utils.compat.TaskDescriptionCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityClientProxy.class */
@ScanClass({ActivityManagerCommonProxy.class})
public class IActivityClientProxy extends ClassInvocationStub {
    public static final String TAG = "IActivityClientProxy";
    private final Object who;

    public IActivityClientProxy(Object who) {
        this.who = who;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        if (this.who != null) {
            return this.who;
        }
        Object instance = BRActivityClient.get().getInstance();
        Object singleton = BRActivityClient.get(instance).INTERFACE_SINGLETON();
        return BRSingleton.get(singleton).get();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        Object instance = BRActivityClient.get().getInstance();
        Object singleton = BRActivityClient.get(instance).INTERFACE_SINGLETON();
        BRSingleton.get(singleton)._set_mInstance(proxyInvocation);
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    public Object getProxyInvocation() {
        return super.getProxyInvocation();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    public void onlyProxy(boolean o) {
        super.onlyProxy(o);
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityClientProxy$FinishActivity.class */
    @ProxyMethod("finishActivity")
    public static class FinishActivity extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IBinder token = (IBinder) args[0];
            BActivityManager.get().onFinishActivity(token);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityClientProxy$ActivityResumed.class */
    @ProxyMethod("activityResumed")
    public static class ActivityResumed extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IBinder token = (IBinder) args[0];
            BActivityManager.get().onActivityResumed(token);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityClientProxy$ActivityDestroyed.class */
    @ProxyMethod("activityDestroyed")
    public static class ActivityDestroyed extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            IBinder token = (IBinder) args[0];
            BActivityManager.get().onActivityDestroyed(token);
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IActivityClientProxy$SetTaskDescription.class */
    @ProxyMethod("setTaskDescription")
    public static class SetTaskDescription extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            ActivityManager.TaskDescription td = (ActivityManager.TaskDescription) args[1];
            args[1] = TaskDescriptionCompat.fix(td);
            return method.invoke(who, args);
        }
    }
}
