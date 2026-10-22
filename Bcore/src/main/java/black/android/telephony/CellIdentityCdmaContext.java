package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellIdentityCdmaContext.class */
@BClassNameNotProcess("android.telephony.CellIdentityCdma")
public interface CellIdentityCdmaContext {
    @BFieldSetNotProcess
    void _set_mBasestationId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mBasestationId();

    @BFieldNotProcess
    Integer mBasestationId();

    @BFieldSetNotProcess
    void _set_mNetworkId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNetworkId();

    @BFieldNotProcess
    Integer mNetworkId();

    @BFieldSetNotProcess
    void _set_mSystemId(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSystemId();

    @BFieldNotProcess
    Integer mSystemId();
}
