package black.android.content;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/PeriodicSyncContext.class */
@BClassNameNotProcess("android.content.PeriodicSync")
public interface PeriodicSyncContext {
    @BFieldSetNotProcess
    void _set_flexTime(Object obj);

    @BFieldCheckNotProcess
    Field _check_flexTime();

    @BFieldNotProcess
    Long flexTime();
}
