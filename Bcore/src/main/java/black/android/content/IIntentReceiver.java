package black.android.content;

import android.content.Intent;
import android.os.Bundle;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/content/IIntentReceiver.class */
@BClassName(android.content.IIntentReceiver.Stub.DESCRIPTOR)
public interface IIntentReceiver {
    @BMethod
    void performReceive(Intent intent, int i, String str, Bundle bundle, boolean z, boolean z2, int i2);
}
