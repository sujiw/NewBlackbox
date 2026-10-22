package black.android.app;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadAppBindDataContext.class */
@BClassNameNotProcess("android.app.ActivityThread$AppBindData")
public interface ActivityThreadAppBindDataContext {
    @BFieldSetNotProcess
    void _set_appInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_appInfo();

    @BFieldNotProcess
    ApplicationInfo appInfo();

    @BFieldSetNotProcess
    void _set_info(Object obj);

    @BFieldCheckNotProcess
    Field _check_info();

    @BFieldNotProcess
    Object info();

    @BFieldSetNotProcess
    void _set_instrumentationName(Object obj);

    @BFieldCheckNotProcess
    Field _check_instrumentationName();

    @BFieldNotProcess
    ComponentName instrumentationName();

    @BFieldSetNotProcess
    void _set_processName(Object obj);

    @BFieldCheckNotProcess
    Field _check_processName();

    @BFieldNotProcess
    String processName();

    @BFieldSetNotProcess
    void _set_providers(Object obj);

    @BFieldCheckNotProcess
    Field _check_providers();

    @BFieldNotProcess
    List<ProviderInfo> providers();
}
