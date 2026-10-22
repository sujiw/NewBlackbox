package black.android.location;

import android.os.IInterface;
import java.lang.reflect.Field;
import java.util.HashMap;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManagerContext.class */
@BClassNameNotProcess("android.location.LocationManager")
public interface LocationManagerContext {
    @BFieldSetNotProcess
    void _set_mGnssNmeaListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGnssNmeaListeners();

    @BFieldNotProcess
    HashMap mGnssNmeaListeners();

    @BFieldSetNotProcess
    void _set_mGnssStatusListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGnssStatusListeners();

    @BFieldNotProcess
    HashMap mGnssStatusListeners();

    @BFieldSetNotProcess
    void _set_mGpsNmeaListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsNmeaListeners();

    @BFieldNotProcess
    HashMap mGpsNmeaListeners();

    @BFieldSetNotProcess
    void _set_mGpsStatusListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsStatusListeners();

    @BFieldNotProcess
    HashMap mGpsStatusListeners();

    @BFieldSetNotProcess
    void _set_mListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mListeners();

    @BFieldNotProcess
    HashMap mListeners();

    @BFieldSetNotProcess
    void _set_mNmeaListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mNmeaListeners();

    @BFieldNotProcess
    HashMap mNmeaListeners();

    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    IInterface mService();
}
