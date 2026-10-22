package black.android.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityTaskManagerStatic.class */
@BClassNameNotProcess("android.app.ActivityTaskManager")
public interface ActivityTaskManagerStatic {
    @BMethodCheckNotProcess
    Method _check_getService();

    Object getService();

    @BFieldSetNotProcess
    void _set_IActivityTaskManagerSingleton(Object obj);

    @BFieldCheckNotProcess
    Field _check_IActivityTaskManagerSingleton();

    @BFieldNotProcess
    Object IActivityTaskManagerSingleton();
}
