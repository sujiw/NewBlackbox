package black.android.app;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadProviderClientRecordPContext.class */
@BClassNameNotProcess("android.app.ActivityThread$ProviderClientRecord")
public interface ActivityThreadProviderClientRecordPContext {
    @BFieldSetNotProcess
    void _set_mNames(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNames();

    @BFieldNotProcess
    String[] mNames();

    @BFieldSetNotProcess
    void _set_mProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProvider();

    @BFieldNotProcess
    IInterface mProvider();
}
