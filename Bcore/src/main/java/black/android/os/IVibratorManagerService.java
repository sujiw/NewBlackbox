package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/IVibratorManagerService.class */
@BClassName("android.os.IVibratorManagerService")
public interface IVibratorManagerService {

    /* JADX INFO: loaded from: classes.jar:black/android/os/IVibratorManagerService$Stub.class */
    @BClassName("android.os.IVibratorManagerService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
