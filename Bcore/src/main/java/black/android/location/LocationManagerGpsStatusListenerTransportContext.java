package black.android.location;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManagerGpsStatusListenerTransportContext.class */
@BClassNameNotProcess("android.location.LocationManager$GpsStatusListenerTransport")
public interface LocationManagerGpsStatusListenerTransportContext {
    @BMethodCheckNotProcess
    Method _check_onFirstFix(int i);

    Void onFirstFix(int i);

    @BMethodCheckNotProcess
    Method _check_onGpsStarted();

    Void onGpsStarted();

    @BMethodCheckNotProcess
    Method _check_onNmeaReceived(long j, String str);

    Void onNmeaReceived(long j, String str);

    @BFieldSetNotProcess
    void _set_mListener(Object obj);

    @BFieldCheckNotProcess
    Field _check_mListener();

    @BFieldNotProcess
    Object mListener();

    @BFieldSetNotProcess
    void _set_mNmeaListener(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNmeaListener();

    @BFieldNotProcess
    Object mNmeaListener();

    @BFieldSetNotProcess
    void _set_this$0(Object obj);

    @BFieldCheckNotProcess
    Field _check_this$0();

    @BFieldNotProcess
    Object this$0();
}
