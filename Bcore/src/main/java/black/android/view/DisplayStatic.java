package black.android.view;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/view/DisplayStatic.class */
@BClassNameNotProcess("android.view.Display")
public interface DisplayStatic {
    @BFieldSetNotProcess
    void _set_sWindowManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_sWindowManager();

    @BFieldNotProcess
    IInterface sWindowManager();
}
