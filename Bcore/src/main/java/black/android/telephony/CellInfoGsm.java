package black.android.telephony;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellInfoGsm.class */
@BClassName("android.telephony.CellInfoGsm")
public interface CellInfoGsm {
    @BConstructor
    CellInfoGsm _new();

    @BField
    android.telephony.CellIdentityGsm mCellIdentityGsm();

    @BField
    android.telephony.CellSignalStrengthGsm mCellSignalStrengthGsm();
}
