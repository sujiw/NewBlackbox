package black.android.webkit;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/webkit/BRIWebViewUpdateService.class */
public class BRIWebViewUpdateService {
    public static IWebViewUpdateServiceStatic getWithException() {
        return (IWebViewUpdateServiceStatic) BlackReflection.create(IWebViewUpdateServiceStatic.class, (Object) null, true);
    }

    public static IWebViewUpdateServiceStatic get() {
        return (IWebViewUpdateServiceStatic) BlackReflection.create(IWebViewUpdateServiceStatic.class, (Object) null, false);
    }

    public static IWebViewUpdateServiceContext getWithException(Object caller) {
        return (IWebViewUpdateServiceContext) BlackReflection.create(IWebViewUpdateServiceContext.class, caller, true);
    }

    public static IWebViewUpdateServiceContext get(Object caller) {
        return (IWebViewUpdateServiceContext) BlackReflection.create(IWebViewUpdateServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWebViewUpdateServiceContext.class);
    }
}
