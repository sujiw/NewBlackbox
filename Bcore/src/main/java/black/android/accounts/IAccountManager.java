package black.android.accounts;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/accounts/IAccountManager.class */
@BClassName("android.accounts.IAccountManager")
public interface IAccountManager {

    /* JADX INFO: loaded from: classes.jar:black/android/accounts/IAccountManager$Stub.class */
    @BClassName("android.accounts.IAccountManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
