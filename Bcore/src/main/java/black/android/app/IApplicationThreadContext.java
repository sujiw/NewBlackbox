package black.android.app;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/IApplicationThreadContext.class */
@BClassNameNotProcess("android.app.IApplicationThread")
public interface IApplicationThreadContext {
    @BMethodCheckNotProcess
    Method _check_scheduleBindService(IBinder iBinder, Intent intent, boolean z);

    Void scheduleBindService(IBinder iBinder, Intent intent, boolean z);

    @BMethodCheckNotProcess
    Method _check_scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo);

    Void scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo);

    @BMethodCheckNotProcess
    Method _check_scheduleNewIntent(List list, IBinder iBinder);

    Void scheduleNewIntent(List list, IBinder iBinder);

    @BMethodCheckNotProcess
    Method _check_scheduleServiceArgs(IBinder iBinder, int i, int i2, Intent intent);

    Void scheduleServiceArgs(IBinder iBinder, int i, int i2, Intent intent);

    @BMethodCheckNotProcess
    Method _check_scheduleStopService(IBinder iBinder);

    Void scheduleStopService(IBinder iBinder);

    @BMethodCheckNotProcess
    Method _check_scheduleUnbindService(IBinder iBinder, Intent intent);

    Void scheduleUnbindService(IBinder iBinder, Intent intent);
}
