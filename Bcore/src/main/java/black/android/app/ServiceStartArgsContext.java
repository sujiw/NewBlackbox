package black.android.app;

import android.content.Intent;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ServiceStartArgsContext.class */
@BClassNameNotProcess("android.app.ServiceStartArgs")
public interface ServiceStartArgsContext {
    @BFieldSetNotProcess
    void _set_args(Object obj);

    @BFieldCheckNotProcess
    Field _check_args();

    @BFieldNotProcess
    Intent args();

    @BFieldSetNotProcess
    void _set_flags(Object obj);

    @BFieldCheckNotProcess
    Field _check_flags();

    @BFieldNotProcess
    Integer flags();

    @BFieldSetNotProcess
    void _set_startId(Object obj);

    @BFieldCheckNotProcess
    Field _check_startId();

    @BFieldNotProcess
    Integer startId();

    @BFieldSetNotProcess
    void _set_taskRemoved(Object obj);

    @BFieldCheckNotProcess
    Field _check_taskRemoved();

    @BFieldNotProcess
    Boolean taskRemoved();
}
