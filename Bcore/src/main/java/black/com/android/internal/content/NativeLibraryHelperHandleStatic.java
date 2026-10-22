package black.com.android.internal.content;

import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/NativeLibraryHelperHandleStatic.class */
@BClassNameNotProcess("com.android.internal.content.NativeLibraryHelper$Handle")
public interface NativeLibraryHelperHandleStatic {
    @BMethodCheckNotProcess
    Method _check_create(File file);

    Object create(File file);
}
