package black.com.android.internal.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/IDropBoxManagerService.class */
@BClassName("com.android.internal.os.IDropBoxManagerService")
public interface IDropBoxManagerService {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/os/IDropBoxManagerService$Stub.class */
    @BClassName("com.android.internal.os.IDropBoxManagerService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
