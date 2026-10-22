package black.android.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IUsageStatsManager.class */
@BClassName("android.app.usage.IUsageStatsManager")
public interface IUsageStatsManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/IUsageStatsManager$Stub.class */
    @BClassName("android.app.usage.IUsageStatsManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
