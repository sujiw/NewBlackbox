package black.android.os;

import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/BundleContext.class */
@BClassNameNotProcess("android.os.Bundle")
public interface BundleContext {
    @BMethodCheckNotProcess
    Method _check_getIBinder(String str);

    IBinder getIBinder(String str);

    @BMethodCheckNotProcess
    Method _check_putIBinder(String str, IBinder iBinder);

    Void putIBinder(String str, IBinder iBinder);
}
