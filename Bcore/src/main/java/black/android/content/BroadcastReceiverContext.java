package black.android.content;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BParamClassName;

/* JADX INFO: loaded from: classes.jar:black/android/content/BroadcastReceiverContext.class */
@BClassNameNotProcess("android.content.BroadcastReceiver")
public interface BroadcastReceiverContext {
    @BMethodCheckNotProcess
    Method _check_getPendingResult();

    android.content.BroadcastReceiver.PendingResult getPendingResult();

    @BMethodCheckNotProcess
    Method _check_setPendingResult(@BParamClassName("android.content.BroadcastReceiver$PendingResult") Object obj);

    Void setPendingResult(@BParamClassName("android.content.BroadcastReceiver$PendingResult") Object obj);
}
