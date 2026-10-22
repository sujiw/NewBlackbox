package black.android.app.usage;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/usage/IStorageStatsManager.class */
@BClassName("android.app.usage.IStorageStatsManager")
public interface IStorageStatsManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/usage/IStorageStatsManager$Stub.class */
    @BClassName("android.app.usage.IStorageStatsManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
