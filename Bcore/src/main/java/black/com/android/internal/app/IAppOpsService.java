package black.com.android.internal.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/IAppOpsService.class */
@BClassName("com.android.internal.app.IAppOpsService")
public interface IAppOpsService {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/app/IAppOpsService$Stub.class */
    @BClassName("com.android.internal.app.IAppOpsService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
