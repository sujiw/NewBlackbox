package black.android.content.res;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/res/AssetManagerContext.class */
@BClassNameNotProcess("android.content.res.AssetManager")
public interface AssetManagerContext {
    @BMethodCheckNotProcess
    Method _check_addAssetPath(String str);

    Integer addAssetPath(String str);

    @BMethodCheckNotProcess
    Method _check_getConfiguration();

    Configuration getConfiguration();

    @BMethodCheckNotProcess
    Method _check_getDisplayMetrics();

    DisplayMetrics getDisplayMetrics();
}
