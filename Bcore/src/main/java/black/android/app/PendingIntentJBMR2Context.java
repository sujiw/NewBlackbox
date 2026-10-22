package black.android.app;

import android.content.Intent;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/PendingIntentJBMR2Context.class */
@BClassNameNotProcess("android.app.PendingIntent")
public interface PendingIntentJBMR2Context {
    @BMethodCheckNotProcess
    Method _check_getIntent();

    Intent getIntent();
}
