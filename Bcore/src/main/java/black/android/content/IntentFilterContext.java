package black.android.content;

import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/IntentFilterContext.class */
@BClassNameNotProcess("android.content.IntentFilter")
public interface IntentFilterContext {
    @BFieldSetNotProcess
    void _set_mActions(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActions();

    @BFieldNotProcess
    List<String> mActions();

    @BFieldSetNotProcess
    void _set_mCategories(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCategories();

    @BFieldNotProcess
    List<String> mCategories();
}
