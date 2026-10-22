package black.android.view;

import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/view/ThreadedRendererStatic.class */
@BClassNameNotProcess("android.view.ThreadedRenderer")
public interface ThreadedRendererStatic {
    @BMethodCheckNotProcess
    Method _check_setupDiskCache(File file);

    Void setupDiskCache(File file);
}
