package black.android.app;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadProviderClientRecordContext.class */
@BClassNameNotProcess("android.app.ActivityThread$ProviderClientRecord")
public interface ActivityThreadProviderClientRecordContext {
    @BFieldSetNotProcess
    void _set_mName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mName();

    @BFieldNotProcess
    String mName();

    @BFieldSetNotProcess
    void _set_mProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProvider();

    @BFieldNotProcess
    IInterface mProvider();
}
