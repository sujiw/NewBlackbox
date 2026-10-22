package black.android.rms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/rms/HwSysResImplPContext.class */
@BClassNameNotProcess("android.rms.HwSysResImpl")
public interface HwSysResImplPContext {
    @BFieldSetNotProcess
    void _set_mWhiteListMap(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWhiteListMap();

    @BFieldNotProcess
    Map<Integer, ArrayList<String>> mWhiteListMap();
}
