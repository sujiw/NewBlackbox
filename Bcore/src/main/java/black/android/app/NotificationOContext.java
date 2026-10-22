package black.android.app;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationOContext.class */
@BClassNameNotProcess("android.app.Notification")
public interface NotificationOContext {
    @BFieldSetNotProcess
    void _set_mChannelId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mChannelId();

    @BFieldNotProcess
    String mChannelId();

    @BFieldSetNotProcess
    void _set_mGroupKey(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGroupKey();

    @BFieldNotProcess
    String mGroupKey();
}
