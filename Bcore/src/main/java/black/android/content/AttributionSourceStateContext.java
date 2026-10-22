package black.android.content;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/AttributionSourceStateContext.class */
@BClassNameNotProcess("android.content.AttributionSourceState")
public interface AttributionSourceStateContext {
    @BFieldSetNotProcess
    void _set_packageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_packageName();

    @BFieldNotProcess
    String packageName();

    @BFieldSetNotProcess
    void _set_uid(Object obj);

    @BFieldCheckNotProcess
    Field _check_uid();

    @BFieldNotProcess
    Integer uid();
}
