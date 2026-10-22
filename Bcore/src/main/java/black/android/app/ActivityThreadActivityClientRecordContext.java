package black.android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadActivityClientRecordContext.class */
@BClassNameNotProcess("android.app.ActivityThread$ActivityClientRecord")
public interface ActivityThreadActivityClientRecordContext {
    @BMethodCheckNotProcess
    Method _check_toString();

    String toString();

    @BFieldSetNotProcess
    void _set_activity(Object obj);

    @BFieldCheckNotProcess
    Field _check_activity();

    @BFieldNotProcess
    android.app.Activity activity();

    @BFieldSetNotProcess
    void _set_activityInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_activityInfo();

    @BFieldNotProcess
    ActivityInfo activityInfo();

    @BFieldSetNotProcess
    void _set_intent(Object obj);

    @BFieldCheckNotProcess
    Field _check_intent();

    @BFieldNotProcess
    Intent intent();

    @BFieldSetNotProcess
    void _set_isTopResumedActivity(Object obj);

    @BFieldCheckNotProcess
    Field _check_isTopResumedActivity();

    @BFieldNotProcess
    Boolean isTopResumedActivity();

    @BFieldSetNotProcess
    void _set_token(Object obj);

    @BFieldCheckNotProcess
    Field _check_token();

    @BFieldNotProcess
    IBinder token();

    @BFieldSetNotProcess
    void _set_packageInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_packageInfo();

    @BFieldNotProcess
    Object packageInfo();

    @BFieldSetNotProcess
    void _set_window(Object obj);

    @BFieldCheckNotProcess
    Field _check_window();

    @BFieldNotProcess
    Window window();

    @BFieldSetNotProcess
    void _set_mPreserveWindow(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPreserveWindow();

    @BFieldNotProcess
    Boolean mPreserveWindow();
}
