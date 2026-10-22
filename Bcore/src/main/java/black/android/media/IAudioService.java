package black.android.media;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/media/IAudioService.class */
@BClassName("android.media.IAudioService")
public interface IAudioService {

    /* JADX INFO: loaded from: classes.jar:black/android/media/IAudioService$Stub.class */
    @BClassName("android.media.IAudioService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
