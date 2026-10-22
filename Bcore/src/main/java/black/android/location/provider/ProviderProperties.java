package black.android.location.provider;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/location/provider/ProviderProperties.class */
@BClassName("android.location.provider.ProviderProperties")
public interface ProviderProperties {
    @BField
    boolean mHasNetworkRequirement();

    @BField
    boolean mHasSatelliteRequirement();

    @BField
    boolean mHasCellRequirement();

    @BField
    boolean mHasMonetaryCost();

    @BField
    boolean mHasAltitudeSupport();

    @BField
    boolean mHasSpeedSupport();

    @BField
    boolean mHasBearingSupport();
}
