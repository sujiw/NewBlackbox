package black.dalvik.system;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/dalvik/system/VMRuntimeContext.class */
@BClassNameNotProcess("dalvik.system.VMRuntime")
public interface VMRuntimeContext {
    @BMethodCheckNotProcess
    Method _check_is64Bit();

    Boolean is64Bit();

    @BMethodCheckNotProcess
    Method _check_isJavaDebuggable();

    Boolean isJavaDebuggable();

    @BMethodCheckNotProcess
    Method _check_setTargetSdkVersion(int i);

    Void setTargetSdkVersion(int i);
}
