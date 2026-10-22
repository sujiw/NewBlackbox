package black.android.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IInterface;
import java.util.HashMap;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager.class */
@BClassName("android.location.LocationManager")
public interface LocationManager {

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GnssStatusListenerTransport.class */
    @BClassName("android.location.LocationManager$GnssStatusListenerTransport")
    public interface GnssStatusListenerTransport {
        @BField
        Object mGpsListener();

        @BField
        Object mGpsNmeaListener();

        @BField
        Object this$0();

        @BMethod
        void onFirstFix(int i);

        @BMethod
        void onGnssStarted();

        @BMethod
        void onNmeaReceived(long j, String str);
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GnssStatusListenerTransportO.class */
    @BClassName("android.location.LocationManager$GnssStatusListenerTransport")
    public interface GnssStatusListenerTransportO {
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GpsStatusListenerTransport.class */
    @BClassName("android.location.LocationManager$GpsStatusListenerTransport")
    public interface GpsStatusListenerTransport {
        @BField
        Object mListener();

        @BField
        Object mNmeaListener();

        @BField
        Object this$0();

        @BMethod
        void onFirstFix(int i);

        @BMethod
        void onGpsStarted();

        @BMethod
        void onNmeaReceived(long j, String str);
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GpsStatusListenerTransportOPPO_R815T.class */
    @BClassName("android.location.LocationManager$GpsStatusListenerTransport")
    public interface GpsStatusListenerTransportOPPO_R815T {
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GpsStatusListenerTransportSumsungS5.class */
    @BClassName("android.location.LocationManager$GpsStatusListenerTransport")
    public interface GpsStatusListenerTransportSumsungS5 {
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$GpsStatusListenerTransportVIVO.class */
    @BClassName("android.location.LocationManager$GnssStatusListenerTransport")
    public interface GpsStatusListenerTransportVIVO {
        @BMethod
        void onSvStatusChanged(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, int i2, int i3, int i4, long[] jArr);
    }

    /* JADX INFO: loaded from: classes.jar:black/android/location/LocationManager$ListenerTransport.class */
    @BClassName("android.location.LocationManager$GnssStatusListenerTransport")
    public interface ListenerTransport {
        @BField
        LocationListener mListener();

        @BField
        Object this$0();

        @BMethod
        void onLocationChanged(Location location);

        @BMethod
        void onProviderDisabled(String str);

        @BMethod
        void onProviderEnabled(String str);

        @BMethod
        void onStatusChanged(String str, int i, Bundle bundle);
    }

    @BField
    HashMap mGnssNmeaListeners();

    @BField
    HashMap mGnssStatusListeners();

    @BField
    HashMap mGpsNmeaListeners();

    @BField
    HashMap mGpsStatusListeners();

    @BField
    HashMap mListeners();

    @BField
    HashMap mNmeaListeners();

    @BField
    IInterface mService();
}
