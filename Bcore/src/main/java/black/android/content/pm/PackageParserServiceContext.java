package black.android.content.pm;

import android.content.pm.ServiceInfo;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserServiceContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser$Service")
public interface PackageParserServiceContext {
    @BFieldSetNotProcess
    void _set_info(Object obj);

    @BFieldCheckNotProcess
    Field _check_info();

    @BFieldNotProcess
    ServiceInfo info();
}
