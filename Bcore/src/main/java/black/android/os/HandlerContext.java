package black.android.os;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/HandlerContext.class */
@BClassNameNotProcess("android.os.Handler")
public interface HandlerContext {
    @BFieldSetNotProcess
    void _set_mCallback(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCallback();

    @BFieldNotProcess
    android.os.Handler.Callback mCallback();
}
