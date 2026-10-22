package black.android.app;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadProviderClientRecordJBContext.class */
@BClassNameNotProcess("android.app.ActivityThread$ProviderClientRecord")
public interface ActivityThreadProviderClientRecordJBContext {
    @BFieldSetNotProcess
    void _set_mHolder(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHolder();

    @BFieldNotProcess
    Object mHolder();

    @BFieldSetNotProcess
    void _set_mProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProvider();

    @BFieldNotProcess
    IInterface mProvider();
}
