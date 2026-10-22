package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadHStatic.class */
@BClassNameNotProcess("android.app.ActivityThread$H")
public interface ActivityThreadHStatic {
    @BFieldSetNotProcess
    void _set_CREATE_SERVICE(Object obj);

    @BFieldCheckNotProcess
    Field _check_CREATE_SERVICE();

    @BFieldNotProcess
    Integer CREATE_SERVICE();

    @BFieldSetNotProcess
    void _set_EXECUTE_TRANSACTION(Object obj);

    @BFieldCheckNotProcess
    Field _check_EXECUTE_TRANSACTION();

    @BFieldNotProcess
    Integer EXECUTE_TRANSACTION();

    @BFieldSetNotProcess
    void _set_LAUNCH_ACTIVITY(Object obj);

    @BFieldCheckNotProcess
    Field _check_LAUNCH_ACTIVITY();

    @BFieldNotProcess
    Integer LAUNCH_ACTIVITY();

    @BFieldSetNotProcess
    void _set_SCHEDULE_CRASH(Object obj);

    @BFieldCheckNotProcess
    Field _check_SCHEDULE_CRASH();

    @BFieldNotProcess
    Integer SCHEDULE_CRASH();
}
