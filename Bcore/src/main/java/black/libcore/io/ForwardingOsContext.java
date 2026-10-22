package black.libcore.io;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/libcore/io/ForwardingOsContext.class */
@BClassNameNotProcess("libcore.io.ForwardingOs")
public interface ForwardingOsContext {
    @BFieldSetNotProcess
    void _set_os(Object obj);

    @BFieldCheckNotProcess
    Field _check_os();

    @BFieldNotProcess
    Object os();
}
