package black.android.app;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadCreateServiceDataContext.class */
@BClassNameNotProcess("android.app.ActivityThread$CreateServiceData")
public interface ActivityThreadCreateServiceDataContext {
    @BFieldSetNotProcess
    void _set_compatInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_compatInfo();

    @BFieldNotProcess
    Object compatInfo();

    @BFieldSetNotProcess
    void _set_info(Object obj);

    @BFieldCheckNotProcess
    Field _check_info();

    @BFieldNotProcess
    ServiceInfo info();

    @BFieldSetNotProcess
    void _set_intent(Object obj);

    @BFieldCheckNotProcess
    Field _check_intent();

    @BFieldNotProcess
    Intent intent();

    @BFieldSetNotProcess
    void _set_token(Object obj);

    @BFieldCheckNotProcess
    Field _check_token();

    @BFieldNotProcess
    IBinder token();
}
