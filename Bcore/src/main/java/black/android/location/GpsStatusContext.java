package black.android.location;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/location/GpsStatusContext.class */
@BClassNameNotProcess("android.location.GpsStatus")
public interface GpsStatusContext {
    @BMethodCheckNotProcess
    Method _check_setStatus(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, int i2, int i3, int i4);

    Void setStatus(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, int i2, int i3, int i4);
}
