package black.android.media;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/media/MediaRouterStaticKitkatContext.class */
@BClassNameNotProcess("android.media.MediaRouter$Static")
public interface MediaRouterStaticKitkatContext {
    @BFieldSetNotProcess
    void _set_mMediaRouterService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mMediaRouterService();

    @BFieldNotProcess
    IInterface mMediaRouterService();
}
