package black.android.net;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/net/NetworkInfoContext.class */
@BClassNameNotProcess("android.net.NetworkInfo")
public interface NetworkInfoContext {
    @BFieldSetNotProcess
    void _set_mDetailedState(Object obj);

    @BFieldCheckNotProcess
    Field _check_mDetailedState();

    @BFieldNotProcess
    android.net.NetworkInfo.DetailedState mDetailedState();

    @BFieldSetNotProcess
    void _set_mIsAvailable(Object obj);

    @BFieldCheckNotProcess
    Field _check_mIsAvailable();

    @BFieldNotProcess
    Boolean mIsAvailable();

    @BFieldSetNotProcess
    void _set_mNetworkType(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNetworkType();

    @BFieldNotProcess
    Integer mNetworkType();

    @BFieldSetNotProcess
    void _set_mState(Object obj);

    @BFieldCheckNotProcess
    Field _check_mState();

    @BFieldNotProcess
    android.net.NetworkInfo.State mState();

    @BFieldSetNotProcess
    void _set_mTypeName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mTypeName();

    @BFieldNotProcess
    String mTypeName();
}
