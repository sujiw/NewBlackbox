package black.android.rms.resource;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/ReceiverResourceMContext.class */
@BClassNameNotProcess("android.rms.resource.ReceiverResource")
public interface ReceiverResourceMContext {
    @BFieldSetNotProcess
    void _set_mWhiteList(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWhiteList();

    @BFieldNotProcess
    String[] mWhiteList();
}
