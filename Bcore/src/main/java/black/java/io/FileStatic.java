package black.java.io;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/java/io/FileStatic.class */
@BClassNameNotProcess("java.io.File")
public interface FileStatic {
    @BFieldSetNotProcess
    void _set_fs(Object obj);

    @BFieldCheckNotProcess
    Field _check_fs();

    @BFieldNotProcess
    Object fs();
}
