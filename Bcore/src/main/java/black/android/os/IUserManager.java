package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/IUserManager.class */
@BClassName("android.os.IUserManager")
public interface IUserManager {

    /* JADX INFO: loaded from: classes.jar:black/android/os/IUserManager$Stub.class */
    @BClassName("android.os.IUserManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
