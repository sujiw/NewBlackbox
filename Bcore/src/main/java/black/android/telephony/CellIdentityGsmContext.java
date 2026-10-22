package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/CellIdentityGsmContext.class */
@BClassNameNotProcess("android.telephony.CellIdentityGsm")
public interface CellIdentityGsmContext {
    @BFieldSetNotProcess
    void _set_mCid(Object obj);

    @BFieldCheckNotProcess
    Field _check_mCid();

    @BFieldNotProcess
    Integer mCid();

    @BFieldSetNotProcess
    void _set_mLac(Object obj);

    @BFieldCheckNotProcess
    Field _check_mLac();

    @BFieldNotProcess
    Integer mLac();

    @BFieldSetNotProcess
    void _set_mMcc(Object obj);

    @BFieldCheckNotProcess
    Field _check_mMcc();

    @BFieldNotProcess
    Integer mMcc();

    @BFieldSetNotProcess
    void _set_mMnc(Object obj);

    @BFieldCheckNotProcess
    Field _check_mMnc();

    @BFieldNotProcess
    Integer mMnc();
}
