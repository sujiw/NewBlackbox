package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/WallpaperManagerGlobalsContext.class */
@BClassNameNotProcess("android.app.WallpaperManager$Globals")
public interface WallpaperManagerGlobalsContext {
    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    Object mService();
}
