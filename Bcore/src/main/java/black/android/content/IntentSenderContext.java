package black.android.content;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/IntentSenderContext.class */
@BClassNameNotProcess("android.content.IntentSender")
public interface IntentSenderContext {
    @BFieldSetNotProcess
    void _set_mTarget(Object obj);

    @BFieldCheckNotProcess
    Field _check_mTarget();

    @BFieldNotProcess
    IInterface mTarget();
}
