package black.android.os;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/DropBoxManagerContext.class */
@BClassNameNotProcess("android.os.DropBoxManager")
public interface DropBoxManagerContext {
    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    IInterface mService();
}
