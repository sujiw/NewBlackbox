package black.android.app.servertransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/LaunchActivityItemContext.class */
@BClassNameNotProcess("android.app.servertransaction.LaunchActivityItem")
public interface LaunchActivityItemContext {
    @BFieldSetNotProcess
    void _set_mInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_mInfo();

    @BFieldNotProcess
    ActivityInfo mInfo();

    @BFieldSetNotProcess
    void _set_mIntent(Object obj);

    @BFieldCheckNotProcess
    Field _check_mIntent();

    @BFieldNotProcess
    Intent mIntent();
}
