package top.niunaijun.blackbox.fake.hook;

import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import top.niunaijun.blackbox.utils.MethodParameterUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/hook/ClassInvocationStub.class */
public abstract class ClassInvocationStub implements InvocationHandler, IInjectHook {
    public static final String TAG = ClassInvocationStub.class.getSimpleName();
    private final Map<String, MethodHook> mMethodHookMap = new HashMap();
    private Object mBase;
    private Object mProxyInvocation;
    private boolean onlyProxy;

    protected abstract Object getWho();

    protected abstract void inject(Object obj, Object obj2);

    protected void onBindMethod() {
    }

    protected Object getProxyInvocation() {
        return this.mProxyInvocation;
    }

    protected Object getBase() {
        return this.mBase;
    }

    protected void onlyProxy(boolean o) {
        this.onlyProxy = o;
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public void injectHook() {
        this.mBase = getWho();
        this.mProxyInvocation = Proxy.newProxyInstance(this.mBase.getClass().getClassLoader(), MethodParameterUtils.getAllInterface(this.mBase.getClass()), this);
        if (!this.onlyProxy) {
            inject(this.mBase, this.mProxyInvocation);
        }
        onBindMethod();
        Class<?>[] declaredClasses = getClass().getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            initAnnotation(declaredClass);
        }
        ScanClass scanClass = (ScanClass) getClass().getAnnotation(ScanClass.class);
        if (scanClass != null) {
            for (Class<?> aClass : scanClass.value()) {
                for (Class<?> declaredClass2 : aClass.getDeclaredClasses()) {
                    initAnnotation(declaredClass2);
                }
            }
        }
    }

    protected void initAnnotation(Class<?> clazz) {
        ProxyMethod proxyMethod = (ProxyMethod) clazz.getAnnotation(ProxyMethod.class);
        if (proxyMethod != null) {
            String name = proxyMethod.value();
            if (!TextUtils.isEmpty(name)) {
                try {
                    addMethodHook(name, (MethodHook) clazz.newInstance());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        ProxyMethods proxyMethods = (ProxyMethods) clazz.getAnnotation(ProxyMethods.class);
        if (proxyMethods != null) {
            String[] value = proxyMethods.value();
            for (String str : value) {
                try {
                    addMethodHook(str, (MethodHook) clazz.newInstance());
                } catch (Throwable t2) {
                    t2.printStackTrace();
                }
            }
        }
    }

    protected void addMethodHook(MethodHook methodHook) {
        this.mMethodHookMap.put(methodHook.getMethodName(), methodHook);
    }

    protected void addMethodHook(String name, MethodHook methodHook) {
        this.mMethodHookMap.put(name, methodHook);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodHook methodHook = this.mMethodHookMap.get(method.getName());
        if (methodHook == null || !methodHook.isEnable()) {
            try {
                return method.invoke(this.mBase, args);
            } catch (Throwable e) {
                throw e.getCause();
            }
        }
        Object result = methodHook.beforeHook(this.mBase, method, args);
        if (result != null) {
            return result;
        }
        return methodHook.afterHook(methodHook.hook(this.mBase, method, args));
    }
}
