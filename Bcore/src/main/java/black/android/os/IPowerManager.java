package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/IPowerManager.class */
@BClassName("android.os.IPowerManager")
public interface IPowerManager {

    /* JADX INFO: loaded from: classes.jar:black/android/os/IPowerManager$Stub.class */
    @BClassName("android.os.IPowerManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
