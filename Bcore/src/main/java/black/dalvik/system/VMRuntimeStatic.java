package black.dalvik.system;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/dalvik/system/VMRuntimeStatic.class */
@BClassNameNotProcess("dalvik.system.VMRuntime")
public interface VMRuntimeStatic {
    @BMethodCheckNotProcess
    Method _check_getCurrentInstructionSet();

    String getCurrentInstructionSet();

    @BMethodCheckNotProcess
    Method _check_getRuntime();

    Object getRuntime();

    @BMethodCheckNotProcess
    Method _check_is64BitAbi(String str);

    Boolean is64BitAbi(String str);
}
