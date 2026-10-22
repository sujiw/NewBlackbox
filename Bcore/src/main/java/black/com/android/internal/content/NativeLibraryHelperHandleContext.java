package black.com.android.internal.content;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/NativeLibraryHelperHandleContext.class */
@BClassNameNotProcess("com.android.internal.content.NativeLibraryHelper$Handle")
public interface NativeLibraryHelperHandleContext {
    @BFieldSetNotProcess
    void _set_extractNativeLibs(Object obj);

    @BFieldCheckNotProcess
    Field _check_extractNativeLibs();

    @BFieldNotProcess
    Boolean extractNativeLibs();
}
