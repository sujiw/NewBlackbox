package black.android.media;

import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BStaticField;

/* JADX INFO: loaded from: classes.jar:black/android/media/MediaRouter.class */
@BClassName("android.media.MediaRouter")
public interface MediaRouter {

    /* JADX INFO: loaded from: classes.jar:black/android/media/MediaRouter$Static.class */
    @BClassName("android.media.MediaRouter$Static")
    public interface Static {
        @BField
        IInterface mAudioService();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/media/MediaRouter$StaticKitkat.class */
    @BClassName("android.media.MediaRouter$Static")
    public interface StaticKitkat {
        @BField
        IInterface mMediaRouterService();
    }

    @BStaticField
    Object sStatic();
}
