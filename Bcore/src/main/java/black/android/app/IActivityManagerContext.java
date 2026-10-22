package black.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BParamClassName;

/* JADX INFO: loaded from: classes.jar:black/android/app/IActivityManagerContext.class */
@BClassNameNotProcess("android.app.IActivityManager")
public interface IActivityManagerContext {
    @BMethodCheckNotProcess
    Method _check_getTaskForActivity(IBinder iBinder, boolean z);

    Integer getTaskForActivity(IBinder iBinder, boolean z);

    @BMethodCheckNotProcess
    Method _check_overridePendingTransition(IBinder iBinder, String str, int i, int i2);

    Void overridePendingTransition(IBinder iBinder, String str, int i, int i2);

    @BMethodCheckNotProcess
    Method _check_setRequestedOrientation(IBinder iBinder, int i);

    Void setRequestedOrientation(IBinder iBinder, int i);

    @BMethodCheckNotProcess
    Method _check_startActivities();

    Integer startActivities();

    @BMethodCheckNotProcess
    Method _check_startActivity(@BParamClassName("android.app.IApplicationThread") Object obj, String str, Intent intent, String str2, IBinder iBinder, String str3, int i, int i2, @BParamClassName("android.app.ProfilerInfo") Object obj2, Bundle bundle);

    Integer startActivity(@BParamClassName("android.app.IApplicationThread") Object obj, String str, Intent intent, String str2, IBinder iBinder, String str3, int i, int i2, @BParamClassName("android.app.ProfilerInfo") Object obj2, Bundle bundle);
}
