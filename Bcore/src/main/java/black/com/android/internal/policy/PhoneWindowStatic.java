package black.com.android.internal.policy;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/policy/PhoneWindowStatic.class */
@BClassNameNotProcess("com.android.internal.policy.PhoneWindow$WindowManagerHolder")
public interface PhoneWindowStatic {
    @BFieldSetNotProcess
    void _set_sWindowManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_sWindowManager();

    @BFieldNotProcess
    IInterface sWindowManager();
}
