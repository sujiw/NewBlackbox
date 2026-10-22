package black.android.os.storage;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/IStorageManager.class */
@BClassName("android.os.storage.IStorageManager")
public interface IStorageManager {

    /* JADX INFO: loaded from: classes.jar:black/android/os/storage/IStorageManager$Stub.class */
    @BClassName("android.os.storage.IStorageManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
