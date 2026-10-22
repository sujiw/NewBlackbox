package black.android.ddm;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/ddm/DdmHandleAppNameStatic.class */
@BClassNameNotProcess("android.ddm.DdmHandleAppName")
public interface DdmHandleAppNameStatic {
    @BMethodCheckNotProcess
    Method _check_setAppName(String str, int i);

    Void setAppName(String str, int i);
}
