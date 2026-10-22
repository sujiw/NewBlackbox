package black.android.app.backup;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/backup/IBackupManager.class */
@BClassName("android.app.backup.IBackupManager")
public interface IBackupManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/backup/IBackupManager$Stub.class */
    @BClassName("android.app.backup.IBackupManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
