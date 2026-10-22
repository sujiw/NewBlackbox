package top.niunaijun.blackbox.fake.service.base;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.MethodHook;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/base/ValueMethodProxy.class */
public class ValueMethodProxy extends MethodHook {
    Object mValue;
    String mName;

    public ValueMethodProxy(String name, Object value) {
        this.mValue = value;
        this.mName = name;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected String getMethodName() {
        return this.mName;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
        return this.mValue;
    }
}
