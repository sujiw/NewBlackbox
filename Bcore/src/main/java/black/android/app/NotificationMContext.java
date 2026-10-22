package black.android.app;

import android.graphics.drawable.Icon;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationMContext.class */
@BClassNameNotProcess("android.app.Notification")
public interface NotificationMContext {
    @BFieldSetNotProcess
    void _set_mLargeIcon(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLargeIcon();

    @BFieldNotProcess
    Icon mLargeIcon();

    @BFieldSetNotProcess
    void _set_mSmallIcon(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSmallIcon();

    @BFieldNotProcess
    Icon mSmallIcon();
}
