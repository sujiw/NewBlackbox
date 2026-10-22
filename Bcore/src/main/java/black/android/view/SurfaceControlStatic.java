package black.android.view;

import android.graphics.Bitmap;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/view/SurfaceControlStatic.class */
@BClassNameNotProcess("android.view.SurfaceControl")
public interface SurfaceControlStatic {
    @BMethodCheckNotProcess
    Method _check_screnshot(int i, int i2);

    Bitmap screnshot(int i, int i2);
}
