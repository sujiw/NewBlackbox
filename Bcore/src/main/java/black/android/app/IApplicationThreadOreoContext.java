package black.android.app;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/IApplicationThreadOreoContext.class */
@BClassNameNotProcess("android.app.IApplicationThread")
public interface IApplicationThreadOreoContext {
    @BMethodCheckNotProcess
    Method _check_scheduleServiceArgs();

    Void scheduleServiceArgs();
}
