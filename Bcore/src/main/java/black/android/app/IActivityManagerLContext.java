package black.android.app;

import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/IActivityManagerLContext.class */
@BClassNameNotProcess("android.app.IActivityManager")
public interface IActivityManagerLContext {
    @BMethodCheckNotProcess
    Method _check_finishActivity(IBinder iBinder, int i, Intent intent, boolean z);

    Boolean finishActivity(IBinder iBinder, int i, Intent intent, boolean z);
}
