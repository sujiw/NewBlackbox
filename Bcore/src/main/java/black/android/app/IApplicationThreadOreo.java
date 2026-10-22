package black.android.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IApplicationThreadOreo.class */
@BClassName("android.app.IApplicationThread")
public interface IApplicationThreadOreo {

    /* JADX INFO: loaded from: classes.jar:black/android/app/IApplicationThreadOreo$Stub.class */
    @BClassName("android.app.IApplicationThread$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }

    @BMethod
    void scheduleServiceArgs();
}
