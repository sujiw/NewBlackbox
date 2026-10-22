package black.android.os;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/ProcessStatic.class */
@BClassNameNotProcess("android.os.Process")
public interface ProcessStatic {
    @BMethodCheckNotProcess
    Method _check_setArgV0(String str);

    Void setArgV0(String str);
}
