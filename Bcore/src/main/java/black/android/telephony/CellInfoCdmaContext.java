package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellInfoCdmaContext.class */
@BClassNameNotProcess("android.telephony.CellInfoCdma")
public interface CellInfoCdmaContext {
    @BFieldSetNotProcess
    void _set_mCellIdentityCdma(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCellIdentityCdma();

    @BFieldNotProcess
    android.telephony.CellIdentityCdma mCellIdentityCdma();

    @BFieldSetNotProcess
    void _set_mCellSignalStrengthCdma(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCellSignalStrengthCdma();

    @BFieldNotProcess
    android.telephony.CellSignalStrengthCdma mCellSignalStrengthCdma();
}
