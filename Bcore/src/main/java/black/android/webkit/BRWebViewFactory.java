package black.android.webkit;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/webkit/BRWebViewFactory.class */
public class BRWebViewFactory {
    public static WebViewFactoryStatic getWithException() {
        return (WebViewFactoryStatic) BlackReflection.create(WebViewFactoryStatic.class, (Object) null, true);
    }

    public static WebViewFactoryStatic get() {
        return (WebViewFactoryStatic) BlackReflection.create(WebViewFactoryStatic.class, (Object) null, false);
    }

    public static WebViewFactoryContext getWithException(Object caller) {
        return (WebViewFactoryContext) BlackReflection.create(WebViewFactoryContext.class, caller, true);
    }

    public static WebViewFactoryContext get(Object caller) {
        return (WebViewFactoryContext) BlackReflection.create(WebViewFactoryContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WebViewFactoryContext.class);
    }
}
