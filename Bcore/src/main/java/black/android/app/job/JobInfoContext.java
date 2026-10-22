package black.android.app.job;

import android.content.ComponentName;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/JobInfoContext.class */
@BClassNameNotProcess("android.app.job.JobInfo")
public interface JobInfoContext {
    @BFieldSetNotProcess
    void _set_flexMillis(Object obj);

    @BFieldCheckNotProcess
    Field _check_flexMillis();

    @BFieldNotProcess
    Long flexMillis();

    @BFieldSetNotProcess
    void _set_intervalMillis(Object obj);

    @BFieldCheckNotProcess
    Field _check_intervalMillis();

    @BFieldNotProcess
    Long intervalMillis();

    @BFieldSetNotProcess
    void _set_jobId(Object obj);

    @BFieldCheckNotProcess
    Field _check_jobId();

    @BFieldNotProcess
    Integer jobId();

    @BFieldSetNotProcess
    void _set_service(Object obj);

    @BFieldCheckNotProcess
    Field _check_service();

    @BFieldNotProcess
    ComponentName service();
}
