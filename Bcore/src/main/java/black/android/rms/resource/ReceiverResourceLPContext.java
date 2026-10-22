package black.android.rms.resource;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/ReceiverResourceLPContext.class */
@BClassNameNotProcess("android.rms.resource.ReceiverResource")
public interface ReceiverResourceLPContext {
    @BFieldSetNotProcess
    void _set_mResourceConfig(Object obj);

    @BFieldCheckNotProcess
    Field _check_mResourceConfig();

    @BFieldNotProcess
    Object mResourceConfig();
}
