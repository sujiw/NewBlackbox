package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellSignalStrengthCdmaContext.class */
@BClassNameNotProcess("android.telephony.CellSignalStrengthCdma")
public interface CellSignalStrengthCdmaContext {
    @BFieldSetNotProcess
    void _set_mCdmaDbm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCdmaDbm();

    @BFieldNotProcess
    Integer mCdmaDbm();

    @BFieldSetNotProcess
    void _set_mCdmaEcio(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCdmaEcio();

    @BFieldNotProcess
    Integer mCdmaEcio();

    @BFieldSetNotProcess
    void _set_mEvdoDbm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mEvdoDbm();

    @BFieldNotProcess
    Integer mEvdoDbm();

    @BFieldSetNotProcess
    void _set_mEvdoEcio(Object obj);

    @BFieldCheckNotProcess
    Field _check_mEvdoEcio();

    @BFieldNotProcess
    Integer mEvdoEcio();

    @BFieldSetNotProcess
    void _set_mEvdoSnr(Object obj);

    @BFieldCheckNotProcess
    Field _check_mEvdoSnr();

    @BFieldNotProcess
    Integer mEvdoSnr();
}
