package black.android.app;

import android.content.Intent;
import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/PendingIntentJBMR2.class */
@BClassName("android.app.PendingIntent")
public interface PendingIntentJBMR2 {
    @BConstructor
    PendingIntentJBMR2 _new(IBinder iBinder);

    @BMethod
    Intent getIntent();
}
