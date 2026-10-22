package black.android.content.pm;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageManagerStatic.class */
@BClassNameNotProcess("android.content.pm.PackageManager")
public interface PackageManagerStatic {
    @BMethodCheckNotProcess
    Method _check_disableApplicationInfoCache();

    Void disableApplicationInfoCache();
}
