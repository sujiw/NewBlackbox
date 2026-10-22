package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellSignalStrengthGsmContext.class */
@BClassNameNotProcess("android.telephony.CellSignalStrengthGsm")
public interface CellSignalStrengthGsmContext {
    @BFieldSetNotProcess
    void _set_mBitErrorRate(Object obj);

    @BFieldCheckNotProcess
    Field _check_mBitErrorRate();

    @BFieldNotProcess
    Integer mBitErrorRate();

    @BFieldSetNotProcess
    void _set_mSignalStrength(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSignalStrength();

    @BFieldNotProcess
    Integer mSignalStrength();
}
