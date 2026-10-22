package black.android.app;

import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ClientTransactionHandlerContext.class */
@BClassNameNotProcess("android.app.ClientTransactionHandler")
public interface ClientTransactionHandlerContext {
    @BMethodCheckNotProcess
    Method _check_getActivityClient(IBinder iBinder);

    Object getActivityClient(IBinder iBinder);
}
