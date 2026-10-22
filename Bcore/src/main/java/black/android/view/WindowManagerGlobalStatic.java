package black.android.view;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/view/WindowManagerGlobalStatic.class */
@BClassNameNotProcess("android.view.WindowManagerGlobal")
public interface WindowManagerGlobalStatic {
    @BFieldSetNotProcess
    void _set_ADD_PERMISSION_DENIED(Object obj);

    @BFieldCheckNotProcess
    Field _check_ADD_PERMISSION_DENIED();

    @BFieldNotProcess
    Integer ADD_PERMISSION_DENIED();

    @BFieldSetNotProcess
    void _set_sWindowManagerService(Object obj);

    @BFieldCheckNotProcess
    Field _check_sWindowManagerService();

    @BFieldNotProcess
    IInterface sWindowManagerService();
}
