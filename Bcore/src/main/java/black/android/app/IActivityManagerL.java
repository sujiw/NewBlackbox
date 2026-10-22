package black.android.app;

import android.content.Intent;
import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IActivityManagerL.class */
@BClassName("android.app.IActivityManager")
public interface IActivityManagerL {
    @BMethod
    Boolean finishActivity(IBinder iBinder, int i, Intent intent, boolean z);
}
