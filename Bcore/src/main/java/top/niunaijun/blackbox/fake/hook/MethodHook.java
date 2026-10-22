package top.niunaijun.blackbox.fake.hook;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/hook/MethodHook.class */
public abstract class MethodHook {
    protected abstract Object hook(Object obj, Method method, Object[] objArr) throws Throwable;

    protected String getMethodName() {
        return null;
    }

    protected Object afterHook(Object result) throws Throwable {
        return result;
    }

    protected Object beforeHook(Object who, Method method, Object[] args) throws Throwable {
        return null;
    }

    protected boolean isEnable() {
        return BlackBoxCore.get().isBlackProcess();
    }
}
