package black.android.location;

import android.util.ArrayMap;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManagerQContext.class */
@BClassNameNotProcess("android.location.LocationManager")
public interface LocationManagerQContext {
    @BFieldSetNotProcess
    void _set_mGnssNmeaListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGnssNmeaListeners();

    @BFieldNotProcess
    ArrayMap mGnssNmeaListeners();

    @BFieldSetNotProcess
    void _set_mGnssStatusListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGnssStatusListeners();

    @BFieldNotProcess
    ArrayMap mGnssStatusListeners();

    @BFieldSetNotProcess
    void _set_mGpsNmeaListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsNmeaListeners();

    @BFieldNotProcess
    ArrayMap mGpsNmeaListeners();

    @BFieldSetNotProcess
    void _set_mGpsStatusListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mGpsStatusListeners();

    @BFieldNotProcess
    ArrayMap mGpsStatusListeners();

    @BFieldSetNotProcess
    void _set_mListeners(Object obj);

    @BFieldCheckNotProcess
    Field _check_mListeners();

    @BFieldNotProcess
    ArrayMap mListeners();
}
