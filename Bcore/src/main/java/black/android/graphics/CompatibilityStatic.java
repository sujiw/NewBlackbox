package black.android.graphics;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/graphics/CompatibilityStatic.class */
@BClassNameNotProcess("android.graphics.Compatibility")
public interface CompatibilityStatic {
    @BMethodCheckNotProcess
    Method _check_setTargetSdkVersion(int i);

    Void setTargetSdkVersion(int i);
}
