package black.android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityContext.class */
@BClassNameNotProcess("android.app.Activity")
public interface ActivityContext {
    @BMethodCheckNotProcess
    Method _check_onActivityResult(int i, int i2, Intent intent);

    Void onActivityResult(int i, int i2, Intent intent);

    @BFieldSetNotProcess
    void _set_mActivityInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActivityInfo();

    @BFieldNotProcess
    ActivityInfo mActivityInfo();

    @BFieldSetNotProcess
    void _set_mEmbeddedID(Object obj);

    @BFieldCheckNotProcess
    Field _check_mEmbeddedID();

    @BFieldNotProcess
    String mEmbeddedID();

    @BFieldSetNotProcess
    void _set_mFinished(Object obj);

    @BFieldCheckNotProcess
    Field _check_mFinished();

    @BFieldNotProcess
    Boolean mFinished();

    @BFieldSetNotProcess
    void _set_mParent(Object obj);

    @BFieldCheckNotProcess
    Field _check_mParent();

    @BFieldNotProcess
    android.app.Activity mParent();

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
    Intent mResultData();

    @BFieldSetNotProcess
    void _set_mToken(Object obj);

    @BFieldCheckNotProcess
    Field _check_mToken();

    @BFieldNotProcess
    IBinder mToken();
}
