package black.android.media.session;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/media/session/ISessionManager.class */
@BClassName("android.media.session.ISessionManager")
public interface ISessionManager {

    /* JADX INFO: loaded from: classes.jar:black/android/media/session/ISessionManager$Stub.class */
    @BClassName("android.media.session.ISessionManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
