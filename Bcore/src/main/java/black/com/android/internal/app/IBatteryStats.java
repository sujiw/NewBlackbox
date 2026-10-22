package black.com.android.internal.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/IBatteryStats.class */
@BClassName("com.android.internal.app.IBatteryStats")
public interface IBatteryStats {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/IBatteryStats$Stub.class */
    @BClassName("com.android.internal.app.IBatteryStats$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
