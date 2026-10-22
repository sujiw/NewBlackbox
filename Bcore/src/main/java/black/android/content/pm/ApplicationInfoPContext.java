package black.android.content.pm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ApplicationInfoPContext.class */
@BClassNameNotProcess("android.content.pm.ApplicationInfo")
public interface ApplicationInfoPContext {
    @BMethodCheckNotProcess
    Method _check_setHiddenApiEnforcementPolicy(int i);

    Void setHiddenApiEnforcementPolicy(int i);

    @BFieldSetNotProcess
    void _set_splitClassLoaderNames(Object obj);

    @BFieldCheckNotProcess
    Field _check_splitClassLoaderNames();

    @BFieldNotProcess
    String[] splitClassLoaderNames();
}
