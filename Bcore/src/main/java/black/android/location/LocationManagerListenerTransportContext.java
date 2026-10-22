package black.android.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManagerListenerTransportContext.class */
@BClassNameNotProcess("android.location.LocationManager$GnssStatusListenerTransport")
public interface LocationManagerListenerTransportContext {
    @BMethodCheckNotProcess
    Method _check_onLocationChanged(Location location);

    Void onLocationChanged(Location location);

    @BMethodCheckNotProcess
    Method _check_onProviderDisabled(String str);

    Void onProviderDisabled(String str);

    @BMethodCheckNotProcess
    Method _check_onProviderEnabled(String str);

    Void onProviderEnabled(String str);

    @BMethodCheckNotProcess
    Method _check_onStatusChanged(String str, int i, Bundle bundle);

    Void onStatusChanged(String str, int i, Bundle bundle);

    @BFieldSetNotProcess
    void _set_mListener(Object obj);

    @BFieldCheckNotProcess
    Field _check_mListener();

    @BFieldNotProcess
    LocationListener mListener();

    @BFieldSetNotProcess
    void _set_this$0(Object obj);

    @BFieldCheckNotProcess
    Field _check_this$0();

    @BFieldNotProcess
    Object this$0();
}
