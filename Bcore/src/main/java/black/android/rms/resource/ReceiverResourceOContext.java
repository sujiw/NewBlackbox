package black.android.rms.resource;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/rms/resource/ReceiverResourceOContext.class */
@BClassNameNotProcess("android.rms.resource.ReceiverResource")
public interface ReceiverResourceOContext {
    @BFieldSetNotProcess
    void _set_mWhiteListMap(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWhiteListMap();

    @BFieldNotProcess
    Map<Integer, List<String>> mWhiteListMap();
}
