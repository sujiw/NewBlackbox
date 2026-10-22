package black.android.os;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/UserHandleStatic.class */
@BClassNameNotProcess("android.os.UserHandle")
public interface UserHandleStatic {
    @BMethodCheckNotProcess
    Method _check_myUserId();

    Integer myUserId();
}
