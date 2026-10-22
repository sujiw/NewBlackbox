package black.android.content.pm;

import android.content.pm.ActivityInfo;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserActivityContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser$Activity")
public interface PackageParserActivityContext {
    @BFieldSetNotProcess
    void _set_info(Object obj);

    @BFieldCheckNotProcess
    Field _check_info();

    @BFieldNotProcess
    ActivityInfo info();
}
