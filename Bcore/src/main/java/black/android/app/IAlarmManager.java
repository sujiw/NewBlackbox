package black.android.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IAlarmManager.class */
@BClassName("android.app.IAlarmManager")
public interface IAlarmManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/IAlarmManager$Stub.class */
    @BClassName("android.app.IAlarmManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
