package black.android.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IActivityTaskManager.class */
@BClassName("android.app.IActivityTaskManager")
public interface IActivityTaskManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/IActivityTaskManager$Stub.class */
    @BClassName("android.app.IActivityTaskManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
