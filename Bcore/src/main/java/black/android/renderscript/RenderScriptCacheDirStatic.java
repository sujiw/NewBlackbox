package black.android.renderscript;

import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/renderscript/RenderScriptCacheDirStatic.class */
@BClassNameNotProcess("android.renderscript.RenderScriptCacheDir")
public interface RenderScriptCacheDirStatic {
    @BMethodCheckNotProcess
    Method _check_setupDiskCache(File file);

    Void setupDiskCache(File file);
}
