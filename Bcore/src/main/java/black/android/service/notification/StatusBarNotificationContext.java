package black.android.service.notification;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/service/notification/StatusBarNotificationContext.class */
@BClassNameNotProcess("android.service.notification.StatusBarNotification")
public interface StatusBarNotificationContext {
    @BFieldSetNotProcess
    void _set_id(Object obj);

    @BFieldCheckNotProcess
    Field _check_id();

    @BFieldNotProcess
    Integer id();

    @BFieldSetNotProcess
    void _set_opPkg(Object obj);

    @BFieldCheckNotProcess
    Field _check_opPkg();

    @BFieldNotProcess
    String opPkg();

    @BFieldSetNotProcess
    void _set_pkg(Object obj);

    @BFieldCheckNotProcess
    Field _check_pkg();

    @BFieldNotProcess
    String pkg();

    @BFieldSetNotProcess
    void _set_tag(Object obj);

    @BFieldCheckNotProcess
    Field _check_tag();

    @BFieldNotProcess
    String tag();
}
