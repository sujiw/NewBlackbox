package top.niunaijun.blackbox.fake.service.libcore;

import black.libcore.io.BRLibcore;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.IOCore;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Reflector;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/libcore/OsStub.class */
public class OsStub extends ClassInvocationStub {
    public static final String TAG = "OsStub";
    private Object mBase = BRLibcore.get().os();

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return this.mBase;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        BRLibcore.get()._set_os(proxyInvocation);
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return BRLibcore.get().os() != getProxyInvocation();
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub, java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null && (args[i] instanceof String)) {
                    String orig = (String) args[i];
                    // /proc/cpuinfo 与 /proc/meminfo 换成档案生成的临时文件
                    String spoof = DeviceInfoManager.procRedirect(orig);
                    if (spoof != null) {
                        args[i] = spoof;
                        continue;
                    }
                    if (orig.startsWith("/")) {
                        args[i] = IOCore.get().redirectPath(orig);
                    }
                }
            }
        }
        return super.invoke(proxy, method, args);
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/libcore/OsStub$getuid.class */
    @ProxyMethod("getuid")
    public static class getuid extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            int callUid = ((Integer) method.invoke(who, args)).intValue();
            return Integer.valueOf(OsStub.getFakeUid(callUid));
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/libcore/OsStub$stat.class */
    @ProxyMethod("stat")
    public static class stat extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            try {
                Object invoke = method.invoke(who, args);
                Reflector.with(invoke).field("st_uid").set(Integer.valueOf(OsStub.getFakeUid(-1)));
                return invoke;
            } catch (Throwable e) {
                throw e.getCause();
            }
        }
    }

    private static int getFakeUid(int callUid) {
        if (callUid > 0 && callUid <= 10000) {
            return callUid;
        }
        if (BActivityThread.isThreadInit() && BActivityThread.currentActivityThread().isInit()) {
            return BActivityThread.getBAppId();
        }
        return BlackBoxCore.getHostUid();
    }

    /* 存储容量：StatFs 在 Java 层就是 Os.statvfs(path)，在这里统一改成档案里的值。 */
    @ProxyMethod("statvfs")
    public static class statvfs extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object stat = method.invoke(who, args);
            long total = DeviceInfoManager.totalStorage();
            long available = DeviceInfoManager.availableStorage();
            if (stat == null || total <= 0) {
                return stat;
            }
            long blockSize = fieldLong(stat, "f_frsize");
            if (blockSize <= 0) {
                blockSize = fieldLong(stat, "f_bsize");
            }
            if (blockSize <= 0) {
                return stat;
            }
            long availBlocks = Math.max(0, available / blockSize);
            setFieldLong(stat, "f_blocks", total / blockSize);
            setFieldLong(stat, "f_bfree", availBlocks);
            setFieldLong(stat, "f_bavail", availBlocks);
            return stat;
        }

        private static long fieldLong(Object target, String name) {
            try {
                java.lang.reflect.Field field = target.getClass().getField(name);
                field.setAccessible(true);
                return field.getLong(target);
            } catch (Throwable t) {
                return 0L;
            }
        }

        private static void setFieldLong(Object target, String name, long value) {
            try {
                java.lang.reflect.Field field = target.getClass().getDeclaredField(name);
                field.setAccessible(true);
                field.setLong(target, value);
            } catch (Throwable ignored) {
            }
        }
    }
}
