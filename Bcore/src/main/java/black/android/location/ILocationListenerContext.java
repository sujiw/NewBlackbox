package black.android.location;

import android.location.Location;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/ILocationListenerContext.class */
@BClassNameNotProcess(android.location.ILocationListener.Stub.DESCRIPTOR)
public interface ILocationListenerContext {
    @BMethodCheckNotProcess
    Method _check_onLocationChanged(Location location);

    Void onLocationChanged(Location location);
}
