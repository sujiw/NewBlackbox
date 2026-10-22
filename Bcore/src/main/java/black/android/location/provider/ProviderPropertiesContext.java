package black.android.location.provider;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/provider/ProviderPropertiesContext.class */
@BClassNameNotProcess("android.location.provider.ProviderProperties")
public interface ProviderPropertiesContext {
    @BFieldSetNotProcess
    void _set_mHasNetworkRequirement(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasNetworkRequirement();

    @BFieldNotProcess
    Boolean mHasNetworkRequirement();

    @BFieldSetNotProcess
    void _set_mHasSatelliteRequirement(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasSatelliteRequirement();

    @BFieldNotProcess
    Boolean mHasSatelliteRequirement();

    @BFieldSetNotProcess
    void _set_mHasCellRequirement(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasCellRequirement();

    @BFieldNotProcess
    Boolean mHasCellRequirement();

    @BFieldSetNotProcess
    void _set_mHasMonetaryCost(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasMonetaryCost();

    @BFieldNotProcess
    Boolean mHasMonetaryCost();

    @BFieldSetNotProcess
    void _set_mHasAltitudeSupport(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasAltitudeSupport();

    @BFieldNotProcess
    Boolean mHasAltitudeSupport();

    @BFieldSetNotProcess
    void _set_mHasSpeedSupport(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasSpeedSupport();

    @BFieldNotProcess
    Boolean mHasSpeedSupport();

    @BFieldSetNotProcess
    void _set_mHasBearingSupport(Object obj);

    @BFieldCheckNotProcess
    Field _check_mHasBearingSupport();

    @BFieldNotProcess
    Boolean mHasBearingSupport();
}
