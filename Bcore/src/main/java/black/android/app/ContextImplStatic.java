package black.android.app;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ContextImplStatic.class */
@BClassNameNotProcess("android.app.ContextImpl")
public interface ContextImplStatic {
    @BMethodCheckNotProcess
    Method _check_createAppContext();

    Object createAppContext();
}
