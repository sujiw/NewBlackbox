package black.android.app.servertransaction;

import android.os.IBinder;
import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/ClientTransactionContext.class */
@BClassNameNotProcess("android.app.servertransaction.ClientTransaction")
public interface ClientTransactionContext {
    @BFieldSetNotProcess
    void _set_mActivityCallbacks(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActivityCallbacks();

    @BFieldNotProcess
    List<Object> mActivityCallbacks();

    @BFieldSetNotProcess
    void _set_mActivityToken(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActivityToken();

    @BFieldNotProcess
    IBinder mActivityToken();

    @BFieldSetNotProcess
    void _set_mLifecycleStateRequest(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLifecycleStateRequest();

    @BFieldNotProcess
    Object mLifecycleStateRequest();
}
