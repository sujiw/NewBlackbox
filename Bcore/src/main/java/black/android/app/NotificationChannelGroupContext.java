package black.android.app;

import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/NotificationChannelGroupContext.class */
@BClassNameNotProcess("android.app.NotificationChannelGroup")
public interface NotificationChannelGroupContext {
    @BFieldSetNotProcess
    void _set_mChannels(Object obj);

    @BFieldCheckNotProcess
    Field _check_mChannels();

    @BFieldNotProcess
    List<android.app.NotificationChannel> mChannels();

    @BFieldSetNotProcess
    void _set_mId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mId();

    @BFieldNotProcess
    String mId();
}
