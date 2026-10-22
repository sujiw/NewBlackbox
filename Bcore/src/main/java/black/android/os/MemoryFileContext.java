package black.android.os;

import java.io.FileDescriptor;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/MemoryFileContext.class */
@BClassNameNotProcess("android.os.MemoryFile")
public interface MemoryFileContext {
    @BMethodCheckNotProcess
    Method _check_getFileDescriptor();

    FileDescriptor getFileDescriptor();
}
