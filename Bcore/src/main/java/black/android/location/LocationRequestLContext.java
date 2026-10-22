package black.android.location;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationRequestLContext.class */
@BClassNameNotProcess("android.location.LocationRequest")
public interface LocationRequestLContext {
    @BMethodCheckNotProcess
    Method _check_getProvider();

    String getProvider();

    @BFieldSetNotProcess
    void _set_mHideFromAppOps(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHideFromAppOps();

    @BFieldNotProcess
    Boolean mHideFromAppOps();

    @BFieldSetNotProcess
    void _set_mProvider(Object obj);

    @BFieldCheckNotProcess
    Field _check_mProvider();

    @BFieldNotProcess
    String mProvider();

    @BFieldSetNotProcess
    void _set_mWorkSource(Object obj);

    @BFieldCheckNotProcess
    Field _check_mWorkSource();

    @BFieldNotProcess
    Object mWorkSource();
}
