package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationChannelContext.class */
@BClassNameNotProcess("android.app.NotificationChannel")
public interface NotificationChannelContext {
    @BFieldSetNotProcess
    void _set_mId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mId();

    @BFieldNotProcess
    String mId();
}
