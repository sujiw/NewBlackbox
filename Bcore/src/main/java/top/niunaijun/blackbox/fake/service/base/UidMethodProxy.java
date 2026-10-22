package top.niunaijun.blackbox.fake.service.base;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.fake.hook.MethodHook;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/base/UidMethodProxy.class */
public class UidMethodProxy extends MethodHook {
    private final int index;
    private final String name;

    public UidMethodProxy(String name, int index) {
        this.index = index;
        this.name = name;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected String getMethodName() {
        return this.name;
    }

    @Override // top.niunaijun.blackbox.fake.hook.MethodHook
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
        int uid = ((Integer) args[this.index]).intValue();
        if (uid == BActivityThread.getBUid()) {
            args[this.index] = Integer.valueOf(BlackBoxCore.getHostUid());
        }
        return method.invoke(who, args);
    }
}
