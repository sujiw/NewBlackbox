package black.com.android.internal.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/UserManager.class */
@BClassName("android.os.UserManager")
public interface UserManager {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/UserManager$Stub.class */
    @BClassName("android.os.IUserManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
