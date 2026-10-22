package black.android.hardware.display;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/hardware/display/DisplayManagerGlobalContext.class */
@BClassNameNotProcess("android.hardware.display.DisplayManagerGlobal")
public interface DisplayManagerGlobalContext {
    @BFieldSetNotProcess
    void _set_mDm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDm();

    @BFieldNotProcess
    IInterface mDm();
}
