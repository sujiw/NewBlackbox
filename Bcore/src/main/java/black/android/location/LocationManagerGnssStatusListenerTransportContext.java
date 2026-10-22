package black.android.location;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManagerGnssStatusListenerTransportContext.class */
@BClassNameNotProcess("android.location.LocationManager$GnssStatusListenerTransport")
public interface LocationManagerGnssStatusListenerTransportContext {
    @BMethodCheckNotProcess
    Method _check_onFirstFix(int i);

    Void onFirstFix(int i);

    @BMethodCheckNotProcess
    Method _check_onGnssStarted();

    Void onGnssStarted();

    @BMethodCheckNotProcess
    Method _check_onNmeaReceived(long j, String str);

    Void onNmeaReceived(long j, String str);

    @BFieldSetNotProcess
    void _set_mGpsListener(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsListener();

    @BFieldNotProcess
    Object mGpsListener();

    @BFieldSetNotProcess
    void _set_mGpsNmeaListener(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsNmeaListener();

    @BFieldNotProcess
    Object mGpsNmeaListener();

    @BFieldSetNotProcess
    void _set_this$0(Object obj);

    @BFieldCheckNotProcess
    Field _check_this$0();

    @BFieldNotProcess
    Object this$0();
}
