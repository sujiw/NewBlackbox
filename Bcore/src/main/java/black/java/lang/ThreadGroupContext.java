package black.java.lang;

import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/java/lang/ThreadGroupContext.class */
@BClassNameNotProcess("java.lang.ThreadGroup")
public interface ThreadGroupContext {
    @BFieldSetNotProcess
    void _set_groups(Object obj);

    @BFieldCheckNotProcess
    Field _check_groups();

    @BFieldNotProcess
    List<java.lang.ThreadGroup> groups();

    @BFieldSetNotProcess
    void _set_parent(Object obj);

    @BFieldCheckNotProcess
    Field _check_parent();

    @BFieldNotProcess
    java.lang.ThreadGroup parent();
}
