package black.java.lang;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/java/lang/ThreadGroupNContext.class */
@BClassNameNotProcess("java.lang.ThreadGroup")
public interface ThreadGroupNContext {
    @BFieldSetNotProcess
    void _set_groups(Object obj);

    @BFieldCheckNotProcess
    Field _check_groups();

    @BFieldNotProcess
    java.lang.ThreadGroup[] groups();

    @BFieldSetNotProcess
    void _set_ngroups(Object obj);

    @BFieldCheckNotProcess
    Field _check_ngroups();

    @BFieldNotProcess
    Integer ngroups();

    @BFieldSetNotProcess
    void _set_parent(Object obj);

    @BFieldCheckNotProcess
    Field _check_parent();

    @BFieldNotProcess
    java.lang.ThreadGroup parent();
}
