package black.android.app;

import android.app.IServiceConnection;
import android.content.ComponentName;
import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IServiceConnectionO.class */
@BClassName(IServiceConnection.Stub.DESCRIPTOR)
public interface IServiceConnectionO {
    @BMethod
    void connected(ComponentName componentName, IBinder iBinder, boolean z);
}
