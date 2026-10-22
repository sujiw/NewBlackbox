package black.android.app;

import android.app.IServiceConnection;
import android.content.ComponentName;
import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/IServiceConnectionOContext.class */
@BClassNameNotProcess(IServiceConnection.Stub.DESCRIPTOR)
public interface IServiceConnectionOContext {
    @BMethodCheckNotProcess
    Method _check_connected(ComponentName componentName, IBinder iBinder, boolean z);

    Void connected(ComponentName componentName, IBinder iBinder, boolean z);
}
