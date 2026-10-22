package black.android.providers;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/providers/SettingsSystemStatic.class */
@BClassNameNotProcess("android.provider.Settings$System")
public interface SettingsSystemStatic {
    @BFieldSetNotProcess
    void _set_sNameValueCache(Object obj);

    @BFieldCheckNotProcess
    Field _check_sNameValueCache();

    @BFieldNotProcess
    Object sNameValueCache();
}
