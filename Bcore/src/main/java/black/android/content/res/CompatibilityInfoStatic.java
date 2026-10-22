package black.android.content.res;

import android.content.pm.ApplicationInfo;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BConstructorNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/res/CompatibilityInfoStatic.class */
@BClassNameNotProcess("android.content.res.CompatibilityInfo")
public interface CompatibilityInfoStatic {
    @BFieldSetNotProcess
    void _set_DEFAULT_COMPATIBILITY_INFO(Object obj);

    @BFieldCheckNotProcess
    Field _check_DEFAULT_COMPATIBILITY_INFO();

    @BFieldNotProcess
    Object DEFAULT_COMPATIBILITY_INFO();

    @BConstructorNotProcess
    CompatibilityInfo _new(ApplicationInfo applicationInfo, int i, int i2, boolean z);

    @BConstructorNotProcess
    CompatibilityInfo _new(ApplicationInfo applicationInfo, int i, int i2, boolean z, int i3);
}
