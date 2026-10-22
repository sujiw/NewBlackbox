package black.android.app.servertransaction;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/ActivityLifecycleItemContext.class */
@BClassNameNotProcess("android.app.servertransaction.ActivityLifecycleItem")
public interface ActivityLifecycleItemContext {
    @BMethodCheckNotProcess
    Method _check_getTargetState();

    Integer getTargetState();
}
