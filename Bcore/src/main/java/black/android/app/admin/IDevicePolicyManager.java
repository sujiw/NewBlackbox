package black.android.app.admin;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/admin/IDevicePolicyManager.class */
@BClassName("android.app.admin.IDevicePolicyManager")
public interface IDevicePolicyManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/admin/IDevicePolicyManager$Stub.class */
    @BClassName("android.app.admin.IDevicePolicyManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
