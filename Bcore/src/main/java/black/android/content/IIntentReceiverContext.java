package black.android.content;

import android.content.Intent;
import android.os.Bundle;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/IIntentReceiverContext.class */
@BClassNameNotProcess(android.content.IIntentReceiver.Stub.DESCRIPTOR)
public interface IIntentReceiverContext {
    @BMethodCheckNotProcess
    Method _check_performReceive(Intent intent, int i, String str, Bundle bundle, boolean z, boolean z2, int i2);

    Void performReceive(Intent intent, int i, String str, Bundle bundle, boolean z, boolean z2, int i2);
}
