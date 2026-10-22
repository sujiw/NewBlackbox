package black.com.android.internal.app;

import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/ISmtOpsServiceStubStatic.class */
@BClassNameNotProcess("com.android.internal.app.ISmtOpsService$Stub")
public interface ISmtOpsServiceStubStatic {
    @BMethodCheckNotProcess
    Method _check_asInterface(IBinder iBinder);

    IInterface asInterface(IBinder iBinder);
}
