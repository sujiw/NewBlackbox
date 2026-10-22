package black.android.app.job;

import android.content.Intent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/JobWorkItemContext.class */
@BClassNameNotProcess("android.app.job.JobWorkItem")
public interface JobWorkItemContext {
    @BMethodCheckNotProcess
    Method _check_getIntent();

    Intent getIntent();

    @BFieldSetNotProcess
    void _set_mDeliveryCount(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDeliveryCount();

    @BFieldNotProcess
    Integer mDeliveryCount();

    @BFieldSetNotProcess
    void _set_mGrants(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGrants();

    @BFieldNotProcess
    Object mGrants();

    @BFieldSetNotProcess
    void _set_mWorkId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWorkId();

    @BFieldNotProcess
    Integer mWorkId();
}
