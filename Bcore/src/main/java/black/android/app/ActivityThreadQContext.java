package black.android.app;

import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadQContext.class */
@BClassNameNotProcess("android.app.ActivityThread")
public interface ActivityThreadQContext {
    @BMethodCheckNotProcess
    Method _check_handleNewIntent(IBinder iBinder, List list);

    Void handleNewIntent(IBinder iBinder, List list);
}
