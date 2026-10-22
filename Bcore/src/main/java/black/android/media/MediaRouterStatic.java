package black.android.media;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/media/MediaRouterStatic.class */
@BClassNameNotProcess("android.media.MediaRouter")
public interface MediaRouterStatic {
    @BFieldSetNotProcess
    void _set_sStatic(Object obj);

    @BFieldCheckNotProcess
    Field _check_sStatic();

    @BFieldNotProcess
    Object sStatic();
}
