package black.android.providers;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/providers/SettingsContentProviderHolderContext.class */
@BClassNameNotProcess("android.provider.Settings$ContentProviderHolder")
public interface SettingsContentProviderHolderContext {
    @BFieldSetNotProcess
    void _set_mContentProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mContentProvider();

    @BFieldNotProcess
    IInterface mContentProvider();
}
