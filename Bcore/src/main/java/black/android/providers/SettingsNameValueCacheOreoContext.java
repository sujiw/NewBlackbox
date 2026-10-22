package black.android.providers;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/providers/SettingsNameValueCacheOreoContext.class */
@BClassNameNotProcess("android.provider.Settings$NameValueCache")
public interface SettingsNameValueCacheOreoContext {
    @BFieldSetNotProcess
    void _set_mProviderHolder(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProviderHolder();

    @BFieldNotProcess
    Object mProviderHolder();
}
