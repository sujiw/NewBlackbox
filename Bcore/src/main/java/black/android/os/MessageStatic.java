package black.android.os;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/MessageStatic.class */
@BClassNameNotProcess("android.os.Message")
public interface MessageStatic {
    @BMethodCheckNotProcess
    Method _check_updateCheckRecycle(int i);

    Void updateCheckRecycle(int i);
}
