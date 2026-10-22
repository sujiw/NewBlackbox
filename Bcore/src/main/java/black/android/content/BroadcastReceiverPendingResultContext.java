package black.android.content;

import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/BroadcastReceiverPendingResultContext.class */
@BClassNameNotProcess("android.content.BroadcastReceiver$PendingResult")
public interface BroadcastReceiverPendingResultContext {
    @BFieldSetNotProcess
    void _set_mAbortBroadcast(Object obj);

    @BFieldCheckNotProcess
    Field _check_mAbortBroadcast();

    @BFieldNotProcess
    Boolean mAbortBroadcast();

    @BFieldSetNotProcess
    void _set_mFinished(Object obj);

    @BFieldCheckNotProcess
    Field _check_mFinished();

    @BFieldNotProcess
    Boolean mFinished();

    @BFieldSetNotProcess
    void _set_mInitialStickyHint(Object obj);

    @BFieldCheckNotProcess
    Field _check_mInitialStickyHint();

    @BFieldNotProcess
    Boolean mInitialStickyHint();

    @BFieldSetNotProcess
    void _set_mOrderedHint(Object obj);

    @BFieldCheckNotProcess
    Field _check_mOrderedHint();

    @BFieldNotProcess
    Boolean mOrderedHint();

    @BFieldSetNotProcess
    void _set_mResultCode(Object obj);

    @BFieldCheckNotProcess
    Field _check_mResultCode();

    @BFieldNotProcess
    Integer mResultCode();

    @BFieldSetNotProcess
    void _set_mResultData(Object obj);

    @BFieldCheckNotProcess
    Field _check_mResultData();

    @BFieldNotProcess
    String mResultData();

    @BFieldSetNotProcess
    void _set_mResultExtras(Object obj);

    @BFieldCheckNotProcess
    Field _check_mResultExtras();

    @BFieldNotProcess
    Bundle mResultExtras();

    @BFieldSetNotProcess
    void _set_mSendingUser(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSendingUser();

    @BFieldNotProcess
    Integer mSendingUser();

    @BFieldSetNotProcess
    void _set_mToken(Object obj);

    @BFieldCheckNotProcess
    Field _check_mToken();

    @BFieldNotProcess
    IBinder mToken();

    @BFieldSetNotProcess
    void _set_mType(Object obj);

    @BFieldCheckNotProcess
    Field _check_mType();

    @BFieldNotProcess
    Integer mType();
}
