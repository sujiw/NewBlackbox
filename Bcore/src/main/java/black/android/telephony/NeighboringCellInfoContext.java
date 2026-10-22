package black.android.telephony;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/NeighboringCellInfoContext.class */
@BClassNameNotProcess("android.telephony.NeighboringCellInfo")
public interface NeighboringCellInfoContext {
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
    void _set_mRssi(Object obj);

    @BFieldCheckNotProcess
    Field _check_mRssi();

    @BFieldNotProcess
    Integer mRssi();
}
