package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellInfoGsmContext.class */
@BClassNameNotProcess("android.telephony.CellInfoGsm")
public interface CellInfoGsmContext {
    @BFieldSetNotProcess
    void _set_mCellIdentityGsm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCellIdentityGsm();

    @BFieldNotProcess
    android.telephony.CellIdentityGsm mCellIdentityGsm();

    @BFieldSetNotProcess
    void _set_mCellSignalStrengthGsm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCellSignalStrengthGsm();

    @BFieldNotProcess
    android.telephony.CellSignalStrengthGsm mCellSignalStrengthGsm();
}
