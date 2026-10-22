package top.niunaijun.blackbox.fake.service.base;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/base/PkgMethodProxy.class */
public class PkgMethodProxy extends MethodHook {
    String mName;

    public PkgMethodProxy(String name) {
        this.mName = name;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected String getMethodName() {
        return this.mName;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
        MethodParameterUtils.replaceFirstAppPkg(args);
        return method.invoke(who, args);
    }
}
