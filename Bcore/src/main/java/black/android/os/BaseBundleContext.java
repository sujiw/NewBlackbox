package black.android.os;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/BaseBundleContext.class */
@BClassNameNotProcess("android.os.BaseBundle")
public interface BaseBundleContext {
    @BFieldSetNotProcess
    void _set_mParcelledData(Object obj);

    @BFieldCheckNotProcess
    Field _check_mParcelledData();

    @BFieldNotProcess
    android.os.Parcel mParcelledData();
}
