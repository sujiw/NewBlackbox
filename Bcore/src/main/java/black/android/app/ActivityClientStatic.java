package black.android.app;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityClientStatic.class */
@BClassNameNotProcess("android.app.ActivityClient")
public interface ActivityClientStatic {
    @BMethodCheckNotProcess
    Method _check_getInstance();

    Object getInstance();

    @BMethodCheckNotProcess
    Method _check_getActivityClientController();

    Object getActivityClientController();

    @BMethodCheckNotProcess
    Method _check_setActivityClientController(Object obj);

    Object setActivityClientController(Object obj);
}
