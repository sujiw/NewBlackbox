package black.android.app;

import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityThreadNMR1Context.class */
@BClassNameNotProcess("android.app.ActivityThread")
public interface ActivityThreadNMR1Context {
    @BMethodCheckNotProcess
    Method _check_performNewIntents(IBinder iBinder, List list, boolean z);

    Void performNewIntents(IBinder iBinder, List list, boolean z);
}
