package black.android.app;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IApplicationThread.class */
@BClassName("android.app.IApplicationThread")
public interface IApplicationThread {
    @BMethod
    void scheduleBindService(IBinder iBinder, Intent intent, boolean z);

    @BMethod
    void scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo);

    @BMethod
    void scheduleNewIntent(List list, IBinder iBinder);

    @BMethod
    void scheduleServiceArgs(IBinder iBinder, int i, int i2, Intent intent);

    @BMethod
    void scheduleStopService(IBinder iBinder);

    @BMethod
    void scheduleUnbindService(IBinder iBinder, Intent intent);
}
