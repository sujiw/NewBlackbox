package black.android.content;

import android.accounts.Account;
import android.os.Bundle;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/SyncRequestContext.class */
@BClassNameNotProcess("android.content.SyncRequest")
public interface SyncRequestContext {
    @BFieldSetNotProcess
    void _set_mAccountToSync(Object obj);

    @BFieldCheckNotProcess
    Field _check_mAccountToSync();

    @BFieldNotProcess
    Account mAccountToSync();

    @BFieldSetNotProcess
    void _set_mAuthority(Object obj);

    @BFieldCheckNotProcess
    Field _check_mAuthority();

    @BFieldNotProcess
    String mAuthority();

    @BFieldSetNotProcess
    void _set_mExtras(Object obj);

    @BFieldCheckNotProcess
    Field _check_mExtras();

    @BFieldNotProcess
    Bundle mExtras();

    @BFieldSetNotProcess
    void _set_mIsPeriodic(Object obj);

    @BFieldCheckNotProcess
    Field _check_mIsPeriodic();

    @BFieldNotProcess
    Boolean mIsPeriodic();

    @BFieldSetNotProcess
    void _set_mSyncFlexTimeSecs(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSyncFlexTimeSecs();

    @BFieldNotProcess
    Long mSyncFlexTimeSecs();

    @BFieldSetNotProcess
    void _set_mSyncRunTimeSecs(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSyncRunTimeSecs();

    @BFieldNotProcess
    Long mSyncRunTimeSecs();
}
