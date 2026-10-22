package black.android.webkit;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/webkit/IWebViewUpdateServiceContext.class */
@BClassNameNotProcess("android.webkit.IWebViewUpdateService")
public interface IWebViewUpdateServiceContext {
    @BMethodCheckNotProcess
    Method _check_getCurrentWebViewPackageName();

    String getCurrentWebViewPackageName();

    @BMethodCheckNotProcess
    Method _check_waitForAndGetProvider();

    Object waitForAndGetProvider();
}
