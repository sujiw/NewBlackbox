package black.com.android.internal.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/IVibratorService.class */
@BClassName("android.os.IVibratorService")
public interface IVibratorService {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/IVibratorService$Stub.class */
    @BClassName("android.os.IVibratorService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
