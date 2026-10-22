package black.android.app.role;

import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/role/IRoleManagerStubStatic.class */
@BClassNameNotProcess("android.app.role.IRoleManager$Stub")
public interface IRoleManagerStubStatic {
    @BMethodCheckNotProcess
    Method _check_asInterface(IBinder iBinder);

    IInterface asInterface(IBinder iBinder);
}
