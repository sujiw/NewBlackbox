package black.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/Instrumentation.class */
@BClassName("android.app.Instrumentation")
public interface Instrumentation {
    @BMethod
    android.app.Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle);
}
