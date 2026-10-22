package black.android.permission;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/permission/IPermissionManager.class */
@BClassName("android.permission.IPermissionManager")
public interface IPermissionManager {

    /* JADX INFO: loaded from: classes.jar:black/android/permission/IPermissionManager$Stub.class */
    @BClassName("android.permission.IPermissionManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
