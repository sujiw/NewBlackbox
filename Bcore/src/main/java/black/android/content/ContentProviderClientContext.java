package black.android.content;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/ContentProviderClientContext.class */
@BClassNameNotProcess("android.content.ContentProviderClient")
public interface ContentProviderClientContext {
    @BFieldSetNotProcess
    void _set_mContentProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mContentProvider();

    @BFieldNotProcess
    IInterface mContentProvider();
}
