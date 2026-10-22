package black.android.app.servertransaction;

import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/ActivityResultItemContext.class */
@BClassNameNotProcess("android.app.servertransaction.ActivityResultItem")
public interface ActivityResultItemContext {
    @BFieldSetNotProcess
    void _set_mResultInfoList(Object obj);

    @BFieldCheckNotProcess
    Field _check_mResultInfoList();

    @BFieldNotProcess
    List mResultInfoList();
}
