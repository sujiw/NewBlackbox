package black.android.app;

import android.os.IInterface;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityClientActivityClientControllerSingletonContext.class */
@BClassNameNotProcess("android.app.ActivityClient$ActivityClientControllerSingleton")
public interface ActivityClientActivityClientControllerSingletonContext {
    @BFieldSetNotProcess
    void _set_mKnownInstance(Object obj);

    @BFieldCheckNotProcess
    Field _check_mKnownInstance();

    @BFieldNotProcess
    IInterface mKnownInstance();
}
