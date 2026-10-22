package black.android.app;

import android.content.pm.ProviderInfo;
import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/IActivityManagerContentProviderHolderMIUIContext.class */
@BClassNameNotProcess("android.app.IActivityManager$ContentProviderHolder")
public interface IActivityManagerContentProviderHolderMIUIContext {
    @BFieldSetNotProcess
    void _set_info(Object obj);

    @BFieldCheckNotProcess
    Field _check_info();

    @BFieldNotProcess
    ProviderInfo info();

    @BFieldSetNotProcess
    void _set_noReleaseNeeded(Object obj);

    @BFieldCheckNotProcess
    Field _check_noReleaseNeeded();

    @BFieldNotProcess
    Boolean noReleaseNeeded();

    @BFieldSetNotProcess
    void _set_provider(Object obj);

    @BFieldCheckNotProcess
    Field _check_provider();

    @BFieldNotProcess
    IInterface provider();

    @BFieldSetNotProcess
    void _set_waitProcessStart(Object obj);

    @BFieldCheckNotProcess
    Field _check_waitProcessStart();

    @BFieldNotProcess
    Boolean waitProcessStart();
}
