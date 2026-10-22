package black.android.content;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/ContentResolverContext.class */
@BClassNameNotProcess("android.content.ContentResolver")
public interface ContentResolverContext {
    @BFieldSetNotProcess
    void _set_mPackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackageName();

    @BFieldNotProcess
    String mPackageName();
}
