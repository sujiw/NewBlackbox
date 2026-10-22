package black.com.android.internal;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/RlayoutStatic.class */
@BClassNameNotProcess("com.android.internal.R$layout")
public interface RlayoutStatic {
    @BFieldSetNotProcess
    void _set_resolver_list(Object obj);

    @BFieldCheckNotProcess
    Field _check_resolver_list();

    @BFieldNotProcess
    Integer resolver_list();
}
