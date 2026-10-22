package black.android.telephony;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellInfoCdma.class */
@BClassName("android.telephony.CellInfoCdma")
public interface CellInfoCdma {
    @BConstructor
    CellInfoCdma _new();

    @BField
    android.telephony.CellIdentityCdma mCellIdentityCdma();

    @BField
    android.telephony.CellSignalStrengthCdma mCellSignalStrengthCdma();
}
